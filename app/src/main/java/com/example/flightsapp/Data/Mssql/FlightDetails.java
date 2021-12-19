package com.example.flightsapp.Data.Mssql;

public class FlightDetails {
    //flight

    private String id_airline_pfk;
    private int id_route_pfk;
    private int id_aircraft_fk;
    private int id_flight_status_fk;
    private String time_departure;
    private String time_arrival;
    private String terminal_departure;
    private String terminal_destination;
    //route
    private String airport_destination;
    private String airport_departure;
    private String airport_transfer;
    //flight status
    private String name_status;
    //aircraft
    private String id_model_fk;
    private String id_manufacturer_fk;
    private int serial_number;
    //airline
    private String name_airline;

    public FlightDetails(String id_airline_pfk, int id_route_pfk, int id_aircraft_fk, int id_flight_status_fk, String time_departure, String time_arrival, String terminal_departure, String terminal_destination, String airport_destination, String airport_departure, String airport_transfer, String name_status, String id_model_fk, String id_manufacturer_fk, int serial_number, String name_airline) {
        this.id_airline_pfk = id_airline_pfk;
        this.id_route_pfk = id_route_pfk;
        this.id_aircraft_fk = id_aircraft_fk;
        this.id_flight_status_fk = id_flight_status_fk;
        this.time_departure = time_departure;
        this.time_arrival = time_arrival;
        this.terminal_departure = terminal_departure;
        this.terminal_destination = terminal_destination;
        this.airport_destination = airport_destination;
        this.airport_departure = airport_departure;
        this.airport_transfer = airport_transfer;
        this.name_status = name_status;
        this.id_model_fk = id_model_fk;
        this.id_manufacturer_fk = id_manufacturer_fk;
        this.serial_number = serial_number;
        this.name_airline = name_airline;
    }

    public FlightDetails() {

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

    public String getAirport_destination() {
        return airport_destination;
    }

    public void setAirport_destination(String airport_destination) {
        this.airport_destination = airport_destination;
    }

    public String getAirport_departure() {
        return airport_departure;
    }

    public void setAirport_departure(String airport_departure) {
        this.airport_departure = airport_departure;
    }

    public String getAirport_transfer() {
        return airport_transfer;
    }

    public void setAirport_transfer(String airport_transfer) {
        this.airport_transfer = airport_transfer;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }

    public String getId_model_fk() {
        return id_model_fk;
    }

    public void setId_model_fk(String id_model_fk) {
        this.id_model_fk = id_model_fk;
    }

    public String getId_manufacturer_fk() {
        return id_manufacturer_fk;
    }

    public void setId_manufacturer_fk(String id_manufacturer_fk) {
        this.id_manufacturer_fk = id_manufacturer_fk;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public String getName_airline() {
        return name_airline;
    }

    public void setName_airline(String name_airline) {
        this.name_airline = name_airline;
    }
}