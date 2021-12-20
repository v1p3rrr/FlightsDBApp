package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminPanelViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public AdminPanelViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public List<String> getTables() {
        List<String> tables = new ArrayList<>();
        tables.add("Flights");
        tables.add("Airlines");
        tables.add("Routes");
        tables.add("Flight Statuses");
        return tables;
    }

    public List<Flight> getFlightsFromDb(){
        List<Flight> flights = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flights";
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Flight item = new Flight();
                    item.setId_airline_pfk(rs.getString("id_airline_pfk"));
                    item.setId_route_pfk(rs.getInt("id_route_pfk"));
                    item.setId_aircraft_fk(rs.getInt("id_aircraft_fk"));
                    item.setId_flight_status_fk(rs.getInt("id_flight_status_fk"));
                    item.setTime_departure(rs.getString("time_departure"));
                    item.setTime_arrival(rs.getString("time_arrival"));
                    item.setTerminal_departure(rs.getString("terminal_departure"));
                    item.setTerminal_destination(rs.getString("terminal_destination"));
                    flights.add(item);
                }
            } else {
                connectionResult = "Check your connection";
                System.out.println(connectionResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Route> getRouteFromDb(){
        List<Route> route = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from routes";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Route item = new Route();
                    item.setAirport_departure(rs.getString("airport_departure"));
                    item.setAirport_destination(rs.getString("airport_destination"));
                    item.setAirport_transfer(rs.getString("airport_transfer"));
                    item.setId_route(rs.getInt("id_route"));
                    route.add(item);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public List<Airline> getAirlineFromDb(){
        List<Airline> airline = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from airlines";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Airline item = new Airline();
                    item.setId_airline(rs.getString("id_airline"));
                    item.setName_airline(rs.getString("name_airline"));
                    item.setApprox_aircrafts_amount(rs.getInt("approx_aircrafts_amount"));
                    item.setOrigin_country(rs.getString("origin_country"));
                    airline.add(item);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airline;
    }

    public List<FlightStatus> getFlightStatusFromDb(){
        List<FlightStatus> flightStatus = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flight_statuses";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    FlightStatus item = new FlightStatus();
                    item.setId_flight_status(rs.getInt("id_flight_status"));
                    item.setName_status(rs.getString("name_status"));
                    item.setArgument_status(rs.getString("argument_status"));
                    flightStatus.add(item);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flightStatus;
    }

    public void deleteFromDb(String table, String idName, int id){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "delete from "+ table +" where " + idName + " = " + id;
                System.out.println(query);
                Statement st = connection.createStatement();
                System.out.println(query);
                st.execute(query);
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDb(String table, String idName, String id){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "delete from "+ table +" where " + idName + " = '" + id + "'";
                System.out.println(query);
                Statement st = connection.createStatement();
                System.out.println(query);
                st.execute(query);
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDb(String table, String idName1, String idName2, String id1, int id2){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "delete from "+ table +" where " + idName1 + " = '" + id1 + "' and " + idName2 + " = " + id2;
                System.out.println(query);
                Statement st = connection.createStatement();
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
