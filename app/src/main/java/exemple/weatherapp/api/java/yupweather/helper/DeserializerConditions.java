package exemple.weatherapp.api.java.yupweather.helper;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import exemple.weatherapp.api.java.yupweather.model.WeatherConditionsDay;
import exemple.weatherapp.api.java.yupweather.utilities.Constants;


public class DeserializerConditions implements JsonDeserializer<WeatherConditionsDay> {


    @Override
    public WeatherConditionsDay deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        int id = json.getAsJsonObject().get(Constants.WEATHER).getAsJsonArray().get(0)
                .getAsJsonObject().get(Constants.ID).getAsInt();

        float temp = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.TEMP).getAsFloat();

        String humidity = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.HUMIDITY).getAsString();

        String pressure = json.getAsJsonObject().get(Constants.MAIN)
                .getAsJsonObject().get(Constants.PRESSURE).getAsString();

        String visibility = json.getAsJsonObject()
                .get(Constants.VISIBILITY).getAsString();

        float windSpeed = json.getAsJsonObject().get(Constants.WIND)
                .getAsJsonObject().get(Constants.SPEED).getAsFloat();

        return new WeatherConditionsDay(
                id,
                temp,
                pressure,
                visibility,
                humidity,
                windSpeed
        );
    }
}
