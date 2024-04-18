package exemple.weatherapp.api.java.yupweather.adapter;

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

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.MyViewHolder> {

    private List<String> daysForecastList;
    private final Context context;

    public DailyForecastAdapter(List<String> daysForecastList, Context context) {
        this.daysForecastList = daysForecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_view_days_forecast, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return daysForecastList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewDaysForecastIcon;
        TextView textViewDaysForecastDate;
        TextView textViewDaysForecastDay;
        TextView textViewDaysForecastTemperature;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDaysForecastIcon = itemView.findViewById(R.id.imageView_rowViewDaysIconForecast);
            textViewDaysForecastDay = itemView.findViewById(R.id.textView_rowViewDaysTempForecast);
            textViewDaysForecastDate = itemView.findViewById(R.id.textView_rowViewDaysDateForecast);
            textViewDaysForecastTemperature = itemView.findViewById(R.id.textView_rowViewTemperatureForecast);

        }
    }
}
