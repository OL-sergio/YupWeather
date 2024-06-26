package exemple.weatherapp.api.java.yupweather;

import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertErrorMessageSize;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.convertKevinToCelsius;
import static exemple.weatherapp.api.java.yupweather.utilities.Converts.simpleConvertDate;
import static exemple.weatherapp.api.java.yupweather.utilities.CustomAlerts.setToastAlert;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import exemple.weatherapp.api.java.yupweather.adapter.DailyForecastAdapter;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientConditions;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientDaily;
import exemple.weatherapp.api.java.yupweather.database.api.cliente.APIClientMain;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceConditions;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceDaily;
import exemple.weatherapp.api.java.yupweather.database.api.service.DataServiceMain;
import exemple.weatherapp.api.java.yupweather.database.local.SharedPreferenceLocation;
import exemple.weatherapp.api.java.yupweather.databinding.ActivityDaysForecastBinding;
import exemple.weatherapp.api.java.yupweather.model.ErrorResponse;
import exemple.weatherapp.api.java.yupweather.model.forecastdaily.DailyResponse;
import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyForecastActivity extends AppCompatActivity {

    private ActivityDaysForecastBinding binding;

    private TextView temperatureForecast;
    private TextView todayForecast;
    private ImageView todayIconForecast;

    private DataServiceConditions dataServiceConditions;
    private DataServiceMain dataServiceMain;
    private DataServiceDaily dataServiceDaily;

    private Call<WeatherConditionsDay> callDayConditions;
    private Call<WeatherMainDay> callDayMain;
    private Call<DailyResponse> callDailyResponse;

    private ErrorResponse errorResponse;
    private RecyclerView recyclerViewDaily;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaysForecastBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toolbar toolbarDays = binding.toolbarDaysForecast.toolbar;
        setSupportActionBar(toolbarDays);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
        toolbarDays.setNavigationIcon(drawable);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        components();

        String latitude = SharedPreferenceLocation.getLatitudeLocation(getBaseContext());
        String longitude = SharedPreferenceLocation.getLongitudeLocation(getBaseContext());

        dataServiceMain = APIClientMain.getMainInstance().create(DataServiceMain.class);
        dataServiceConditions = APIClientConditions.getConditionsInstance().create(DataServiceConditions.class);
        dataServiceDaily = APIClientDaily.getDailyInstance().create(DataServiceDaily.class);


        callDayMain = dataServiceMain.getDayWeatherMain(latitude, longitude, Constants.API_KEY);
        callDayConditions = dataServiceConditions.getDayWeatherConditions(latitude, longitude, Constants.API_KEY);
        callDailyResponse = dataServiceDaily.getDailyWeatherConditions(latitude, longitude, Constants.CMT_COUNT, Constants.API_KEY, Constants.UNITS_FORMAT);

        recyclerViewDaily = binding.recyclerViewDaysForecast;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setReverseLayout(false);
        recyclerViewDaily.setLayoutManager(linearLayoutManager);

        getDailyForecast();
        getMainWeather();
        getConditionWeather();

    }

    private void getDailyForecast() {
        callDailyResponse.clone().enqueue(new Callback<DailyResponse>() {
            @Override
            public void onResponse(Call<DailyResponse> call, Response<DailyResponse> response) {

                if (response.isSuccessful()){
                    DailyResponse dailyResponse = response.body();
                    assert dailyResponse != null;

                    DailyForecastAdapter dailyForecastAdapter = new DailyForecastAdapter(dailyResponse.getList(), DailyForecastActivity.this);
                    recyclerViewDaily.setAdapter(dailyForecastAdapter);


                    Log.d("Success T: ", "Response: " + new Gson().toJson(dailyResponse) );
                    Log.d("Success T: ", response.message() + " " + response.code());
                } else {
                    try {
                        errorResponse = APIClientDaily.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("DailyForecastActivity", "Error code T: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);

                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        setToastAlert(DailyForecastActivity.this, errorTextConvert);

                    }
                    Log.d("Error: ", response.message() + " " + response.code() + " " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<DailyResponse> call, Throwable t) {
                Log.d("Network error T: ", t.getMessage());
            }
        });
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
                        Log.e("DailyForecastActivity", "Error code C: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);

                        setToastAlert(DailyForecastActivity.this, errorTextConvert);

                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherConditionsDay> call, Throwable t) {
                Log.d("Network error: ", t.getMessage());
            }
        });
    }

    private void getWeatherConditionDayData(WeatherConditionsDay weatherConditionsDay) {
        int temp = (int) convertKevinToCelsius(weatherConditionsDay.getTemp());
        temperatureForecast.setText(new StringBuilder().append(temp).append(getString(R.string.special_character)));
    }

    private void getMainWeather() {

        callDayMain.clone().enqueue(new Callback<WeatherMainDay>() {
            @Override
            public void onResponse(Call<WeatherMainDay> call, Response<WeatherMainDay> response) {
                if (response.isSuccessful()) {
                    WeatherMainDay weatherMainDay = response.body();
                    assert weatherMainDay != null;
                    getMainWeatherData(weatherMainDay);
                    Log.d("Success D: ", "Response: " + new Gson().toJson(weatherMainDay));
                    Log.d("Success D: ", response.message() + " " + response.code());

                } else {
                    try {
                        errorResponse = APIClientMain.parseError(response);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (errorResponse != null) {
                        // Handle error response
                        Log.e("DailyForecastActivity", "Error code D: " + errorResponse.getCod() + ", message: " + errorResponse.getMessage());

                        String errorText = errorResponse.getMessage();
                        convertErrorMessageSize(errorText);
                        String errorTextConvert = convertErrorMessageSize(errorText);
                        //Toast.makeText(MainActivity.this, "Error code: " + errorResponse.getCod() + ", message: " + errorTextConvert, Toast.LENGTH_SHORT).show();

                        setToastAlert(DailyForecastActivity.this, errorTextConvert);

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

        String convertedDate = simpleConvertDate(weatherMainDay.getDt(), weatherMainDay.getTimezone());
        TextView toolbarDayDate = binding.toolbarDaysForecast.textViewToolbarDaysWeatherDate;
        toolbarDayDate.setText(convertedDate);

        TextView toolbarTitle = binding.toolbarDaysForecast.textViewToolbarTitleDaysWeather;
        toolbarTitle.setText(R.string.today_s_weather);

        todayForecast.setText(new StringBuilder()
                .append(weatherMainDay.getMain())
                .append(", ")
                .append(weatherMainDay.getDescription()));


        String mICON_URL = "https://openweathermap.org/img/wn/" + weatherMainDay.getIcon() + "@2x.png";

        switch (weatherMainDay.getIcon()) {

            case "01d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "01n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "02d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "02n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "03d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "03n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "04d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "04n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "09d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "09n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "10d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "10n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "11d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "11n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "13d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "13n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "50d":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            case "50n":
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
            default:
                Glide.with(this).load(mICON_URL).dontAnimate().error(R.drawable.ic_reload)
                        .into(todayIconForecast);
                break;
        }
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

    private void components() {
        todayForecast = binding.textViewDayForecastDataDays;
        todayIconForecast = binding.imageViewDayForecastIconDays;
        temperatureForecast = binding.textViewDayForecastTempDays;
    }
}