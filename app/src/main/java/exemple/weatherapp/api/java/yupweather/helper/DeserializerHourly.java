package exemple.weatherapp.api.java.yupweather.helper;


import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import exemple.weatherapp.api.java.yupweather.model.forecasthourly.WeatherData;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Clouds;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Main;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Sys;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.WeatherResponse;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.WeatherItem;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Wind;

public class DeserializerHourly implements JsonDeserializer<WeatherResponse>{

    @Override
    public WeatherResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        int cod = jsonObject.get("cod").getAsInt();
        int message = jsonObject.get("message").getAsInt();
        int cnt = jsonObject.get("cnt").getAsInt();

        JsonArray listArray = jsonObject.getAsJsonArray("list");
        List<WeatherData> list = new ArrayList<>();

        for (JsonElement element : listArray) {

            JsonObject listObj = element.getAsJsonObject();
            long dt = listObj.get("dt").getAsLong();

            Main main = context.deserialize(listObj.get("main"), Main.class);

            JsonArray weatherArray = listObj.getAsJsonArray("weather");
            List<WeatherItem> weatherItems = new ArrayList<>();

            for (JsonElement weatherElement : weatherArray) {
                JsonObject weatherObj = weatherElement.getAsJsonObject();
                WeatherItem weatherItem = context.deserialize(weatherObj, WeatherItem.class);
                weatherItems.add(weatherItem);
            }

            Clouds clouds = context.deserialize(listObj.get("clouds"), Clouds.class);
            Wind wind = context.deserialize(listObj.get("wind"), Wind.class);

            int visibility = listObj.get("visibility").getAsInt();
            double pop = listObj.get("pop").getAsDouble();

            Sys sys = context.deserialize(listObj.get("sys"), Sys.class);
            String dtTxt = listObj.get("dt_txt").getAsString();

            WeatherData weatherData = new WeatherData();
            weatherData.setDt(dt);
            weatherData.setMain(main);
            weatherData.setWeather(weatherItems);
            weatherData.setClouds(clouds);
            weatherData.setWind(wind);
            weatherData.setVisibility(visibility);
            weatherData.setPop(pop);
            weatherData.setSys(sys);
            weatherData.setDtTxt(dtTxt);
            list.add(weatherData);

        }

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCod(cod);
        weatherResponse.setMessage(message);
        weatherResponse.setCnt(cnt);
        weatherResponse.setList(list);
        return weatherResponse;

    }

}
