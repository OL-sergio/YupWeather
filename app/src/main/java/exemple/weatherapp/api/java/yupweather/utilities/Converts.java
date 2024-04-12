package exemple.weatherapp.api.java.yupweather.utilities;

import static java.util.Locale.US;

import android.annotation.SuppressLint;
import android.icu.text.TimeZoneFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import exemple.weatherapp.api.java.yupweather.model.WeatherMainDay;


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


    @SuppressLint("SimpleDateFormat")
    public static String simpleConvertDate(String date) {
     return new SimpleDateFormat( "EEEE, MMMM, dd, yyyy").format(new Date());
    }



    @SuppressLint("SimpleDateFormat")
    public static String simpleConvertHourSeconds(String hours, long timeZone) {


        ZoneOffset offset = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            offset = ZoneOffset.ofTotalSeconds((int) timeZone);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss ", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT" +  offset));
        Date date = new Date();
        return dateFormat.format(date);
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
