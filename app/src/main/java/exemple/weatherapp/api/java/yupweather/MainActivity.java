package exemple.weatherapp.api.java.yupweather;

import android.annotation.SuppressLint;
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

import exemple.weatherapp.api.java.yupweather.databinding.ActivityMainBinding;
import exemple.weatherapp.api.java.yupweather.utilities.SystemUi;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

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

        TextView textView = findViewById(R.id.textView_todayWeather);

        textView.setText("adadadad");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuItem_gpsLocation) {
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
}