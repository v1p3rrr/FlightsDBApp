package com.example.flightsapp.Data.Mssql;

import java.io.Serializable;
import java.util.Objects;

public class Route implements Serializable {

    private int id_route;

    private String airport_destination;

    private String airport_departure;

    private String airport_transfer;

    int id_route_length = 3;
    int airport_destination_length = 199;
    int airport_departure_length = 199;
    int airport_transfer_length = 199;

    public Route(int id_route, String airport_destination, String airport_departure, String airport_transfer) {
        this.id_route = id_route;
        this.airport_destination = airport_destination;
        this.airport_departure = airport_departure;
        this.airport_transfer = airport_transfer;
    }

    public Route(){

    }

    @Override
    public String toString() {
        return ""+id_route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return getId_route() == route.getId_route() && getId_route_length() == route.getId_route_length() && getAirport_destination_length() == route.getAirport_destination_length() && getAirport_departure_length() == route.getAirport_departure_length() && getAirport_transfer_length() == route.getAirport_transfer_length() && Objects.equals(getAirport_destination(), route.getAirport_destination()) && Objects.equals(getAirport_departure(), route.getAirport_departure()) && Objects.equals(getAirport_transfer(), route.getAirport_transfer());
    }

    public int getId_route() {
        return id_route;
    }

    public void setId_route(int id_route) {
        this.id_route = id_route;
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

    public int getId_route_length() {
        return id_route_length;
    }

    public int getAirport_destination_length() {
        return airport_destination_length;
    }

    public int getAirport_departure_length() {
        return airport_departure_length;
    }

    public int getAirport_transfer_length() {
        return airport_transfer_length;
    }

}
