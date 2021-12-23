package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.Airline;
import com.example.flightsapp.Data.Mssql.Route;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EditAirlineViewModel extends AndroidViewModel {

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public EditAirlineViewModel(@NonNull Application application) {
        super(application);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public Airline getAirlineFromDb(String airlineId){
        Airline airline = new Airline();
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from airlines where id_airline = '"+airlineId+"'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    airline.setId_airline(rs.getString("id_airline"));
                    airline.setName_airline(rs.getString("name_airline"));
                    airline.setApprox_aircrafts_amount(rs.getInt("approx_aircrafts_amount"));
                    airline.setOrigin_country(rs.getString("origin_country"));
                }
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airline;
    }

    public void setAirlineToDb(String id_airline, String name_airline, int approx_aircrafts_amount, String origin_country, boolean edit){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "";
                if (edit) {
                    query = "update airlines set name_airline = '" + name_airline + "', origin_country = '" + origin_country + "', approx_aircrafts_amount = " + approx_aircrafts_amount + " where id_airline = '" + id_airline+"'";
                } else {
                    query = "insert into airlines (id_airline, name_airline, origin_country, approx_aircrafts_amount) values ('" + id_airline + "', '" + name_airline + "', '" + origin_country + "', " + approx_aircrafts_amount +")";
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
