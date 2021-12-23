package com.example.flightsapp.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.View.AdminPanelActivity;
import com.example.flightsapp.View.EditAirlineActivity;
import com.example.flightsapp.View.EditFlightActivity;
import com.example.flightsapp.View.EditFlightStatusActivity;
import com.example.flightsapp.View.EditRouteActivity;
import com.example.flightsapp.View.FlightDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.MyViewHolder> {
    private final Context context;
    private List<String> tableArray;
    private List<Flight> flightArray;
    private List<FlightStatus> flightStatusArray;
    private List<Airline> airlineArray;
    private List<Route> routeArray;
    private String status;


    public AdminAdapter(Context context) throws IllegalAccessException { // Адаптер, отвечающий за заполнение списка recyclerView
        this.context = context;
        tableArray = new ArrayList<>();
        flightArray = new ArrayList<>();
        flightStatusArray = new ArrayList<>();
        airlineArray = new ArrayList<>();
        routeArray = new ArrayList<>();
    }

    @NonNull
    @Override
    public AdminAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Создание
        View view = LayoutInflater.from(context).inflate(R.layout.departure_airports_list_layout, parent, false);
        return new AdminAdapter.MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdapter.MyViewHolder holder, int position) {
        holder.setData(tableArray.get(position), flightArray.size()==0 ? null : flightArray.get(position), flightStatusArray.size()==0 ? null : flightStatusArray.get(position), airlineArray.size()==0 ? null : airlineArray.get(position), routeArray.size()==0 ? null : routeArray.get(position), status);
    }

    @Override
    public int getItemCount() {
        return tableArray.size();
    }

    public String getTable(int position){
        return tableArray.get(position);
    }
    public Flight getFlight(int position){
        return flightArray.get(position);
    }
    public FlightStatus getFlightStatus(int position){
        return flightStatusArray.get(position);
    }
    public Route getRoute(int position){
        return routeArray.get(position);
    }
    public Airline getAirline(int position){
        return airlineArray.get(position);
    }

    // Класс, отвечающий за отдельный элемент
    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvTitle;
        private final Context context;
        private String localTable;
        private Flight flight;
        private FlightStatus flightStatus;
        private Airline airline;
        private Route route;
        private String status;


        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void setData(String table, Flight flight, FlightStatus flightStatus, Airline airline, Route route, String status) {
            localTable = table;
            tvTitle.setText(table); // заполнение заголовка для элемента списка
            this.status = status;
            this.flight = flight;
            this.flightStatus = flightStatus;
            this.airline = airline;
            this.route = route;
        }

        @Override
        public void onClick(View v) { // при нажатии на элемент передается ID заметки из бд и переходит на экран изменения
            switch (status) {
                case "flights": {
                    Intent i = new Intent(context, EditFlightActivity.class);
                    i.putExtra("airlineId", flight.getId_airline_pfk());
                    i.putExtra("routeId", flight.getId_route_pfk());
                    context.startActivity(i);
                    break;
                }
                case "airlines": {
                    Intent i = new Intent(context, EditAirlineActivity.class);
                    i.putExtra("airlineId", airline.getId_airline());
                    context.startActivity(i);
                    break;
                }
                case "flight_statuses": {
                    Intent i = new Intent(context, EditFlightStatusActivity.class);
                    i.putExtra("statusId", flightStatus.getId_flight_status());
                    context.startActivity(i);
                    break;
                }
                case "routes": {
                    Intent i = new Intent(context, EditRouteActivity.class);
                    i.putExtra("routeId", route.getId_route());
                    context.startActivity(i);
                    break;
                }
                case "tables":
                    switch (localTable) {
                        case "Flights": {
                            Intent i = new Intent(context, AdminPanelActivity.class);
                            i.putExtra("status", "flights");
                            context.startActivity(i);
                            break;
                        }
                        case "Airlines": {
                            Intent i = new Intent(context, AdminPanelActivity.class);
                            i.putExtra("status", "airlines");
                            context.startActivity(i);
                            break;
                        }
                        case "Routes": {
                            Intent i = new Intent(context, AdminPanelActivity.class);
                            i.putExtra("status", "routes");
                            context.startActivity(i);
                            break;
                        }
                        case "Flight Statuses": {
                            Intent i = new Intent(context, AdminPanelActivity.class);
                            i.putExtra("status", "flight_statuses");
                            context.startActivity(i);
                            break;
                        }
                    }
                    break;
            }
        }
    }

    public void clear(){
        tableArray.clear();
        flightArray.clear();
        flightStatusArray.clear();
        routeArray.clear();
        airlineArray.clear();
    }

    public void updateTableAdapter(List<String> newList) { // Обновление списка
        clear();
        tableArray = newList;
        status = "tables";
    }

    public void updateFlightAdapter(List<Flight> newList) { // Обновление списка
        clear();
        for (int i = 0; i<newList.size(); i++){
            tableArray.add(newList.get(i).getId_airline_pfk()+"'"+newList.get(i).getId_route_pfk());
        }
        flightArray = newList;
        status = "flights";
    }

    public void updateAirlineAdapter(List<Airline> newList) { // Обновление списка
        clear();
        for (int i = 0; i<newList.size(); i++){
            tableArray.add(newList.get(i).getName_airline());
        }
        airlineArray = newList;
        status = "airlines";
    }

    public void updateFlightStatusAdapter(List<FlightStatus> newList) { // Обновление списка
        clear();
        for (int i = 0; i<newList.size(); i++){
            tableArray.add(""+newList.get(i).getName_status());
        }
        flightStatusArray = newList;
        status = "flight_statuses";
    }

    public void updateRoutesAdapter(List<Route> newList) { // Обновление списка
        clear();
        for (int i = 0; i<newList.size(); i++){
            tableArray.add(newList.get(i).getAirport_departure()+" → "+newList.get(i).getAirport_destination());
        }
        routeArray = newList;
        status = "routes";
    }

}
