package com.example.flightsapp.View.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flightsapp.Data.Mssql.Aircraft;
import com.example.flightsapp.R;

import java.util.List;

public class FlightsAircraftSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Aircraft> aircrafts;
    // String type;

    public FlightsAircraftSpinnerAdapter(Context context, List<Aircraft> routes){ //todo how to deal with generics? spam more classes, add String param to check what I get here, or is there something else?
        this.context = context;
        this.aircrafts = routes;
    }
    @Override
    public int getCount() {
        return aircrafts != null ? aircrafts.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return aircrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPosition(Aircraft o){
        return aircrafts.indexOf(o);
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View view, ViewGroup parent){
        View rootView = LayoutInflater.from(context).inflate(R.layout.flights_list_layout, parent, false);

        TextView tvFlight = rootView.findViewById(R.id.tvFlight);
        tvFlight.setText(aircrafts.get(position).toString());

        return rootView;
    }
}
