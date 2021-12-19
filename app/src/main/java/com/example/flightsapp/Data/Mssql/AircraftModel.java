package com.example.flightsapp.Data.Mssql;

public class AircraftModel {
    private String name_model;
    private String  id_manufacturer_pfk;
    private int max_seats;
    private int max_flight_range;

    private int name_model_length = 30;
    private int id_manufacturer_pfk_length = 30;

    public AircraftModel(String name_model, String id_manufacturer_pfk, int max_seats, int max_flight_range) {
        this.name_model = name_model;
        this.id_manufacturer_pfk = id_manufacturer_pfk;
        this.max_seats = max_seats;
        this.max_flight_range = max_flight_range;
    }

    public AircraftModel() {
    }

    public String getName_model() {
        return name_model;
    }

    public void setName_model(String name_model) {
        this.name_model = name_model;
    }

    public String getId_manufacturer_pfk() {
        return id_manufacturer_pfk;
    }

    public void setId_manufacturer_pfk(String id_manufacturer_pfk) {
        this.id_manufacturer_pfk = id_manufacturer_pfk;
    }

    public int getMax_seats() {
        return max_seats;
    }

    public void setMax_seats(int max_seats) {
        this.max_seats = max_seats;
    }

    public int getMax_flight_range() {
        return max_flight_range;
    }

    public void setMax_flight_range(int max_flight_range) {
        this.max_flight_range = max_flight_range;
    }

    public int getName_model_length() {
        return name_model_length;
    }

    public int getId_manufacturer_pfk_length() {
        return id_manufacturer_pfk_length;
    }
}
