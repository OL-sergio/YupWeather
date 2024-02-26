package exemple.weatherapp.api.java.yupweather.utilities;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public final class Converts {

    public static float convertMeterToKilometer(float meter) {
        return (float) (meter * 0.001);
    }

    public static float convertWindSpeedMeterToKilometer(float meter) {
        return (float) (meter * 3.6);
    }

    public static float convertKevinToCelsius(float celsius) {
        return (float) (celsius - 273.15);
    }

    public static String convertDoubleToInteger(double location) {
        return Integer.toString((int) Math.round(location));
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertDate(String date) {

     return DateTimeFormatter.ofPattern("EEEE, MMMM, dd, yyyy" )
                        .withZone(ZoneId.of("UTC"))
                .toFormat().format(Instant.ofEpochMilli(Long.parseLong(date)));

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String convertHour(String date) {

        return DateTimeFormatter.ofPattern("h:mm:ss a" )
                .withZone(ZoneId.of("UTC"))
                .toFormat().format(Instant.ofEpochMilli(Long.parseLong(date)));

    }

}
