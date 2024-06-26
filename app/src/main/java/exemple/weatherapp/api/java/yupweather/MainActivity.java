package exemple.weatherapp.api.java.yupweather;

import static java.lang.Integer.parseInt;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertDoubleToInteger;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertErrorMessageSize;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertKevinToCelsius;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertMeterToKilometer;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertWindSpeedMeterToKilometer;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.simpleConvertDate;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.simpleConvertHourSeconds;
import static exemple.weatherapp.api.java.yupweather.utilities.CustomAlerts.setToastAlert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;

import exemple.weatherapp.api.java.yupweather.adapter.HourlyForecastAdapter;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientConditions;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientMain;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientHourly;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceConditions;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceHourly;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceMain;
import exemple.weatherapp.api.java.yupweather.database.local.SharedPreferenceLocation;
import exemple.weatherapp.api.java.yupweather.databinding.ActivityMainBinding;
import exemple.weatherapp.api.java.yupweather.model.ErrorResponse;
import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyResponse;
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
    private TextView forecastDays ;
    private ImageView todayIconForecast;

    private GPSTracker gpsTracker;

    private DataServiceConditions dataServiceConditions;
    private DataServiceMain dataServiceMain;
    private DataServiceHourly dataServiviceHours;

    private Call<WeatherConditionsDay> callDayConditions;
    private Call<WeatherMainDay> callDayMain;
    private Call<HourlyResponse> callDayHourly ;

    private RecyclerView recyclerViewHourly;
    private ErrorResponse errorResponse;



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

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.scrollView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


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


        getLocationData();

        String latitude = SharedPreferenceLocation.getLatitudeLocation(getBaseContext());
        String longitude = SharedPreferenceLocation.getLongitudeLocation(getBaseContext());

        Log.d("LocationGet: ", "lat: " + latitude + " , " + "long: " + longitude );

        dataServiceConditions = APIClientConditions.getConditionsInstance().create(DataServiceConditions.class);
        dataServiceMain = APIClientMain.getMainInstance().create(DataServiceMain.class);
        dataServiviceHours = APIClientHourly.getHoursInstance().create(DataServiceHourly.class);


        callDayConditions = dataServiceConditions.getDayWeatherConditions(latitude, longitude, Constants.API_KEY);
        callDayMain = dataServiceMain.getDayWeatherMain(latitude, longitude, Constants.API_KEY);
        callDayHourly = dataServiviceHours.getHourlyWeatherConditions(latitude, longitude, Constants.CMT_COUNT, Constants.API_KEY, Constants.UNITS_FORMAT);

        recyclerViewHourly = binding.recyclerViewForecastMini;
        recyclerViewHourly.setHasFixedSize(true);


        forecastDays.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DailyForecastActivity.class);
            startActivity(intent);
        });

        getHoursForecast();
        getMainWeather();
        getConditionWeather();
    }

    private void getHoursForecast() {
        callDayHourly.clone().enqueue(new Callback<HourlyResponse>() {
            @Override
            public void onResponse(Call<HourlyResponse> call, Response <HourlyResponse> response) {

                if (response.isSuccessful()) {
                    HourlyResponse weatherForecastHourly = response.body();
                    assert weatherForecastHourly != null;


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    linearLayoutManager.setSmoothScrollbarEnabled(true);
                    linearLayoutManager.setReverseLayout(false);
                    recyclerViewHourly.setLayoutManager(linearLayoutManager);

                    HourlyForecastAdapter hourlyForecastAdapter = new HourlyForecastAdapter(weatherForecastHourly.getList(), MainActivity.this);
                    recyclerViewHourly.setAdapter(hourlyForecastAdapter);

                    Log.d("Success H: ", "Response: " + new Gson().toJson(weatherForecastHourly) );
                    Log.d("Success H: ", response.message() + " " + response.code());

                }else {
                    try {
                        errorResponse = APIClientHourly.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("MainActivity", "Error code H: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);

                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        setToastAlert(MainActivity.this, errorTextConvert);

                    }

                    Log.d("Error: ", response.message() + " " + response.code() + " " + response.errorBody());

                }
            }


            @Override
            public void onFailure(Call<HourlyResponse> call, Throwable t) {
                Log.d("Network error H: ", t.getMessage());
            }
        });
    }

    private void getMainWeather() {
        callDayMain.clone().enqueue(new Callback<WeatherMainDay>() {

            @Override
            public void onResponse(Call<WeatherMainDay> call, Response<WeatherMainDay> response) {
                if (response.isSuccessful()) {
                    WeatherMainDay weatherMainDay = response.body();
                    assert weatherMainDay != null;
                    getMainWeatherData(weatherMainDay);
                    Log.d("Success M: ", "Response: " + new Gson().toJson(weatherMainDay) );
                    Log.d("Success M: ", response.message() + " " + response.code());

                } else {
                    try {
                        errorResponse = APIClientMain.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("MainActivity", "Error code M: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);
                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        setToastAlert(MainActivity.this, errorTextConvert);

                    }
                    Log.d("Error: ", response.message() + " " + response.code() + " " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<WeatherMainDay> call, Throwable t) {
                Log.d("Network error: ", t.getMessage());
            }
        });
    }

    private void getMainWeatherData(WeatherMainDay weatherMainDay) {

        TextView toolbarCityName = binding.toolbarMain.textViewSelectedCity;
        toolbarCityName.setText(new StringBuilder().append(
                weatherMainDay.getName()).append(", ")
                .append(weatherMainDay.getCountry())
        );

        String convertedHour =  simpleConvertHourSeconds(weatherMainDay.getDt(), weatherMainDay.getTimezone());

        TextView toolbarWeatherLastUpdate = binding.toolbarMain.textViewWeatherData;
        toolbarWeatherLastUpdate.setText(new StringBuilder()
                .append("Last Update: ")
                .append(convertedHour));

        String convertedDate = simpleConvertDate(weatherMainDay.getDt(), weatherMainDay.getTimezone());
        dateTimeDay.setText(convertedDate);

        todayWeather.setText(new StringBuilder()
                .append(weatherMainDay.getMain())
                .append(", ")
                .append(weatherMainDay.getDescription()));


        String mIconUrl = "https://openweathermap.org/img/wn/" + weatherMainDay.getIcon() + "@2x.png";

        switch (weatherMainDay.getIcon()) {

            case "01d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "01n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "02d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "02n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "03d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "03n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "04d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "04n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "09d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "09n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "10d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "10n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "11d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "11n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "13d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "13n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "50d":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_gps_fixed_24)
                        .into(todayIconForecast);
            case "50n":
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            default:
                Glide.with(this).load(mIconUrl).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
                break;
        }
    }


    private void getConditionWeather() {

        callDayConditions.clone().enqueue(new Callback<WeatherConditionsDay>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<WeatherConditionsDay> call, Response<WeatherConditionsDay> response) {
                if (response.isSuccessful()) {
                    WeatherConditionsDay weatherConditionsDay = response.body();
                    assert weatherConditionsDay != null;
                    getWeatherConditionDayData(weatherConditionsDay);
                    Log.d("Success C: ", "Response: " + new Gson().toJson(weatherConditionsDay) );
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
                        Log.e("MainActivity", "Error code C: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

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
        temperatureDay.setText(new StringBuilder().append(temp).append(getString(R.string.special_character)));

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

            Log.d("LocationSet: ", "lat: " + textLatitude + " , " + "long: " + textLongitude );

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
        forecastDays = binding.textViewNextSevenDays;
        todayIconForecast = binding.imageViewTodayIconForecast;
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
        getHoursForecast();
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