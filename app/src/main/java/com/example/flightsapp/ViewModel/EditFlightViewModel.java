package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Aircraft;
import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightStatus;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EditFlightViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public EditFlightViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public Flight getFlightFromDb(String airlineId, int routeId){
        Flight flight = new Flight();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flights where id_airline_pfk = '"+airlineId+"' and id_route_pfk ="+routeId;
                Statement st = connection.createStatement();
                System.out.println(query);
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

    public boolean setFlightToDb(String airlineIdOld, int routeIdOld, String airlineId, int routeId, int id_aircraft_fk, int id_flight_status_fk, String time_departure, String time_arrival, String terminal_departure, String terminal_destination, boolean edit){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "";
                if (edit) {
                    query = "update flights set id_airline_pfk = '" + airlineId + "', id_route_pfk = " + routeId + ", id_aircraft_fk = " + id_aircraft_fk + ", id_flight_status_fk = " + id_flight_status_fk + ", time_departure = '" + time_departure + "', time_arrival = '" + time_arrival + "', terminal_departure = " + (terminal_departure != null ? ("'" + terminal_departure + "'") : "null") + ", terminal_destination = " + (terminal_destination != null ? "'" + terminal_destination + "'" : "null") + " where id_airline_pfk = '" + airlineIdOld + "' and id_route_pfk =" + routeIdOld;
                } else {
                    query = "insert into flights (id_airline_pfk, id_route_pfk, id_aircraft_fk, id_flight_status_fk, time_departure, time_arrival, terminal_departure, terminal_destination) values ('" + airlineId + "', " + routeId + ", " + id_aircraft_fk + ", " + id_flight_status_fk + ", '" + time_departure + "', '" + time_arrival + "', " + (terminal_departure != null ? "'" + terminal_departure + "'" : "null") + ", " + (terminal_destination != null ? "'" + terminal_destination + "'" : "null)");
                }Statement st = connection.createStatement();
                System.out.println(query);
                st.execute(query);
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Airline> getAirlineFromDb(){
        return getAirlineFromDb(null);
    }

    public List<Airline> getAirlineFromDb(String airlineId){
        List<Airline> list = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from airlines" + (airlineId == null ? "" : " where id_airline = '"+airlineId+"'");
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Airline airline = new Airline();
                    airline.setId_airline(rs.getString("id_airline"));
                    airline.setName_airline(rs.getString("name_airline"));
                    airline.setApprox_aircrafts_amount(rs.getInt("approx_aircrafts_amount"));
                    airline.setOrigin_country(rs.getString("origin_country"));
                    list.add(airline);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Route> getRouteFromDb(){
        return getRouteFromDb(-1);
    }

    public List<Route> getRouteFromDb(int routeId){
        List<Route> list = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from routes"  + (routeId == -1 ? "" : " where id_route = "+routeId);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Route route = new Route();
                    route.setId_route(rs.getInt("id_route"));
                    route.setAirport_departure(rs.getString("airport_departure"));
                    route.setAirport_destination(rs.getString("airport_destination"));
                    route.setAirport_transfer(rs.getString("airport_transfer"));
                    list.add(route);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FlightStatus> getFlightStatusFromDb(){
        return getFlightStatusFromDb(-1);
    }

    public List<FlightStatus> getFlightStatusFromDb(int statusId){
        List<FlightStatus> list = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from flight_statuses"  + (statusId == -1 ? "" : " where id_flight_status = "+statusId);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    FlightStatus flightStatus = new FlightStatus();
                    flightStatus.setId_flight_status(rs.getInt("id_flight_status"));
                    flightStatus.setName_status(rs.getString("name_status"));
                    flightStatus.setArgument_status(rs.getString("argument_status"));
                    list.add(flightStatus);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Aircraft> getAircraftFromDb(){
        return getAircraftFromDb(-1);
    }
// String query = "Select * from flight_statuses" + ((airlineId == null || routeId == -1) ? "" : " inner join flights on on id_flight_status = id_flight_status_fk where id_airline = '"+airlineId+"' and id_route = "+routeId);
// String query = "Select * from aircrafts" + ((airlineId == null || routeId == -1) ? "" : " inner join flights on id_aircraft = id_aircraft_fk where id_airline = '"+airlineId+"' and id_route = "+routeId);
    public List<Aircraft> getAircraftFromDb(int aircraftId){
        List<Aircraft> list = new ArrayList<>();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from aircrafts" + (aircraftId == -1 ? "" : " where id_aircraft = "+aircraftId);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Aircraft aircraft = new Aircraft();
                    aircraft.setId_aircraft(rs.getInt("id_aircraft"));
                    aircraft.setId_model_fk(rs.getString("id_model_fk"));
                    aircraft.setId_manufacturer_fk(rs.getString("id_model_fk"));
                    aircraft.setSerial_number(rs.getInt("serial_number"));
                    aircraft.setSpecifications(rs.getString("specifications"));
                    list.add(aircraft);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
