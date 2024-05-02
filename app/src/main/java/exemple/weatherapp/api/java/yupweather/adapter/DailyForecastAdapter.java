package exemple.weatherapp.api.java.yupweather.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import exemple.weatherapp.api.java.yupweather.R;
import exemple.weatherapp.api.java.yupweather.model.forecastdaily.DailyData;
import exemple.weatherapp.api.java.yupweather.model.forecastdaily.FeelsLike;

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {

    private final List<DailyData> dailyForecastList;
    private final Context context;

    public DailyForecastAdapter(List<DailyData> daysForecastList, Context context) {
        this.dailyForecastList = daysForecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view_days_forecast, parent, false);
        return new ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewDailyForecastIcon;
        TextView textViewDailyForecastDate;
        TextView textViewDailyForecastDay;
        TextView textViewDailyForecastTemperature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDailyForecastIcon = itemView.findViewById(R.id.imageView_rowViewDaysIconForecast);
            textViewDailyForecastTemperature = itemView.findViewById(R.id.textView_rowViewDaysTempForecast);
            textViewDailyForecastDate = itemView.findViewById(R.id.textView_rowViewDaysDateForecast);
            textViewDailyForecastDay = itemView.findViewById(R.id.textView_rowViewDaysTitleForecast);

        }

        @SuppressLint("DefaultLocale")
        public void bindViewHolder(DailyData item){

            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            Date day = new Date(item.getDt() * 1000L);
            textViewDailyForecastDay.setText( dayFormat.format(day) );

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault());
            Date date = new Date(item.getDt() * 1000L);
            textViewDailyForecastDate.setText(dateFormat.format(date) );

            FeelsLike dataFeelsLike = item.getFeelsLike();
            textViewDailyForecastTemperature.setText(String.format("%.0fº", dataFeelsLike.getDay()));


            String iconUrl = "https://openweathermap.org/img/wn/" + item.getIcon() + "@2x.png";
            switch (item.getIcon()) {

                case "01d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
               case "01n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "02d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "02n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "03d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "03n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "04d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "04n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "09d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "09n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "10d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "10n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "11d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "11n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "13d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "13n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "50d":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                case "50n":
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                default:
                    Glide.with(context).load(iconUrl).dontAnimate().error(R.drawable.ic_reload)
                            .into(imageViewDailyForecastIcon);
                    break;
            }
        }
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindViewHolder(dailyForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return dailyForecastList.size();
    }

}
