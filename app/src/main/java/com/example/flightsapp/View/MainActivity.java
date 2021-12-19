package com.example.flightsapp.View;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Room.Note;
import com.example.flightsapp.R;
import com.example.flightsapp.View.Adapters.MainAdapter;
import com.example.flightsapp.ViewModel.MainViewModel;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private MainAdapter mainAdapter;
    private RecyclerView rcView;
    private MainViewModel mainViewModel;
    private String airport_query;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color=\"black\">" + getString(R
                .string.app_name) + "</font>")); // Перекраска заголовка Actionbar
        this.mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        try {
            mainAdapter = new MainAdapter(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("caught");
        }
        init();
    }


    private void init() { // Инициализация необходимых элементов активити и прочего
        airport_query="Russia, Moscow, VKO ";
        try {
            rcView = findViewById(R.id.rcView);

            List<FlightDetails> flights = mainViewModel.getFlightsFromDb(airport_query);
            if (flights != null){
                rcView.setAdapter(mainAdapter);
//                   mainViewModel.setDisplayList(noteList);
                mainAdapter.updateAdapter(flights);
            }
            rcView.setLayoutManager(new LinearLayoutManager(this));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            Log.i(TAG, "IllegalAccessException 22222");
            //mainViewModel.logout();
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        }
    }



//    private void onLogout() { // метод для выхода из аккаунта
//        mainViewModel.logout();
//        startActivity(new Intent(MainActivity.this, AuthActivity.class));
//        finish();
//    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addNote) { // обработка нажатия создания новой заметки
            //startActivity(new Intent(MainActivity.this, EditActivity.class));
            startActivity(new Intent(MainActivity.this, FlightDetailsActivity.class));
        } else if (item.getItemId() == R.id.logout) {
            //onLogout(); // обработка нажатия кнопки выхода из аккаунта
            startActivity(new Intent(MainActivity.this, EditActivity.class));
        }
        return true;
    }

}