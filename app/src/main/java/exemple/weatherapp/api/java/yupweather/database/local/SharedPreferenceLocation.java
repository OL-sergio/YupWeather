package exemple.weatherapp.api.java.yupweather.database.local;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import exemple.weatherapp.api.java.yupweather.utilities.Constants;

public class SharedPreferenceLocation extends Activity {

    private static SharedPreferences sharedPreferences;

    public static void setLocation(@NonNull Context context, String latitude , String longitude) {
        sharedPreferences = context.getSharedPreferences(Constants.LOCATION, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(Constants.LATITUDE, latitude);
        editor.putString(Constants.LONGITUDE, longitude);
        editor.apply();
    }

    public static String getLongitudeLocation(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.LOCATION, MODE_PRIVATE);
        return sharedPreferences.getString( Constants.LONGITUDE,"" )  ;
    }

    public static String getLatitudeLocation(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.LOCATION, MODE_PRIVATE);
        return sharedPreferences.getString(Constants.LATITUDE, "")  ;
    }
}
