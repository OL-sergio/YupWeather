package exemple.weatherapp.api.java.yupweather.helper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import exemple.weatherapp.api.java.yupweather.model.forecastday.WeatherMainDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;

public class DeserializerMain implements JsonDeserializer<WeatherMainDay> {
    @Override
    public WeatherMainDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String main = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.MAIN).getAsString();

        String description = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.DESCRIPTION).getAsString();

        String icon = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.ICON).getAsString();

        String dateTime = json.getAsJsonObject().get(Constants.DATE_TIME).getAsString();

        String name = json.getAsJsonObject().get(Constants.NAME).getAsString();

        String country = json.getAsJsonObject().get(Constants.SYS).getAsJsonObject().get(Constants.COUNTRY).getAsString();

        int timezone = json.getAsJsonObject().get(Constants.TIME_ZONE).getAsInt();

        return new WeatherMainDay(
                main,
                description,
                icon,
                dateTime,
                name,
                country,
                timezone
        );
    }
}
