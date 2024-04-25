package exemple.weatherapp.api.java.yupweather.database.api.service;


import exemple.weatherapp.api.java.yupweather.model.forescastdaily.DailyResponse;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//https://pro.openweathermap.org/data/2.5/forecast/daily?lat=37&lon=-122&cnt=4&appid=apikey&units=metric
public interface DataServiceDaily {
    @GET(Constants.DAILY)
    Call<DailyResponse> getDailyWeatherConditions (
            @Query(Constants.LAT) String latitude,
            @Query(Constants.LON) String longitude,
            @Query(Constants.CNT) int count,
            @Query(Constants.APPID) String apiKey,
            @Query(Constants.UNITS) String units
    );
}
