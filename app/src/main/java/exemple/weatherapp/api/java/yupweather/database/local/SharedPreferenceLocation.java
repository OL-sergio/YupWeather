package exemple.weatherapp.api.java.yupweather.database.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class SharedPreferenceLocation extends Activity {

    private static final String location = "location_gps";
    private static final String _latitude = "latitude";
    private static final String _longitude = "longitude";

    private static SharedPreferences sharedPreferences;


    public static void setLocation(@NonNull Context context, String latitude , String longitude) {
        sharedPreferences = context.getSharedPreferences(location, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.putString(_latitude, longitude);
        editor.putString(_longitude, latitude);
        editor.apply();
    }

    public static String getLongitudeLocation(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(location, MODE_PRIVATE);
        return sharedPreferences.getString( _longitude,"" )  ;
    }

    public static String getLatitudeLocation(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(location, MODE_PRIVATE);
        return sharedPreferences.getString(_latitude, "")  ;
    }
}
