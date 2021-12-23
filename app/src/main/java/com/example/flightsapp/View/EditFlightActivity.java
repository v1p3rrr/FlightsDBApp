package com.example.flightsapp.View;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.flightsapp.Data.Mssql.Aircraft;
import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.R;
import com.example.flightsapp.View.Adapters.FlightsAircraftSpinnerAdapter;
import com.example.flightsapp.View.Adapters.FlightsAirlineSpinnerAdapter;
import com.example.flightsapp.View.Adapters.FlightsRouteSpinnerAdapter;
import com.example.flightsapp.View.Adapters.FlightsStatusSpinnerAdapter;
import com.example.flightsapp.ViewModel.EditAirlineViewModel;
import com.example.flightsapp.ViewModel.EditFlightViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditFlightActivity extends AppCompatActivity {

    private Spinner idAirlineSpinner, idRouteSpinner, idAircraftSpinner, idFlightStatusSpinner;
    private Button buttonPickDateDeparture, buttonPickDateDestination, buttonPickTimeDeparture, buttonPickTimeDestination;
    private EditText editTerminalDeparture, editTerminalDestination;
    private EditFlightViewModel editFlightViewModel;
    private FlightsAirlineSpinnerAdapter adapterAirline;
    private FlightsRouteSpinnerAdapter adapterRoute;
    private FlightsAircraftSpinnerAdapter adapterAircraft;
    private FlightsStatusSpinnerAdapter adapterStatus;
    private String airlineId, timeDeparture, timeDestination, dateDeparture, dateDestination;
    private int routeId;
    private Airline airline;
    private Route route;
    private FlightStatus flightStatus;
    private Aircraft aircraft;
    private List<FlightDetails> flights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight);
        getIntentMain();
        init();
    }

    Calendar date;
    public void showDateTimePicker(View view) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, (view1, year, monthOfYear, dayOfMonth) -> {
            date.set(year, monthOfYear, dayOfMonth);
            new TimePickerDialog(getBaseContext(), (view2, hourOfDay, minute) -> {
                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                date.set(Calendar.MINUTE, minute);
                Log.v(TAG, "The chosen one " + date.getTime());
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    @SuppressLint("SetTextI18n")
    public void init() {
        this.editFlightViewModel = new ViewModelProvider(this).get(EditFlightViewModel.class);

        idAirlineSpinner = findViewById(R.id.idAirlineSpinner); //todo editable only if new
        idRouteSpinner = findViewById(R.id.idRouteSpinner);
        idAircraftSpinner = findViewById(R.id.idAircraftSpinner);
        idFlightStatusSpinner = findViewById(R.id.idFlightStatusSpinner);

        editTerminalDeparture = findViewById(R.id.editTerminalDeparture);
        editTerminalDestination = findViewById(R.id.editTerminalDestination);

        editTerminalDeparture.setText(editFlightViewModel.getFlightFromDb(airlineId, routeId).getTerminal_departure());
        editTerminalDestination.setText(editFlightViewModel.getFlightFromDb(airlineId, routeId).getTerminal_destination());

        setButtons();
        setSpinners();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onClickSave();
    }

    public void setButtons(){
        Pattern patternDate = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Pattern patternTime = Pattern.compile("\\d{2}:\\d{2}");
        timeDeparture=""; timeDestination=""; dateDeparture=""; dateDestination="";

        buttonPickDateDeparture = findViewById(R.id.buttonPickDateDeparture);
        String text = editFlightViewModel.getFlightFromDb(airlineId, routeId).getTime_departure();
        Matcher matcher = patternDate.matcher(text);
        while (matcher.find()) {
            dateDeparture+=text.substring(matcher.start(), matcher.end());
        }
        buttonPickDateDeparture.setText(dateDeparture);
        buttonPickDateDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(buttonPickDateDeparture);
            }
        });

        buttonPickDateDestination = findViewById(R.id.buttonPickDateDestination);
        text = editFlightViewModel.getFlightFromDb(airlineId, routeId).getTime_arrival();
        matcher = patternDate.matcher(text);
        while (matcher.find()) {
            dateDestination+=text.substring(matcher.start(), matcher.end());
        }
        buttonPickDateDestination.setText(dateDestination);
        buttonPickDateDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate(buttonPickDateDestination);
            }
        });

        buttonPickTimeDeparture = findViewById(R.id.buttonPickTimeDeparture);
        text = editFlightViewModel.getFlightFromDb(airlineId, routeId).getTime_departure();
        matcher = patternTime.matcher(text);
        while (matcher.find()) {
            timeDeparture+=text.substring(matcher.start(), matcher.end());
        }
        buttonPickTimeDeparture.setText(timeDeparture);
        timeDeparture+=":00";
        buttonPickTimeDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(buttonPickTimeDeparture);
            }
        });

        buttonPickTimeDestination = findViewById(R.id.buttonPickTimeDestination);
        text = editFlightViewModel.getFlightFromDb(airlineId, routeId).getTime_arrival();
        matcher = patternTime.matcher(text);
        while (matcher.find()) {
            timeDestination+=text.substring(matcher.start(), matcher.end());
        }
        buttonPickTimeDestination.setText(timeDestination);
        timeDestination+=":00";
        buttonPickTimeDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(buttonPickTimeDestination);
            }
        });
    }

    public void setTime(Button btn){
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);

        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                CharSequence charSequence = DateFormat.format("HH:mm", calendar1);

                btn.setText(charSequence.toString());
                if (btn == buttonPickTimeDeparture){
                    timeDeparture = charSequence.toString()+":00";
                } else {
                    timeDestination = charSequence.toString()+":00";
                }
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();
    }

    public void setDate(Button btn){
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                CharSequence charSequence = DateFormat.format("MMM d, yyyy", calendar1);
                btn.setText(charSequence.toString());

                charSequence = DateFormat.format("yyyy-MM-dd", calendar1);
                if (btn == buttonPickDateDeparture){
                    dateDeparture = charSequence.toString();
                } else if (btn == buttonPickDateDestination){
                    dateDestination = charSequence.toString();
                }
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    public void setSpinners(){
        //Airline spinner
        adapterAirline = new FlightsAirlineSpinnerAdapter(this, editFlightViewModel.getAirlineFromDb());
        idAirlineSpinner.setAdapter(adapterAirline);
        if (airlineId != null) {
            int spinnerPosition = adapterAirline.getPosition(editFlightViewModel.getAirlineFromDb(airlineId).get(0));
            idAirlineSpinner.setSelection(spinnerPosition);
        }
        AdapterView.OnItemSelectedListener airlineSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                airline = (Airline) parent.getItemAtPosition(position);

                Toast.makeText(parent.getContext(), "Selected: " + airline.getName_airline() + "  " + airline.getId_airline(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        idAirlineSpinner.setOnItemSelectedListener(airlineSelectedListener);

        //Route spinner
        adapterRoute = new FlightsRouteSpinnerAdapter(this, editFlightViewModel.getRouteFromDb());
        idRouteSpinner.setAdapter(adapterRoute);
        if (routeId != -1) {
            int spinnerPosition = adapterRoute.getPosition(editFlightViewModel.getRouteFromDb(routeId).get(0));
            idRouteSpinner.setSelection(spinnerPosition);
        }
        AdapterView.OnItemSelectedListener routeSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                route = (Route) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        idRouteSpinner.setOnItemSelectedListener(routeSelectedListener);

        //Aircraft spinner
        adapterAircraft = new FlightsAircraftSpinnerAdapter(this, editFlightViewModel.getAircraftFromDb());
        idAircraftSpinner.setAdapter(adapterAircraft);
        if (airlineId != null && routeId != -1) {
            int spinnerPosition = adapterAircraft.getPosition(editFlightViewModel.getAircraftFromDb(editFlightViewModel.getFlightFromDb(airlineId, routeId).getId_flight_status_fk()).get(0));
            idAircraftSpinner.setSelection(spinnerPosition);
        }
        AdapterView.OnItemSelectedListener aircraftSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                aircraft = (Aircraft) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        idAircraftSpinner.setOnItemSelectedListener(aircraftSelectedListener);

        //Flight status spinner
        adapterStatus = new FlightsStatusSpinnerAdapter(this, editFlightViewModel.getFlightStatusFromDb());
        idFlightStatusSpinner.setAdapter(adapterStatus);
        if (airlineId != null && routeId != -1) {
            int spinnerPosition = adapterStatus.getPosition(editFlightViewModel.getFlightStatusFromDb(editFlightViewModel.getFlightFromDb(airlineId, routeId).getId_flight_status_fk()).get(0));
            idFlightStatusSpinner.setSelection(spinnerPosition);
        }
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                flightStatus = (FlightStatus) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        idFlightStatusSpinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public void onClickSave() { // Сохранение
        if (!(editFlightViewModel.setFlightToDb(airlineId, routeId, airline.getId_airline(), route.getId_route(), aircraft.getId_aircraft(), flightStatus.getId_flight_status(), dateDeparture+"T"+timeDeparture, dateDestination+"T"+timeDestination, editTerminalDeparture.getText().toString().equals("") ? null : editTerminalDeparture.getText().toString(), editTerminalDestination.getText().toString().equals("") ? null : editTerminalDestination.getText().toString(), (airlineId != null && routeId != -1)))) {
            Toast.makeText(getApplicationContext(), "Ошибка при сохранении", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
        }
    }



    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            airlineId = i.getStringExtra("airlineId");
            routeId = i.getIntExtra("routeId", -1);
        }
    }
}
