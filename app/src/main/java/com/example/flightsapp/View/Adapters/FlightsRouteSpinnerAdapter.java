package com.example.flightsapp.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;

import java.util.List;

public class FlightsRouteSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Route> routes;
    String type;

    public FlightsRouteSpinnerAdapter(Context context, List<Route> routes){
        this.context = context;
        this.routes = routes;
    }
    @Override
    public int getCount() {
        return routes != null ? routes.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return routes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(Route o){
        return routes.indexOf(o);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent){
        View rootView = LayoutInflater.from(context).inflate(R.layout.flights_list_layout, parent, false);

        TextView tvFlight = rootView.findViewById(R.id.tvFlight);
        tvFlight.setText(routes.get(position).getAirport_departure() + " → " + routes.get(position).getAirport_destination());

        return rootView;
    }
}
