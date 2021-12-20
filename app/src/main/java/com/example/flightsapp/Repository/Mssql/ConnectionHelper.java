package com.example.flightsapp.Repository.Mssql;

import android.os.StrictMode;
import android.util.Log;

import com.example.flightsapp.Repository.Room.NoteRoomRepository;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    private static ConnectionHelper instance;

    private ConnectionHelper() {

    }

    public static ConnectionHelper getInstance() {
        if (instance == null)
            instance = new ConnectionHelper();
        return instance;
    }

    String username, password, ip, port, database;

    public Connection connection(boolean isAdmin){
        ip = "192.168.1.71";
        if (isAdmin) {
            username = "sa";
            password = "12345";
        } else {
            //todo
            username = "flights_user";
            password = "12345";
        }
        database = "flightsdb";
        port = "1433";

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";"+"databasename="+database+";user="+username+";password="+password+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return connection;
    }
}
