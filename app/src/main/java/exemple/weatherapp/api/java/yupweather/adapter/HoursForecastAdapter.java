package exemple.weatherapp.api.java.yupweather.adapter;


import static java.lang.Math.toIntExact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import exemple.weatherapp.api.java.yupweather.R;
import exemple.weatherapp.api.java.yupweather.model.WeatherData;

public class HoursForecastAdapter extends RecyclerView.Adapter<HoursForecastAdapter.MyViewHolder>{
    private List<WeatherData> hoursForecastList;
    private final Context context;

    public HoursForecastAdapter(List<WeatherData> hoursForecastList, Context context) {
        this.hoursForecastList = hoursForecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hours_forecast, parent, false);
       return new MyViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherData weatherData = hoursForecastList.get(position);

        holder.textViewForecastDateTime.setText( Integer.toString(toIntExact(weatherData.getDt())));
        holder.textViewForecastTemperature.setText( weatherData.getMain().getTemp() + "°C");
    }

    @Override
    public int getItemCount() {
        return hoursForecastList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewForecastDateTime;
        TextView textViewForecastTemperature;
        ImageView imageForecastIcon;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewForecastDateTime = itemView.findViewById(R.id.textView_rowViewHoursForecast);
            textViewForecastTemperature = itemView.findViewById(R.id.textView_rowViewTemperatureForecast);
            imageForecastIcon = itemView.findViewById(R.id.imageView_rowViewIconForecast);
        }
    }
}
