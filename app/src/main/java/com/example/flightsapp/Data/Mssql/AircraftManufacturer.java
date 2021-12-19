package com.example.flightsapp.Data.Mssql;

import java.io.Serializable;

public class AircraftManufacturer implements Serializable {

    private String name_manufacturer;

    private String name_manufacturer_long;

    private String origin_country;

    int name_manufacturer_length = 30;
    int name_manufacturer_long_length = 100;
    int origin_country_length = 100;


    public AircraftManufacturer(String name_manufacturer, String name_manufacturer_long, String origin_country){
        this.name_manufacturer = name_manufacturer;
        this.name_manufacturer_long = name_manufacturer_long;
        this.origin_country = origin_country;
    }

    public String getNameManufacturer(){
        return this.name_manufacturer;
    }

    public String getNameManufacturerLong() {
        return this.name_manufacturer_long;
    }

    public String getOriginCountry() { return this.origin_country; }

    public int getNameManufacturerLength() {
        return name_manufacturer_length;
    }

    public int getNameManufacturerLongLength() {
        return name_manufacturer_long_length;
    }

    public int getOriginCountryLength() {
        return origin_country_length;
    }
}
