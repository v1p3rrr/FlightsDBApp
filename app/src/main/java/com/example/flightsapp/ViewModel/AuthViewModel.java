package com.example.flightsapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.flightsapp.Data.Mssql.FlightDetails;
import com.example.flightsapp.Repository.Firebase.FirebaseAuthRepository;
import com.example.flightsapp.Repository.Mssql.ConnectionHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthViewModel extends AndroidViewModel {
//    private final FirebaseAuthRepository firebaseAuthRepository;
//
//    public AuthViewModel(Application app){
//        super(app);
//        firebaseAuthRepository = FirebaseAuthRepository.getInstance();
//    }
//
//    public FirebaseAuth getMAuth(){
//        return firebaseAuthRepository.getMAuth();
//    }

    private ConnectionHelper connectionHelper;
    Connection connection;
    String connectionResult = "";

    public AuthViewModel(Application app){
        super(app);
        connectionHelper = ConnectionHelper.getInstance();
    }

    public int authorization(String username, String pass){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from users where username = '" + username + "' and pass = '" + pass + "'";
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    if (rs.getInt("privileges") == 1) {
                        connection = null;
                        return 1;
                    } else if (rs.getInt("privileges") == 0) {
                        connection = null;
                        return 0;
                    }
                }
            } else {
                    connectionResult = "Check your connection";
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = null;
        return -1;
    }

    public boolean registration(String username, String pass){
        try {
            connection = connectionHelper.connection(true);
            if (connection != null) {
                String query = "Select * from users where username = '" + username;
                System.out.println(query);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    if (username.equals(rs.getString("username"))) {
                        connection = null;
                        return false;
                    }
                }
                query = "insert into users (username, pass, privileges) values ('" + username + "', '" + pass + "', 0)";
                st = connection.createStatement();
                st.execute(query);
                connection = null;
                return true;
            } else {
                connectionResult = "Check your connection";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection = null;
        return false;
    }
}
