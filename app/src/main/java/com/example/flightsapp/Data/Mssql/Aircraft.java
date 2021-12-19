package com.example.flightsapp.Data.Mssql;

public class Aircraft {

    private int id_aircraft;
    private String id_model_fk;
    private String id_manufacturer_fk;
    private int serial_number;
    private String specifications;

    private int id_model_fk_length = 30;
    private int id_manufacturer_fk_length = 30;
    private int specifications_length = 3000;

    public Aircraft(int id_aircraft, String id_model_fk, String id_manufacturer_fk, int serial_number, String specifications) {
        this.id_aircraft = id_aircraft;
        this.id_model_fk = id_model_fk;
        this.id_manufacturer_fk = id_manufacturer_fk;
        this.serial_number = serial_number;
        this.specifications = specifications;
    }

    public Aircraft() {
    }

    public int getId_aircraft() {
        return id_aircraft;
    }

    public void setId_aircraft(int id_aircraft) {
        this.id_aircraft = id_aircraft;
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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public int getId_model_fk_length() {
        return id_model_fk_length;
    }

    public int getId_manufacturer_fk_length() {
        return id_manufacturer_fk_length;
    }

    public int getSpecifications_length() {
        return specifications_length;
    }
}
