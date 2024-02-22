package exemple.weatherapp.api.java.yupweather.helper;

import com.google.gson.JsonDeserializationContext;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import exemple.weatherapp.api.java.yupweather.model.WeatherDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;


public class DeserializerDay implements JsonDeserializer<WeatherDay> {


    @Override
    public WeatherDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        int id = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.ID).getAsInt();

        String main = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.MAIN).getAsString();

        String description = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.DESCRIPTION).getAsString();

        String icon = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.ICON).getAsString();

        String temp = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.TEMP).getAsString();

        String humidity = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.HUMIDITY).getAsString();

        String pressure = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.PRESSURE).getAsString();

        String visibility = json.getAsJsonObject()
                .get(Constants.VISIBILITY).getAsString();

        String windSpeed = json.getAsJsonObject().get(Constants.WIND)
                .getAsJsonObject().get(Constants.SPEED).getAsString();

        String country = json.getAsJsonObject().get(Constants.SYS)
                .getAsJsonObject().get(Constants.COUNTRY).getAsString();

        String name = json.getAsJsonObject()
                .get(Constants.NAME)
                .getAsString();

        return new WeatherDay(
                id,
                main,
                description,
                icon,
                temp,
                pressure,
                visibility,
                humidity,
                windSpeed,
                country,
                name
        );
    }
}
