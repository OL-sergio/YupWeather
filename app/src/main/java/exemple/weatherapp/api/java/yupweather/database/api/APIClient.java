package exemple.weatherapp.api.java.yupweather.database.api;


import com.google.gson.GsonBuilder;

import exemple.weatherapp.api.java.yupweather.helper.DeserializerDay;
import exemple.weatherapp.api.java.yupweather.model.WeatherDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {

private static Retrofit retrofit = null;

public static Retrofit getInstance(){
        if ( retrofit == null ) {

           GsonBuilder builder = new GsonBuilder();
           builder.registerTypeAdapter( WeatherDay.class, new DeserializerDay() );

            retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();

        }
        return retrofit;
    }
}

