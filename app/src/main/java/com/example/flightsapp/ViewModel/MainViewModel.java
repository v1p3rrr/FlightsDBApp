package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Data.Room.Note;
import com.example.flightsapp.Repository.Firebase.FirebaseAuthRepository;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;
import com.example.flightsapp.Repository.Room.NoteRoomRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public MainViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public List<FlightDetails> getFlightsFromDb(String airport_query){
        List<FlightDetails> flights = new ArrayList<>();
        try {
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Select * from flights inner join routes on id_route = id_route_pfk inner join flight_statuses on id_flight_status = id_flight_status_fk where airport_departure= '"+airport_query+"' or airport_destination = '"+airport_query+"'";
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    FlightDetails item = new FlightDetails();
                    //TODO обработка если в результате выполнения вернется null
                    item.setId_airline_pfk(rs.getString("id_airline_pfk"));
                    item.setId_route_pfk(rs.getInt("id_route_pfk"));
                    item.setId_aircraft_fk(rs.getInt("id_aircraft_fk"));
                    item.setId_flight_status_fk(rs.getInt("id_flight_status_fk"));
                    item.setTime_departure(rs.getString("time_departure"));
                    item.setTime_arrival(rs.getString("time_arrival"));
                    item.setTerminal_departure(rs.getString("terminal_departure"));
                    item.setTerminal_destination(rs.getString("terminal_destination"));
                    item.setAirport_destination(rs.getString("airport_destination"));
                    item.setAirport_departure(rs.getString("airport_departure"));
                    item.setAirport_transfer(rs.getString("airport_transfer"));
                    item.setName_status(rs.getString("name_status"));
                    flights.add(item);
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<String> getRouteFromDb(){
        List<String> routes = new ArrayList<>();
        try {
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "(SELECT airport_departure as airport FROM routes) UNION (SELECT airport_destination as airport FROM routes)";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    //TODO обработка если в результате выполнения вернется null
                    routes.add(rs.getString("airport"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routes;
    }

//    public void logout(){
//        firebaseAuthRepository.getMAuth().signOut();
//    }


}
