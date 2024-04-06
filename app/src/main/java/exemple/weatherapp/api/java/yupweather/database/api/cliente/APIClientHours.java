package exemple.weatherapp.api.java.yupweather.database.api.cliente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import exemple.weatherapp.api.java.yupweather.helper.DeserializerHours;
import exemple.weatherapp.api.java.yupweather.model.ErrorResponse;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.WeatherConditionsHours;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClientHours {
    private static Retrofit retrofit = null;

    public static Retrofit getHoursInstance() {

        if (retrofit == null) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(  WeatherConditionsHours.class, new DeserializerHours());

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_HOURS)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();

        }
        return retrofit;
    }

    public static ErrorResponse parseError(Response response) throws IOException {

        Gson gson = new Gson();
        if (response.errorBody() == null) throw new AssertionError();

        return gson.fromJson(response.errorBody().string(), ErrorResponse.class);

    }
}
