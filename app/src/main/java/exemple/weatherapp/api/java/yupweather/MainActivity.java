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

import exemple.weatherapp.api.java.yupweather.databinding.ActivityMainBinding;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import exemple.weatherapp.api.java.yupweather.utilities.GPSTracker;
import exemple.weatherapp.api.java.yupweather.utilities.SystemUi;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private TextView dateTimeDay, temperatureDay, todayWeather,
            visibilityDay, pressureDay, windDay, humidityDay;

    private GPSTracker gpsTracker;

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

        textView.setText("adadadad");

        try {
            if ( ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED ){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, Constants.requestCode );
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        getLocationData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

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