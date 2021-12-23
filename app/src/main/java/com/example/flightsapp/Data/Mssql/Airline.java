package com.example.flightsapp.Data.Mssql;

import java.util.Objects;

public class Airline {
    private String id_airline;
    private String name_airline;
    private int approx_aircrafts_amount;
    private String origin_country;

    private int id_airline_length = 3;
    private int name_airline_length = 60;
    private int origin_country_length = 100;

    public Airline(String id_airline, String name_airline, int approx_aircrafts_amount, String origin_country) {
        this.id_airline = id_airline;
        this.name_airline = name_airline;
        this.approx_aircrafts_amount = approx_aircrafts_amount;
        this.origin_country = origin_country;
    }

    public Airline() {

    }

    @Override
    public String toString() {
        return name_airline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline)) return false;
        Airline airline = (Airline) o;
        return getApprox_aircrafts_amount() == airline.getApprox_aircrafts_amount() && getId_airline_length() == airline.getId_airline_length() && getName_airline_length() == airline.getName_airline_length() && getOrigin_country_length() == airline.getOrigin_country_length() && Objects.equals(getId_airline(), airline.getId_airline()) && Objects.equals(getName_airline(), airline.getName_airline()) && Objects.equals(getOrigin_country(), airline.getOrigin_country());
    }

    public String getId_airline() {
        return id_airline;
    }

    public void setId_airline(String id_airline) {
        this.id_airline = id_airline;
    }

    public String getName_airline() {
        return name_airline;
    }

    public void setName_airline(String name_airline) {
        this.name_airline = name_airline;
    }

    public int getApprox_aircrafts_amount() {
        return approx_aircrafts_amount;
    }

    public void setApprox_aircrafts_amount(int approx_aircrafts_amount) {
        this.approx_aircrafts_amount = approx_aircrafts_amount;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public int getId_airline_length() {
        return id_airline_length;
    }

    public int getName_airline_length() {
        return name_airline_length;
    }

    public int getOrigin_country_length() {
        return origin_country_length;
    }

}
