package com.example.flightsapp.Data.Mssql;

public class Flight {
    private String id_airline_pfk;
    private int id_route_pfk;
    private int id_aircraft_fk;
    private int id_flight_status_fk;
    private String time_departure;
    private String time_arrival;
    private String terminal_departure;
    private String terminal_destination;

    private int id_airline_pfk_length = 3;
    private int terminal_departure_length = 2;
    private int terminal_destination_length = 2;

    public Flight(String id_airline_pfk, int id_route_pfk, int id_aircraft_fk, int id_flight_status_fk, String time_departure, String time_arrival, String terminal_departure, String terminal_destination) {
        this.id_airline_pfk = id_airline_pfk;
        this.id_route_pfk = id_route_pfk;
        this.id_aircraft_fk = id_aircraft_fk;
        this.id_flight_status_fk = id_flight_status_fk;
        this.time_departure = time_departure;
        this.time_arrival = time_arrival;
        this.terminal_departure = terminal_departure;
        this.terminal_destination = terminal_destination;
    }

    public Flight() {

    }

    public String getId_airline_pfk() {
        return id_airline_pfk;
    }

    public void setId_airline_pfk(String id_airline_pfk) {
        this.id_airline_pfk = id_airline_pfk;
    }

    public int getId_route_pfk() {
        return id_route_pfk;
    }

    public void setId_route_pfk(int id_route_pfk) {
        this.id_route_pfk = id_route_pfk;
    }

    public int getId_aircraft_fk() {
        return id_aircraft_fk;
    }

    public void setId_aircraft_fk(int id_aircraft_fk) {
        this.id_aircraft_fk = id_aircraft_fk;
    }

    public int getId_flight_status_fk() {
        return id_flight_status_fk;
    }

    public void setId_flight_status_fk(int id_flight_status_fk) {
        this.id_flight_status_fk = id_flight_status_fk;
    }

    public String getTime_departure() {
        return time_departure;
    }

    public void setTime_departure(String time_departure) {
        this.time_departure = time_departure;
    }

    public String getTime_arrival() {
        return time_arrival;
    }

    public void setTime_arrival(String time_arrival) {
        this.time_arrival = time_arrival;
    }

    public String getTerminal_departure() {
        return terminal_departure;
    }

    public void setTerminal_departure(String terminal_departure) {
        this.terminal_departure = terminal_departure;
    }

    public String getTerminal_destination() {
        return terminal_destination;
    }

    public void setTerminal_destination(String terminal_destination) {
        this.terminal_destination = terminal_destination;
    }

    public int getId_airline_pfk_length() {
        return id_airline_pfk_length;
    }

    public int getTerminal_departure_length() {
        return terminal_departure_length;
    }

    public int getTerminal_destination_length() {
        return terminal_destination_length;
    }
}
