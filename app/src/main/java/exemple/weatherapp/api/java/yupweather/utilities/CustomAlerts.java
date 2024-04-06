package exemple.weatherapp.api.java.yupweather.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import exemple.weatherapp.api.java.yupweather.databinding.CustomToastBinding;

public class CustomAlerts extends AppCompatActivity {


    private static AlertDialog alertDialog;
    private static CustomToastBinding binding;

    public static void setGpsSettings(Context context, String title, String message){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog = alert.create();
        alertDialog.show();
    }

    public static void setNetworkSettings(Context context, String title, String message){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog = alert.create();
        alertDialog.show();
    }



    public static void setToastAlert(Context context, String message) {
        // Inflate the custom_toast.xml layout
        CustomToastBinding binding = CustomToastBinding.inflate(LayoutInflater.from(context));
        View view = binding.getRoot();

        // Initialize the TextView and set the message
        TextView text = binding.textViewCustomToast;
        text.setText(message);

        // Initialize the Toast
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, -125);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }


}
