package exemple.weatherapp.api.java.yupweather.database.api;


import com.google.gson.GsonBuilder;

import exemple.weatherapp.api.java.yupweather.helper.DeserializerMain;
import exemple.weatherapp.api.java.yupweather.model.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClientMain {

private static Retrofit retrofit = null;

public static Retrofit getMainInstance(){
        if ( retrofit == null ) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(WeatherMainDay.class, new DeserializerMain() );

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();

        }
        return retrofit;
    }

}


