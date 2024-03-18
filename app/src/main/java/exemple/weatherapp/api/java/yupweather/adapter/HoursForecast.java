package exemple.weatherapp.api.java.yupweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import exemple.weatherapp.api.java.yupweather.databinding.RowHoursForecastBinding;

public class HoursForecast extends RecyclerView.Adapter<HoursForecast.MyViewHolder>{
    private final List<String> hoursForecastList;
    private final Context context;


    public HoursForecast (List<String> hoursForecastList , Context context) {
        this.context = context;
        this.hoursForecastList = hoursForecastList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowHoursForecastBinding binding = RowHoursForecastBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(hoursForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return hoursForecastList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final RowHoursForecastBinding binding;

        public MyViewHolder(RowHoursForecastBinding binding) {
            super(binding.getRoot() );
            this.binding = binding;
        }

        public void bind(String item) {
            // binding.imageViewRowViewIconForecast.setImageIcon(Icon.createWithBitmap(icon));
            binding.textViewRowViewHoursForecast.setText(item);
            binding.textViewRowViewTemperatureForecast.setText(item);
        }
    }
}
