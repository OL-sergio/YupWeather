package exemple.weatherapp.api.java.yupweather.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import exemple.weatherapp.api.java.yupweather.R;
import exemple.weatherapp.api.java.yupweather.model.WeatherData;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Main;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.WeatherItem;

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7"));
        Date date = new Date(weatherData.getDt() * 1000);
        holder.textViewForecastDateTime.setText(dateFormat.format(date) );

        Main main = weatherData.getMain();
        holder.textViewForecastTemperature.setText( String.format(context.getString(R.string._0f_c), main.getTemp()));
        WeatherItem weatherItem = weatherData.getWeather().get(0);

        if(position > 0) {
            holder.constraintLayoutRowHoursForecast.setBackgroundResource(R.drawable.ic_background_mini_round_corners);
            holder.textViewForecastDateTime.setTextColor(context.getColor(R.color.color_1000));
            holder.textViewForecastTemperature.setTextColor(context.getColor(R.color.color_1000));
        } else {
            holder.constraintLayoutRowHoursForecast.setBackgroundResource(R.drawable.background_mini_blue);
            holder.textViewForecastDateTime.setTextColor(context.getColor(R.color.color_200));
            holder.textViewForecastTemperature.setTextColor(context.getColor(R.color.color_200));
        }

        String iconUrl = "https://openweathermap.org/img/wn/" + weatherItem.getIcon() + "@2x.png";
        switch (weatherItem.getIcon()) {

            case "01d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "01n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "02d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "02n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "03d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "03n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "04d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "04n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "09d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "09n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "10d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "10n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "11d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "11n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "13d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "13n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "50d":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            case "50n":
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
            default:
                Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_alert)
                        .into(holder.imageForecastIcon);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return hoursForecastList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView  recyclerViewForecastMini;
        TextView textViewForecastDateTime;
        TextView textViewForecastTemperature;
        ImageView imageForecastIcon;
        ConstraintLayout constraintLayoutRowHoursForecast;

        public MyViewHolder(View itemView) {
            super(itemView);

            constraintLayoutRowHoursForecast = itemView.findViewById(R.id.constraintLayout_rowViewHoursForecast);
            textViewForecastDateTime = itemView.findViewById(R.id.textView_rowViewHoursForecast);
            textViewForecastTemperature = itemView.findViewById(R.id.textView_rowViewTemperatureForecast);
            imageForecastIcon = itemView.findViewById(R.id.imageView_rowViewIconForecast);
        }
    }
}
