package com.example.flightsapp.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.R;
import com.example.flightsapp.View.FlightDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private final Context context;
    private List<FlightDetails> mainArray;


    public MainAdapter(Context context) throws IllegalAccessException { // Адаптер, отвечающий за заполнение списка recyclerView
        this.context = context;
        mainArray = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание
        View view = LayoutInflater.from(context).inflate(R.layout.departure_airports_list_layout, parent, false);
        return new MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { // Заполнение
        holder.setData(mainArray.get(position));
    }

    @Override
    public int getItemCount() {
        return mainArray.size();
    }

    public FlightDetails getFlight(int position){
        return mainArray.get(position);
    }

    // Класс, отвечающий за отдельный элемент
    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitle;
        private final Context context;
        private FlightDetails localFlight;


        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void setData(FlightDetails flight) {
            localFlight = flight;
            tvTitle.setText(flight.getId_airline_pfk()+flight.getId_route_pfk() + " | " + flight.getTime_departure() + " — " + flight.getTime_arrival() + " | " + flight.getName_status()); // заполнение заголовка для элемента списка
        }

        @Override
        public void onClick(View v) { // при нажатии на элемент передается ID заметки из бд и переходит на экран изменения
            Intent i = new Intent(context, FlightDetailsActivity.class);
            i.putExtra("airlineId", localFlight.getId_airline_pfk());
            i.putExtra("routeId", localFlight.getId_route_pfk());
            context.startActivity(i);
        }
    }

    public void updateAdapter(List<FlightDetails> newList) { // Обновление списка
        mainArray = newList;
        notifyDataSetChanged();
    }
}