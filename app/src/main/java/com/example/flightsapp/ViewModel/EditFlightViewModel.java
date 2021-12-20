package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditFlightViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public EditFlightViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public Flight getFlightFromDb(int flightId){
        Flight flight = new Flight();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flight where id_flight = "+flightId;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    flight.setId_airline_pfk(rs.getString("id_airline_pfk"));
                    flight.setId_route_pfk(rs.getInt("id_route_pfk"));
                    flight.setId_aircraft_fk(rs.getInt("id_aircraft_fk"));
                    flight.setId_flight_status_fk(rs.getInt("id_flight_status_fk"));
                    flight.setTime_departure(rs.getString("time_departure"));
                    flight.setTime_arrival(rs.getString("time_arrival"));
                    flight.setTerminal_departure(rs.getString("terminal_departure"));
                    flight.setTerminal_destination(rs.getString("terminal_destination"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }

    public void setFlightToDb(String airlineId, int routeId, int id_aircraft_fk, int id_flight_status_fk, String time_departure, String time_arrival, String terminal_departure, String terminal_destination, boolean edit){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "";
                if (edit) {
                    query = "update flight set id_aircraft_fk = '" + id_aircraft_fk + "', id_flight_status_fk = '" + id_flight_status_fk + "', time_departure = '" + time_departure + "', time_arrival = '" + time_arrival + "', terminal_departure = " + (terminal_departure != null ? "'" + terminal_departure + "'" : "null") + "', terminal_destination = " + (terminal_destination != null ? "'" + terminal_destination + "'" : "null") + " where id_airline_pfk = '" + airlineId + "' and id_route_pfk =" + routeId;
                } else {
                    query = "insert into flights (id_airline_pfk, id_route_pfk, id_aircraft_fk, id_flight_status_fk, time_departure, time_arrival, terminal_departure, terminal_destination) values (" + id_aircraft_fk + ", " + id_flight_status_fk + ", '" + time_departure + "', '" + time_arrival + "', " + (terminal_departure != null ? "'" + terminal_departure + "'" : "null") + "', " + (terminal_destination != null ? "'" + terminal_destination + "'" : "null");
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
