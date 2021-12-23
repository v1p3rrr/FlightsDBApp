package com.example.flightsapp.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditRouteViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public EditRouteViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public Route getRouteFromDb(int routeId){
        Route route = new Route();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from routes where id_route = "+routeId;
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    route.setId_route(rs.getInt("id_route"));
                    route.setAirport_departure(rs.getString("airport_departure"));
                    route.setAirport_destination(rs.getString("airport_destination"));
                    route.setAirport_transfer(rs.getString("airport_transfer"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public void setRouteToDb(int id_route, String airport_departure, String airport_destination, String airport_transfer, boolean edit){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "";
                if (edit) {
                    query = "update routes set airport_departure = '" + airport_departure + "', airport_destination = '" + airport_destination + "', airport_transfer = " + (airport_transfer != null ? "'" + airport_transfer + "'" : "null") + " where id_route = " + id_route;
                } else {
                    query = "insert into routes (id_route, airport_departure, airport_destination, airport_transfer) values (" + id_route + ", '" + airport_departure + "', '" + airport_destination + (airport_transfer != null ? "', '" + airport_transfer + "')" : ", null)");
                }Statement st = connection.createStatement();
                System.out.println(query);
                st.execute(query);
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
