package exemple.weatherapp.api.java.yupweather.database.api;

import com.google.gson.GsonBuilder;

import exemple.weatherapp.api.java.yupweather.helper.DeserializerConditions;
import exemple.weatherapp.api.java.yupweather.model.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientConditions {
    private static Retrofit retrofit = null;

    public static Retrofit getConditionsInstance() {
        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(WeatherConditionsDay.class, new DeserializerConditions());

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();

        }
        return retrofit;
    }
}
