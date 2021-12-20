package com.example.flightsapp.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.ViewModel.EditFlightStatusViewModel;
import com.example.flightsapp.ViewModel.EditRouteViewModel;

public class EditRouteActivity extends AppCompatActivity {

    private EditText editRouteId;
    private EditText editAirportDeparture;
    private EditText editAirportDestination;
    private EditText editAirportTransfer;
    private int routeId;
    private EditRouteViewModel editRouteViewModel;
    private Route route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_route);
        getIntentMain();
        init();
    }

    public void init() {
        editRouteId = findViewById(R.id.editRouteId);
        editAirportDeparture = findViewById(R.id.editAirportDeparture);
        editAirportDestination = findViewById(R.id.editAirportDestination);
        editAirportTransfer = findViewById(R.id.editAirportTransfer);

        this.editRouteViewModel = new ViewModelProvider(this).get(EditRouteViewModel.class);

        if (routeId != -1){
            route = editRouteViewModel.getRouteFromDb(routeId);
        }
        if (route != null){
            editRouteId.setText(""+route.getId_route());
            editAirportDeparture.setText(route.getAirport_departure());
            editAirportDestination.setText(route.getAirport_destination());
            editAirportTransfer.setText(route.getAirport_transfer());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickSave();
    }

    public void onClickSave() { // Сохранение
        if (!TextUtils.isEmpty(editRouteId.getText().toString().trim()) && !TextUtils.isEmpty(editAirportDeparture.getText().toString().trim()) && !TextUtils.isEmpty(editAirportDestination.getText().toString().trim())) {
            route.setId_route(Integer.parseInt(editRouteId.getText().toString()));
            route.setAirport_departure(editAirportDeparture.getText().toString());
            route.setAirport_destination(editAirportDestination.getText().toString());
            route.setAirport_transfer(editAirportTransfer.getText().toString());
            editRouteViewModel.setRouteToDb(route.getId_route(), route.getAirport_departure(), route.getAirport_destination(), route.getAirport_transfer(), routeId != -1);
        } else {
            Toast.makeText(getApplicationContext(), "Необходимые поля не заполнены", Toast.LENGTH_SHORT).show();
        }
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            routeId = i.getIntExtra("routeId", -1);
        }
    }
}
