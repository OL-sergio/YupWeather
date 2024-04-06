package exemple.weatherapp.api.java.yupweather.database.api.service;


import exemple.weatherapp.api.java.yupweather.model.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://pro.openweathermap.org/data/2.5/weather?lat=37&lon=-122&appid=apikey
//https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=37&lon=-122&cnt=4&appid=apikey
//https://pro.openweathermap.org/data/2.5/forecast/hourly?lat=37&lon=-122&cnt=4&appid=apikey&units=metric

public interface DataServiceConditions {

    @GET(Constants.WEATHER)
    Call<WeatherConditionsDay> getDayWeatherConditions (
            @Query(Constants.LAT) String latitude,
            @Query(Constants.LON) String longitude,
            @Query(Constants.APPID) String apiKey
    );
}
