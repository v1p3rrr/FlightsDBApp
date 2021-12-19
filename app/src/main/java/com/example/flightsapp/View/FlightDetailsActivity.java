package com.example.flightsapp.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;
import com.example.flightsapp.Repository.Network.WordsImplementation;
import com.example.flightsapp.ViewModel.FlightDetailsViewModel;
import com.example.flightsapp.ViewModel.MainViewModel;
import com.google.android.gms.common.ConnectionResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

public class FlightDetailsActivity extends AppCompatActivity {

    FlightDetailsViewModel viewModel;
    private int id_route_pfk;
    private String id_airline_pfk;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        this.viewModel = new ViewModelProvider(this).get(FlightDetailsViewModel.class);
        getIntentMain();
        getInfo();
    }

    @SuppressLint("SetTextI18n")
    public void getInfo() {
        if (id_route_pfk!=-1 && id_airline_pfk!=null) {
            FlightDetails flight = viewModel.getFlightFromDb(id_airline_pfk, id_route_pfk); //todo

            TextView textView1 = findViewById(R.id.textViewTest1);
            TextView textView2 = findViewById(R.id.textViewTest2);
            TextView textView3 = findViewById(R.id.textViewTest3);
            TextView textView4 = findViewById(R.id.textViewTest4);
            TextView textView5 = findViewById(R.id.textViewTest5);
            TextView textView6 = findViewById(R.id.textViewTest6);
            TextView textView7 = findViewById(R.id.textViewTest7);
            TextView textView8 = findViewById(R.id.textViewTest8);

            if (flight != null) { //todo
                textView1.setText("Рейс " + flight.getId_airline_pfk() + "-" + flight.getId_route_pfk() + " авиакомпании " + flight.getName_airline());
                textView2.setText("Из: " + flight.getAirport_departure() + (flight.getTerminal_departure() != null ? ", терминал: " + flight.getTerminal_departure() : ", терминал не указан"));
                textView3.setText("В: " + flight.getAirport_destination() + (flight.getTerminal_destination() != null ? ", терминал: " + flight.getTerminal_destination() : ", терминал не указан"));
                textView4.setText("Пересадка " + (flight.getAirport_transfer() != null ? "в: " + flight.getAirport_transfer() : "отсутствует"));
                textView5.setText("Отправление в: " + flight.getTime_departure());
                textView6.setText("Прибытие в: " + flight.getTime_arrival());
                textView7.setText("Статус: " + flight.getName_status());
                textView8.setText("Самолёт: " + flight.getId_model_fk() + " " + flight.getId_manufacturer_fk() + ", серийный номер: " + flight.getSerial_number());
            }
        }
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            id_route_pfk = i.getIntExtra("routeId", -1);
            id_airline_pfk = i.getStringExtra("airlineId");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addNote) { // обработка нажатия создания новой заметки
            //startActivity(new Intent(MainActivity.this, EditActivity.class));
            getInfo();
        }
        return true;
    }
}
