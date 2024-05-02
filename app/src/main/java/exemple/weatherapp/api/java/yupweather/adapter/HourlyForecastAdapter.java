package exemple.weatherapp.api.java.yupweather.adapter;


import android.annotation.SuppressLint;
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
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyData;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.Main;
import exemple.weatherapp.api.java.yupweather.model.forecasthourly.HourlyItem;

public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>{
    private final List<HourlyData> hourlyForecastList;
    private final Context context;

    public HourlyForecastAdapter(List<HourlyData> hoursForecastList, Context context) {
        this.hourlyForecastList = hoursForecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.row_view_hours_forecast, parent, false);
       return new ViewHolder(view);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindViewHolder(hourlyForecastList.get(position),  position);
    }

    @Override
    public int getItemCount() {
        return hourlyForecastList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewForecastDateTime;
        TextView textViewForecastTemperature;
        ImageView imageForecastIcon;
        ConstraintLayout constraintLayoutRowHoursForecast;

        public ViewHolder(View itemView) {
            super(itemView);

            constraintLayoutRowHoursForecast = itemView.findViewById(R.id.constraintLayout_rowViewHoursForecast);
            textViewForecastDateTime = itemView.findViewById(R.id.textView_rowViewHoursForecast);
            textViewForecastTemperature = itemView.findViewById(R.id.textView_rowViewTemperatureForecast);
            imageForecastIcon = itemView.findViewById(R.id.imageView_rowViewIconForecast);
        }

        public void bindViewHolder(HourlyData item, int position){

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-7"));
            Date date = new Date(item.getDt() * 1000);
            textViewForecastDateTime.setText(dateFormat.format(date) );


            Main main = item.getMain();
            textViewForecastTemperature.setText( String.format(context.getString(R.string._0f_C), main.getTemp()));
            HourlyItem weatherItem = item.getWeather().get(0);

            if(position > 0) {
                constraintLayoutRowHoursForecast.setBackgroundResource(R.drawable.ic_background_mini_round_corners);
                textViewForecastDateTime.setTextColor(context.getColor(R.color.color_1000));
                textViewForecastTemperature.setTextColor(context.getColor(R.color.color_1000));
            } else {
                constraintLayoutRowHoursForecast.setBackgroundResource(R.drawable.background_mini_blue);
                textViewForecastDateTime.setTextColor(context.getColor(R.color.color_200));
                textViewForecastTemperature.setTextColor(context.getColor(R.color.color_200));
            }

            String iconUrl = "https://openweathermap.org/img/wn/" + weatherItem.getIcon() + "@2x.png";
            switch (weatherItem.getIcon()) {

                case "01d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "01n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "02d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "02n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "03d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "03n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "04d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "04n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "09d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "09n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "10d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "10n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "11d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "11n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "13d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "13n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "50d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                case "50n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                default:
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageForecastIcon);
                    break;
            }
        }
    }
}
