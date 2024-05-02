package exemple.weatherapp.api.java.yupweather.database.api.service;

import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//https://pro.openweathermap.org/data/2.5/weather?lat=37&lon=-122&appid=apikey

public interface DataServiceMain {
    @GET(Constants.WEATHER)
    Call<WeatherMainDay> getDayWeatherMain (
            @Query(Constants.LAT) String latitude,
            @Query(Constants.LON) String longitude,
            @Query(Constants.APPID) String apiKey
    );
}
