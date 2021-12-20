package com.example.flightsapp.Data.Mssql;

public class User {

    private String username;
    private String pass;
    private boolean isAdmin;

    public User(String username, String pass, boolean isAdmin) {
        this.username = username;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
