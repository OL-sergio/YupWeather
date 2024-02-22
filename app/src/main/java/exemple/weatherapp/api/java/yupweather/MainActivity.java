package exemple.weatherapp.api.java.yupweather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import exemple.weatherapp.api.java.yupweather.database.api.APIClient;
import exemple.weatherapp.api.java.yupweather.database.api.DataService;
import exemple.weatherapp.api.java.yupweather.database.local.SharedPreferenceLocation;
import exemple.weatherapp.api.java.yupweather.databinding.ActivityMainBinding;
import exemple.weatherapp.api.java.yupweather.model.WeatherDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import exemple.weatherapp.api.java.yupweather.utilities.CustomAlertDialog;
import exemple.weatherapp.api.java.yupweather.utilities.GPSTracker;
import exemple.weatherapp.api.java.yupweather.utilities.SystemUi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TextView dateTimeDay, temperatureDay, todayWeather,
            visibilityDay, pressureDay, windDay, humidityDay;

    private GPSTracker gpsTracker;

    private DataService dataService;
    private Call<WeatherDay> dayCall;

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

        TextView textView = findViewById(R.id.textView_todayWeather);

        textView.setText("test");

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

        dataService = APIClient.getInstance().create(DataService.class);

        dayCall = dataService.getDayWeather(latitude, longitude, Constants.API_KEY);

        dayCall.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                if (response.isSuccessful()) {
                    WeatherDay weatherDay = response.body();
                    getWeatherDayData(weatherDay);
                    Log.d("Success: ", response.message() + " " + response.code());

                } else {
                    Log.d("Error: ", response.message() + " " + response.code());
                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.d("Network error: ", t.getMessage());
            }
        });

    }

    private void getWeatherDayData(WeatherDay weatherDay) {

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

            String latitude = String.valueOf(gpsTracker.getLatitude());
            String longitude = String.valueOf(gpsTracker.getLongitude());

            Log.d("Location: ", latitude + " , " + longitude );

            SharedPreferenceLocation.setLocation(
                    getBaseContext(),
                    latitude,
                    longitude
            );

            String shareLatitude = SharedPreferenceLocation.getLatitudeLocation(getBaseContext());
            String shareLongitude = SharedPreferenceLocation.getLongitudeLocation(getBaseContext());

            Log.d("LocationSetShared: ", shareLatitude + " , " + shareLongitude );



        } else {

            CustomAlertDialog.setGpsSettings(this, "GPS settings","GPS is not enabled. Do you want to go to settings menu?");

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
    protected void onDestroy() {
        gpsTracker.stopUsingGPS();
        super.onDestroy();
    }
}