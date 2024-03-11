package exemple.weatherapp.api.java.yupweather.utilities;

import android.os.Build;
import android.util.Log;

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
    public static String convertHour(String hours) {
        return DateTimeFormatter.ofPattern("HH:mm:ss" )
                .withZone(ZoneId.of("UTC"))
                .toFormat().format(Instant.ofEpochMilli(Long.parseLong(hours)));
    }

    public static String convertErrorMessageSize(String message) {

        String[] paragraphs = message.split("Please see");

        if (paragraphs.length > 1) {
            paragraphs[1] = ""; // delete the first paragraph
        }

        String newMessage = String.join("", paragraphs);

        Log.d("New Message", "The new message is: " + newMessage);
        return newMessage;

    }

}
