package exemple.weatherapp.api.java.yupweather.helper;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import exemple.weatherapp.api.java.yupweather.model.forecastdaily.DailyData;
import exemple.weatherapp.api.java.yupweather.model.forecastdaily.FeelsLike;
import exemple.weatherapp.api.java.yupweather.model.forecastdaily.DailyResponse;

public class DeserializerDaily implements JsonDeserializer<DailyResponse> {
    @Override
    public DailyResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String lon = jsonObject.get("city").getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString();
        String lat = jsonObject.get("city").getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString();
        Log.d(TAG, "lat: " + lon + " " + "lat: " + lat );

        String name = jsonObject.get("city").getAsJsonObject().get("name").getAsString();
        String country = jsonObject.get("city").getAsJsonObject().get("country").getAsString();
        String population = jsonObject.get("city").getAsJsonObject().get("population").getAsString();
        int timezone = jsonObject.get("city").getAsJsonObject().get("timezone").getAsInt();
        String cod = jsonObject.get("cod").getAsString();
        String message = jsonObject.get("message").getAsString();
        String cnt = jsonObject.get("cnt").getAsString();


        JsonArray listArray = jsonObject.getAsJsonArray("list");
        List<DailyData> listItems = new ArrayList<>();
        for ( JsonElement dailyElement : listArray ) {

            JsonObject listObj = dailyElement.getAsJsonObject();
            int dt = listObj.get("dt").getAsInt();

            int sunrise = listObj.get("sunrise").getAsInt();
            int sunset = listObj.get("sunset").getAsInt();

            double day = listObj.get("temp").getAsJsonObject().get("day").getAsDouble();
            double min = listObj.get("temp").getAsJsonObject().get("min").getAsInt();
            double max = listObj.get("temp").getAsJsonObject().get("max").getAsInt();
            double night = listObj.get("temp").getAsJsonObject().get("night").getAsInt();
            double eve = listObj.get("temp").getAsJsonObject().get("eve").getAsInt();
            double morn = listObj.get("temp").getAsJsonObject().get("morn").getAsInt();

            int pressure = listObj.get("pressure").getAsInt();
            int humidity = listObj.get("humidity").getAsInt();
            double speed = listObj.get("speed").getAsDouble();
            int deg = listObj.get("deg").getAsInt();
            double gust = listObj.get("clouds").getAsDouble();
            int clouds = listObj.get("clouds").getAsInt();
            String pop = listObj.get("pop").getAsString();


            int id = listObj.get("weather").getAsJsonArray().get(0)
                    .getAsJsonObject().get("id").getAsInt();

            String main = listObj.get("weather").getAsJsonArray().get(0)
                    .getAsJsonObject().get("main").getAsString();

            String description = listObj.get("weather").getAsJsonArray().get(0)
                    .getAsJsonObject().get("description").getAsString();

            String icon = listObj.get("weather").getAsJsonArray().get(0)
                    .getAsJsonObject().get("icon").getAsString();

            FeelsLike feelsLike = context.deserialize(listObj.get("feels_like"), FeelsLike.class);

            DailyData dailyData = new DailyData();
            dailyData.setDt(dt);
            dailyData.setSunrise(sunrise);
            dailyData.setSunset(sunset);
            dailyData.setDay(day);
            dailyData.setMin(min);
            dailyData.setMax(max);
            dailyData.setNight(night);
            dailyData.setEve(eve);
            dailyData.setMorn(morn);
            dailyData.setPressure(pressure);
            dailyData.setHumidity(humidity);
            dailyData.setSpeed(speed);
            dailyData.setDay(deg);
            dailyData.setGust(gust);
            dailyData.setClouds(clouds);
            dailyData.setPop(pop);
            dailyData.setId(id);
            dailyData.setMain(main);
            dailyData.setDescription(description);
            dailyData.setIcon(icon);
            dailyData.setFeelsLike(feelsLike);
            listItems.add(dailyData);

        }

        DailyResponse dailyResponse = new DailyResponse();
        dailyResponse.setTimezone(timezone);
        dailyResponse.setCountry(country);
        dailyResponse.setPopulation(population);
        dailyResponse.setCod(cod);
        dailyResponse.setName(name);
        dailyResponse.setMessage(message);
        dailyResponse.setCod(cnt);
        dailyResponse.setList(listItems);
        return dailyResponse;
    }
}
