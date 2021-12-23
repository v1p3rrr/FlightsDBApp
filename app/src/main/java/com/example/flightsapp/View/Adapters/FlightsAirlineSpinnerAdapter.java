package com.example.flightsapp.View.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.flightsapp.Data.Mssql.Aircraft;
import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;

import java.util.List;

public class FlightsAirlineSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Airline> airlines;
    String type;

    public FlightsAirlineSpinnerAdapter(Context context, List<Airline> airlines){
        this.context = context;
        this.airlines = airlines;
    }
    @Override
    public int getCount() {
        return airlines != null ? airlines.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return airlines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(Airline o){
        return airlines.indexOf(o);
    }

    public View getView(int position, View view, ViewGroup parent){
        View rootView = LayoutInflater.from(context).inflate(R.layout.flights_list_layout, parent, false);

        TextView tvFlight = rootView.findViewById(R.id.tvFlight);
        tvFlight.setText(airlines.get(position).getName_airline());

        return rootView;
    }
}
