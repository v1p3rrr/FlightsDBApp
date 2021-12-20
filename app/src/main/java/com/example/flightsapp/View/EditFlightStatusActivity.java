package com.example.flightsapp.View;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Room.Note;
import com.example.flightsapp.ViewModel.EditFlightStatusViewModel;
import com.example.flightsapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class EditFlightStatusActivity extends AppCompatActivity {

    private AutoCompleteTextView editName;
    private EditText editSpecifications;
    private int statusId;
    private EditFlightStatusViewModel editFlightStatusViewModel;
    private FlightStatus flightStatus = new FlightStatus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight_status);
        getIntentMain();
        init();
    }

    public void init() {
        editName = findViewById(R.id.editStatusName);
        editSpecifications = findViewById(R.id.editSpecifications);

        this.editFlightStatusViewModel = new ViewModelProvider(this).get(EditFlightStatusViewModel.class);

        if (statusId != -1){
            flightStatus = editFlightStatusViewModel.getFlightStatusFromDb(statusId);
        }
        if (flightStatus != null){
            editName.setText(flightStatus.getName_status());
            editSpecifications.setText(flightStatus.getArgument_status());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickSave();
    }

    public void onClickSave() { // Сохранение заметки
        if (!TextUtils.isEmpty(editName.getText().toString().trim()) && !TextUtils.isEmpty(editSpecifications.getText().toString().trim())) {
            flightStatus.setName_status(editName.getText().toString());
            flightStatus.setArgument_status(editSpecifications.getText().toString());
            editFlightStatusViewModel.setFlightStatusToDb(flightStatus.getId_flight_status(), flightStatus.getName_status(), flightStatus.getArgument_status(), statusId != -1);
        } else {
            Toast.makeText(getApplicationContext(), "Необходимые поля не заполнены", Toast.LENGTH_SHORT).show();
        }
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            statusId = i.getIntExtra("statusId", -1);
        }
    }

}