package com.example.flightsapp.View;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.View.Adapters.AdminAdapter;
import com.example.flightsapp.View.Adapters.MainAdapter;
import com.example.flightsapp.ViewModel.AdminPanelViewModel;
import com.example.flightsapp.ViewModel.MainViewModel;

import java.util.List;
import java.util.Objects;

public class AdminPanelActivity extends AppCompatActivity {

    private AdminAdapter adminAdapter;
    private RecyclerView rcView;
    private AdminPanelViewModel adminPanelViewModel;
    private String status;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"black\">" + getString(R
                .string.app_name) + "</font>")); // Перекраска заголовка Actionbar
        this.adminPanelViewModel = new ViewModelProvider(this).get(AdminPanelViewModel.class);
        try {
            adminAdapter = new AdminAdapter(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("caught");
        }
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() { // Инициализация необходимых элементов активити и прочего
        try {
            getIntentStatus();
            setRcView();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.i(TAG, "IllegalAccessException 22222");
            //mainViewModel.logout();
            startActivity(new Intent(AdminPanelActivity.this, AuthActivity.class));
            finish();
        }
    }

    private void setRcView(){
        rcView = findViewById(R.id.rcView);
        if (status == null || status.equals("tables")) {
            List<String> tables = adminPanelViewModel.getTables();
            if (tables != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateTableAdapter(tables);
            }
        } else if (status.equals("flights")) {
            List<Flight> flights = adminPanelViewModel.getFlightsFromDb();
            if (flights != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateFlightAdapter(flights);
            }
        } else if (status.equals("airlines")) {
            List<Airline> airlines = adminPanelViewModel.getAirlineFromDb();
            if (airlines != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateAirlineAdapter(airlines);
            }
        } else if (status.equals("routes")) {
            List<Route> routes = adminPanelViewModel.getRouteFromDb();
            if (routes != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateRoutesAdapter(routes);
            }
        } else if (status.equals("flight statuses")) {
            List<FlightStatus> flightStatus = adminPanelViewModel.getFlightStatusFromDb();
            if (flightStatus != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateFlightStatusAdapter(flightStatus);
            }
        }
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getIntentStatus() {
        Intent i = getIntent();
        if (i != null) {
            status = i.getStringExtra("status");
        }
    }

//    private void onLogout() { // метод для выхода из аккаунта
//        mainViewModel.logout();
//        startActivity(new Intent(MainActivity.this, AuthActivity.class));
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        if (status == null || status.equals("tables")) {
            getMenuInflater().inflate(R.menu.menu_main_user, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {if (item.getItemId() == R.id.addNote) {
        if (status.equals("flight statuses")){
            Intent i = new Intent(AdminPanelActivity.this, EditFlightStatusActivity.class);
            startActivity(i);
        }
    } else if (item.getItemId() == R.id.logout) {
        //onLogout(); // обработка нажатия кнопки выхода из аккаунта
        startActivity(new Intent(AdminPanelActivity.this, AuthActivity.class));
        finish();
    }
        return true;
    }
}
