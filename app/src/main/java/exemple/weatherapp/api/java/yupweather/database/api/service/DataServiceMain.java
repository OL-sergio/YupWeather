package exemple.weatherapp.api.java.yupweather.database.api.service;

import exemple.weatherapp.api.java.yupweather.model.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataServiceMain {
    @GET(Constants.WEATHER)
    Call<WeatherMainDay> getDayWeatherMain (
            @Query(Constants.LAT) String latitude,
            @Query(Constants.LON) String longitude,
            @Query(Constants.APPID) String apiKey
    );
}
