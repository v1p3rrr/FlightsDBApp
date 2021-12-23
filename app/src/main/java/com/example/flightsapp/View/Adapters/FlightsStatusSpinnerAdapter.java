package com.example.flightsapp.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.R;

import java.util.List;

public class FlightsStatusSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<FlightStatus> flightStatuses;
    String type;

    public FlightsStatusSpinnerAdapter(Context context, List<FlightStatus> routes){
        this.context = context;
        this.flightStatuses = routes;
    }
    @Override
    public int getCount() {
        return flightStatuses != null ? flightStatuses.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return flightStatuses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(FlightStatus o){
        return flightStatuses.indexOf(o);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent){
        View rootView = LayoutInflater.from(context).inflate(R.layout.flights_list_layout, parent, false);

        TextView tvFlight = rootView.findViewById(R.id.tvFlight);
        tvFlight.setText(flightStatuses.get(position).toString());

        return rootView;
    }
}
