package com.example.flightsapp.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.ViewModel.EditAirlineViewModel;
import com.example.flightsapp.ViewModel.EditRouteViewModel;

public class EditAirlineActivity  extends AppCompatActivity {

    private EditText editAirlineId;
    private EditText editAirlineName;
    private EditText editAircraftsAmount;
    private EditText editOriginCountry;
    private String airlineId;
    private EditAirlineViewModel editAirlineViewModel;
    private Airline airline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_airline);
        getIntentMain();
        init();
    }

    @SuppressLint("SetTextI18n")
    public void init() {
        editAirlineId = findViewById(R.id.editAirlineId); //todo editable only if new
        editAirlineName = findViewById(R.id.editAirlineName);
        editAircraftsAmount = findViewById(R.id.editAircraftsAmount);
        editOriginCountry = findViewById(R.id.editOriginCountry);

        this.editAirlineViewModel = new ViewModelProvider(this).get(EditAirlineViewModel.class);

        if (airlineId != null) {
            airline = editAirlineViewModel.getAirlineFromDb(airlineId);
        }
        if (airline != null) {
            editAirlineId.setText(airline.getId_airline());
            editAirlineName.setText(airline.getName_airline());
            editAircraftsAmount.setText(""+airline.getApprox_aircrafts_amount());
            editOriginCountry.setText(airline.getOrigin_country());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickSave();
    }

    public void onClickSave() { // Сохранение
        if (!TextUtils.isEmpty(editAirlineId.getText().toString().trim()) && !TextUtils.isEmpty(editAirlineName.getText().toString().trim()) && !TextUtils.isEmpty(editOriginCountry.getText().toString().trim())) {
            if (airline == null) airline = new Airline();
            airline.setId_airline(editAirlineId.getText().toString());
            airline.setName_airline(editAirlineName.getText().toString());
            airline.setApprox_aircrafts_amount(Integer.parseInt(editAircraftsAmount.getText().toString()));
            airline.setOrigin_country(editOriginCountry.getText().toString());
            editAirlineViewModel.setAirlineToDb(airline.getId_airline(), airline.getName_airline(), airline.getApprox_aircrafts_amount(), airline.getOrigin_country(), airlineId != null);
        } else {
            Toast.makeText(getApplicationContext(), "Необходимые поля не заполнены", Toast.LENGTH_SHORT).show();
        }
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            airlineId = i.getStringExtra("airlineId");
        }
    }
}