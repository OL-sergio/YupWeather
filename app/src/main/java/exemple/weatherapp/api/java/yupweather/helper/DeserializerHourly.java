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

import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyData;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Clouds;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyResponse;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Main;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Sys;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyItem;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Wind;

public class DeserializerHourly implements JsonDeserializer<HourlyResponse>{

    @Override
    public HourlyResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        int cod = jsonObject.get("cod").getAsInt();
        int message = jsonObject.get("message").getAsInt();
        int cnt = jsonObject.get("cnt").getAsInt();

        JsonArray listArray = jsonObject.getAsJsonArray("list");
        List<HourlyData> list = new ArrayList<>();

        for (JsonElement element : listArray) {

            JsonObject listObj = element.getAsJsonObject();
            long dt = listObj.get("dt").getAsLong();

            Main main = context.deserialize(listObj.get("main"), Main.class);

            JsonArray weatherArray = listObj.getAsJsonArray("weather");
            List<HourlyItem> weatherItems = new ArrayList<>();

            for (JsonElement weatherElement : weatherArray) {
                JsonObject weatherObj = weatherElement.getAsJsonObject();
                HourlyItem weatherItem = context.deserialize(weatherObj, HourlyItem.class);
                weatherItems.add(weatherItem);
            }

            Clouds clouds = context.deserialize(listObj.get("clouds"), Clouds.class);
            Wind wind = context.deserialize(listObj.get("wind"), Wind.class);

            int visibility = listObj.get("visibility").getAsInt();
            double pop = listObj.get("pop").getAsDouble();

            Sys sys = context.deserialize(listObj.get("sys"), Sys.class);
            String dtTxt = listObj.get("dt_txt").getAsString();

            HourlyData hourlyData = new HourlyData();
            hourlyData.setDt(dt);
            hourlyData.setMain(main);
            hourlyData.setWeather(weatherItems);
            hourlyData.setClouds(clouds);
            hourlyData.setWind(wind);
            hourlyData.setVisibility(visibility);
            hourlyData.setPop(pop);
            hourlyData.setSys(sys);
            hourlyData.setDtTxt(dtTxt);
            list.add(hourlyData);

        }

        HourlyResponse hourlyResponse = new HourlyResponse();
        hourlyResponse.setCod(cod);
        hourlyResponse.setMessage(message);
        hourlyResponse.setCnt(cnt);
        hourlyResponse.setList(list);
        return hourlyResponse;

    }

}
