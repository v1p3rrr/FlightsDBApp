package com.example.flightsapp.Data.Mssql;

import java.util.Objects;

public class FlightStatus {
    private int id_flight_status;
    private String name_status;
    private String argument_status;

    private int name_status_length = 50;
    private int argument_status_length = 199;

    public FlightStatus(int id_flight_status, String name_status, String argument_status) {
        this.id_flight_status = id_flight_status;
        this.name_status = name_status;
        this.argument_status = argument_status;
    }

    public FlightStatus() {
    }

    @Override
    public String toString() {
        return name_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightStatus)) return false;
        FlightStatus that = (FlightStatus) o;
        return getId_flight_status() == that.getId_flight_status() && getName_status_length() == that.getName_status_length() && getArgument_status_length() == that.getArgument_status_length() && Objects.equals(getName_status(), that.getName_status()) && Objects.equals(getArgument_status(), that.getArgument_status());
    }

    public int getId_flight_status() {
        return id_flight_status;
    }

    public void setId_flight_status(int id_flight_status) {
        this.id_flight_status = id_flight_status;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }

    public String getArgument_status() {
        return argument_status;
    }

    public void setArgument_status(String argument_status) {
        this.argument_status = argument_status;
    }

    public int getName_status_length() {
        return name_status_length;
    }

    public int getArgument_status_length() {
        return argument_status_length;
    }
}
