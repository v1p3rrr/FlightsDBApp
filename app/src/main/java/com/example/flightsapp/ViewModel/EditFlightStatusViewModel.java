package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Room.Note;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;
import com.example.flightsapp.Repository.Network.WordsImplementation;
import com.example.flightsapp.Repository.Room.NoteRoomRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditFlightStatusViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public EditFlightStatusViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public FlightStatus getFlightStatusFromDb(int statusId){
        FlightStatus flightStatus = new FlightStatus();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flight_statuses where id_flight_status = "+statusId;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    flightStatus.setId_flight_status(rs.getInt("id_flight_status"));
                    flightStatus.setName_status(rs.getString("name_status"));
                    flightStatus.setArgument_status(rs.getString("argument_status"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightStatus;
    }

    public void setFlightStatusToDb(int statusId, String name_status, String argument_status, boolean edit){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "";
                if (edit) {
                    query = "update flight_statuses set name_status = '" + name_status + "', argument_status = " + (argument_status != null ? "'" + argument_status + "'" : "null") + " where id_flight_status = " + statusId;
                } else {
                    query = "insert into flight_statuses (name_status, argument_status) values ('" + name_status + (argument_status != null ? "', '" + argument_status + "')" : ", null)");
                }Statement st = connection.createStatement();
                System.out.println(query);
                st.execute(query);
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
