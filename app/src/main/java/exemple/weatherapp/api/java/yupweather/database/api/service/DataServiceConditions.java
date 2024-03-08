package exemple.weatherapp.api.java.yupweather.database.api.service;


import exemple.weatherapp.api.java.yupweather.model.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=apikey

public interface DataServiceConditions {

    @GET(Constants.WEATHER)
    Call<WeatherConditionsDay> getDayWeatherConditions (
            @Query(Constants.LAT) String latitude,
            @Query(Constants.LON) String longitude,
            @Query(Constants.APPID) String apiKey
    );

}
