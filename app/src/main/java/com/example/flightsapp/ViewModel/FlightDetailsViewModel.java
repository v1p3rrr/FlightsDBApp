package com.example.flightsapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Flight;
import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightDetailsViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public FlightDetailsViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public FlightDetails getFlightFromDb(String id_airline_pfk, int id_route_pfk){
        FlightDetails flight = new FlightDetails();
        try {
            connection = connectionHelper.connection(true); //todo
            if (connection != null) {
                String query = "Select * from flights inner join routes on id_route = id_route_pfk inner join aircrafts on id_aircraft = id_aircraft_fk inner join flight_statuses on id_flight_status = id_flight_status_fk inner join airlines on id_airline = id_airline_pfk where id_airline_pfk = '"+id_airline_pfk+"' and id_route_pfk = "+id_route_pfk;
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    System.out.println(rs.getString("id_airline_pfk"));
                    //TODO обработка если в результате выполнения вернется null
                    flight.setId_airline_pfk(rs.getString("id_airline_pfk"));
                    flight.setId_route_pfk(rs.getInt("id_route_pfk"));
                    flight.setId_aircraft_fk(rs.getInt("id_aircraft_fk"));
                    flight.setId_flight_status_fk(rs.getInt("id_flight_status_fk"));
                    flight.setTime_departure(rs.getString("time_departure"));
                    flight.setTime_arrival(rs.getString("time_arrival"));
                    flight.setTerminal_departure(rs.getString("terminal_departure"));
                    flight.setTerminal_destination(rs.getString("terminal_destination"));
                    flight.setAirport_destination(rs.getString("airport_destination"));
                    flight.setAirport_departure(rs.getString("airport_departure"));
                    flight.setAirport_transfer(rs.getString("airport_transfer"));
                    flight.setName_status(rs.getString("name_status"));
                    flight.setId_model_fk(rs.getString("id_model_fk"));
                    flight.setId_manufacturer_fk(rs.getString("id_manufacturer_fk"));
                    flight.setSerial_number(rs.getInt("serial_number"));
                    flight.setName_airline(rs.getString("name_airline"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flight;
    }


//    public Route getRouteFromDb(String id_airline_pfk, int id_route_pfk){
//        Route route = new Route();
//        try {
//            connection = connectionHelper.connection();
//            if (connection != null) {
//                String query = "Select id_route, airport_departure, airport_destination, airport_transfer from flights inner join routes on id_route = id_route_pfk where id_airline_pfk = "+id_airline_pfk+" and id_route_pfk = "+id_route_pfk;
//                Statement st = connection.createStatement();
//                ResultSet rs = st.executeQuery(query);
//                while (rs.next()) {
//                    //TODO обработка если в результате выполнения вернется null
//                    route.setAirport_departure(rs.getString("airport_departure"));
//                    route.setAirport_departure(rs.getString("airport_destination"));
//                    route.setAirport_transfer(rs.getString("airport_transfer"));
//                    route.setId_route(rs.getInt("id_route"));
//                }
//            } else {
//                connectionResult = "Check your connection";
//            }
//        } catch (Exception e) {
//            Log.e("Error: ", e.getMessage());
//        }
//        return route;
//    }
}
