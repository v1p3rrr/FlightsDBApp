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
import androidx.recyclerview.widget.ItemTouchHelper;
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
                getItemTouchHelper().attachToRecyclerView(rcView);
            }
        } else if (status.equals("airlines")) {
            List<Airline> airlines = adminPanelViewModel.getAirlineFromDb();
            if (airlines != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateAirlineAdapter(airlines);
                getItemTouchHelper().attachToRecyclerView(rcView);
            }
        } else if (status.equals("routes")) {
            List<Route> routes = adminPanelViewModel.getRouteFromDb();
            if (routes != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateRoutesAdapter(routes);
                getItemTouchHelper().attachToRecyclerView(rcView);
            }
        } else if (status.equals("flight_statuses")) {
            List<FlightStatus> flightStatus = adminPanelViewModel.getFlightStatusFromDb();
            if (flightStatus != null) {
                rcView.setAdapter(adminAdapter);
                adminAdapter.updateFlightStatusAdapter(flightStatus);
                getItemTouchHelper().attachToRecyclerView(rcView);
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

    private ItemTouchHelper getItemTouchHelper() {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) { // Удаление по свайпу
                switch (status) {
                    case "flights":
                        adminPanelViewModel.deleteFromDb("flights", "id_airline_pfk", "id_route_pfk", adminAdapter.getFlight(viewHolder.getAdapterPosition()).getId_airline_pfk(), adminAdapter.getFlight(viewHolder.getAdapterPosition()).getId_route_pfk());
                        adminAdapter.updateFlightAdapter(adminPanelViewModel.getFlightsFromDb());
                        break;
                    case "airlines":
                        adminPanelViewModel.deleteFromDb("airlines", "id_airline", adminAdapter.getAirline(viewHolder.getAdapterPosition()).getId_airline());
                        adminAdapter.updateAirlineAdapter(adminPanelViewModel.getAirlineFromDb());
                        break;
                    case "routes":
                        adminPanelViewModel.deleteFromDb("routes", "id_route", adminAdapter.getRoute(viewHolder.getAdapterPosition()).getId_route());
                        adminAdapter.updateRoutesAdapter(adminPanelViewModel.getRouteFromDb());
                        break;
                    case "flight_statuses":
                        adminPanelViewModel.deleteFromDb("flight_statuses", "id_flight_status", adminAdapter.getFlightStatus(viewHolder.getAdapterPosition()).getId_flight_status());
                        adminAdapter.updateFlightStatusAdapter(adminPanelViewModel.getFlightStatusFromDb());
                        break;
                }
            }
        });
    }

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
        if (status.equals("flight_statuses")){
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
