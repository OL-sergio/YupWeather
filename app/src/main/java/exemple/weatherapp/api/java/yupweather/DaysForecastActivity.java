package exemple.weatherapp.api.java.yupweather;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;

import exemple.weatherapp.api.java.yupweather.adapter.DaysForecastAdapter;
import exemple.weatherapp.api.java.yupweather.databinding.ActivityDaysForecastBinding;

public class DaysForecastActivity extends AppCompatActivity {

    private ActivityDaysForecastBinding binding;

    private RecyclerView recyclerViewDays;

    private List<String> daysForecastList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaysForecastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbarDays = binding.toolbarDaysForecast.toolbar;
        setSupportActionBar(toolbarDays);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
        toolbarDays.setNavigationIcon(drawable);

        daysForecastList.add("1");
        daysForecastList.add("2");
        daysForecastList.add("3");
        daysForecastList.add("4");
        daysForecastList.add("5");
        daysForecastList.add("6");
        daysForecastList.add("7");

        recyclerViewDays = binding.recyclerViewDaysForecast;
        recyclerViewDays.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDays.setAdapter(new DaysForecastAdapter(daysForecastList, getApplicationContext()));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}