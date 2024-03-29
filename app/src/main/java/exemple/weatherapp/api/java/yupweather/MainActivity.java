package exemple.weatherapp.api.java.yupweather;

import static java.lang.Integer.parseInt;

import static exemple.weatherapp.api.java.yupweather.utilities.Converts.*;
import static exemple.weatherapp.api.java.yupweather.utilities.CustomAlerts.setToastAlert;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exemple.weatherapp.api.java.yupweather.adapter.HoursForecast;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientMain;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientConditions;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceConditions;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceMain;
import exemple.weatherapp.api.java.yupweather.database.local.SharedPreferenceLocation;
import exemple.weatherapp.api.java.yupweather.databinding.ActivityMainBinding;
import exemple.weatherapp.api.java.yupweather.model.ErrorResponse;
import exemple.weatherapp.api.java.yupweather.model.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.model.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import exemple.weatherapp.api.java.yupweather.utilities.CustomAlerts;
import exemple.weatherapp.api.java.yupweather.utilities.GPSTracker;
import exemple.weatherapp.api.java.yupweather.utilities.SystemUi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TextView dateTimeDay;
    private TextView temperatureDay;
    private TextView todayWeather;
    private TextView visibilityDay;
    private TextView pressureDay;
    private TextView windDay;
    private TextView humidityDay;

    private GPSTracker gpsTracker;

    private DataServiceConditions dataServiceConditions;
    private DataServiceMain dataServiceMain;

    private Call<WeatherConditionsDay> dayCallConditions;
    private Call<WeatherMainDay> dayCallMain;
    private ErrorResponse errorResponse;

    private List<String> hoursForecastList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SystemUi systemUi = new SystemUi();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(systemUi.settingsMainNavigation());

        Toolbar toolbarMain = binding.toolbarMain.toolbar;
        setSupportActionBar(toolbarMain);

        components();

        try {
            if ( ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, Constants.REQUEST_CODE );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        String latitude = SharedPreferenceLocation.getLatitudeLocation(getBaseContext());
        String longitude = SharedPreferenceLocation.getLongitudeLocation(getBaseContext());

        Log.d("LocationGetShared: ", latitude + " , " + longitude );

        getLocationData();

        dataServiceConditions = APIClientConditions.getConditionsInstance().create(DataServiceConditions.class);
        dataServiceMain = APIClientMain.getMainInstance().create(DataServiceMain.class);

        dayCallConditions = dataServiceConditions.getDayWeatherConditions(latitude, longitude, Constants.API_KEY);
        dayCallMain = dataServiceMain.getDayWeatherMain(latitude, longitude, Constants.API_KEY);

        hoursForecastList.add("12:00 PM");
        hoursForecastList.add("1:00 PM");
        hoursForecastList.add("1:00 PM");
        hoursForecastList.add("2:00 PM");
        hoursForecastList.add("3:00 PM");
        hoursForecastList.add("4:00 PM");

        RecyclerView recyclerView = binding.recyclerViewForecastMini;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new HoursForecast(hoursForecastList, getApplicationContext()));

    }

    private void getMainWeather() {
        dayCallMain.clone().enqueue(new Callback<WeatherMainDay>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<WeatherMainDay> call, Response<WeatherMainDay> response) {
                if (response.isSuccessful()) {
                    WeatherMainDay weatherMainDay = response.body();
                    getMainWeatherData(weatherMainDay);
                    Log.d("Success M: ", "Response: " + new Gson().toJson(weatherMainDay) );
                    Log.d("Success M: ", response.message() + " " + response.code());

                } else {
                    Log.d("Error: ", response.message() + " " + response.code() + " " + response.errorBody());
                    try {
                        errorResponse = APIClientMain.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("MainActivity", "Error code: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);

                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        setToastAlert(MainActivity.this, errorTextConvert);

                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherMainDay> call, Throwable t) {
                Log.d("Network error: ", t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getMainWeatherData(WeatherMainDay weatherMainDay) {

        TextView toolbarCityName = binding.toolbarMain.textViewSelectedCity;
        toolbarCityName.setText(new StringBuilder().append(
                weatherMainDay.getName()).append(", ")
                .append(weatherMainDay.getCountry())
        );

        String convertedHour =  simpleConvertHour(weatherMainDay.getDt());

        TextView toolbarWeatherLastUpdate = binding.toolbarMain.textViewWeatherData;
        toolbarWeatherLastUpdate.setText(new StringBuilder()
                .append("Last Update: ")
                .append(convertedHour));

        String convertedDate = simpleConvertDate(weatherMainDay.getDt());
        dateTimeDay.setText(convertedDate);

        todayWeather.setText(new StringBuilder()
                .append(weatherMainDay.getMain())
                .append(" - ")
                .append(weatherMainDay.getDescription()));
    }


    private void getConditionWeather() {

        dayCallConditions.clone().enqueue(new Callback<WeatherConditionsDay>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<WeatherConditionsDay> call, Response<WeatherConditionsDay> response) {
                if (response.isSuccessful()) {
                    WeatherConditionsDay weatherConditionsDay = response.body();
                    assert weatherConditionsDay != null;
                    getWeatherConditionDayData(weatherConditionsDay);
                    Log.d("Success C: ", response.message() + " " + response.code());

                } else {
                    Log.d("Error: ", response.message() + " " + response.code() + " " + response.errorBody());

                    try {
                        errorResponse = APIClientConditions.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("MainActivity", "Error code: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);

                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        /*
                        View layout = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.layout_customToast));
                        TextView text = layout.findViewById(R.id.textView_customToast);
                        text.setText(errorTextConvert);
                        */

                        /*
                        *
                        Toast toast = new Toast(getApplicationContext());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, -125);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                        */

                        setToastAlert(MainActivity.this, errorTextConvert);

                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherConditionsDay> call, Throwable t) {
                Log.d("Network error: ", t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getWeatherConditionDayData(WeatherConditionsDay weatherConditionsDay) {

        int temp = (int) convertKevinToCelsius(weatherConditionsDay.getTemp());
        temperatureDay.setText(new StringBuilder().append(temp).append(getString(R.string.special_character)).append(getString(R.string.celcius)));

        int visibility = (int) convertMeterToKilometer(parseInt(weatherConditionsDay.getVisibility()));

        visibilityDay.setText(new StringBuilder().append(visibility).append(" km"));

        pressureDay.setText(weatherConditionsDay.getPressure());

         int winSpeedKmH = (int) convertWindSpeedMeterToKilometer(weatherConditionsDay.getSpeed());

         windDay.setText(new StringBuilder().append(winSpeedKmH).append(" km/h"));

         humidityDay.setText(new StringBuilder().append(weatherConditionsDay.getHumidity()).append("%"));
         humidityDay.setText(new StringBuilder().append(weatherConditionsDay.getHumidity()).append("%"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItem_gpsLocation) {
            getLocationData();
            getConditionWeather();
            getMainWeather();

            // Handle click event for menu item 1
            return true;

        } else if (item.getItemId() == R.id.menuItem_searchLocation) {
            // Handle click event for menu item 2
            Log.d("Data: ", "search");
            return true;

        } else {

            return super.onOptionsItemSelected(item);

        }
    }

    public void getLocationData(){
        gpsTracker = new GPSTracker(MainActivity.this );
        if (gpsTracker.statusSettingsLocation()){

            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            String textLatitude = convertDoubleToInteger(latitude);
            String textLongitude = convertDoubleToInteger(longitude);
            //String textLatitude = String.valueOf((int) Math.round(latitude));
            //String textLongitude = String.valueOf((int) Math.round(longitude));

            Log.d("Location: ", textLatitude + " , " + textLongitude );

            SharedPreferenceLocation.setLocation(
                    getBaseContext(),
                    textLatitude,
                    textLongitude
            );

        } else {

            CustomAlerts.setGpsSettings(this, "GPS settings","GPS is not enabled. Do you want to go to settings menu?");

        }
    }

    private void components() {
        dateTimeDay = binding.textViewDateForecast;
        temperatureDay = binding.textViewTodayTemperature;
        todayWeather = binding.textViewTodayWeather;
        visibilityDay = binding.textViewDataVisibility;
        pressureDay = binding.textViewDataAirPressure;
        windDay = binding.textViewDataWind;
        humidityDay = binding.textViewDataHumidity;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMainWeather();
        getConditionWeather();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getMainWeather();
        getConditionWeather();
    }

    @Override
    protected void onDestroy() {
        gpsTracker.stopUsingGPS();
        super.onDestroy();
    }
}