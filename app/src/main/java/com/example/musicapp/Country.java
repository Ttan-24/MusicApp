package com.example.musicapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries")
public class Country {
    public Country(String country_code, String country_name, String wikipedia, float lat, float lng, boolean country_favourite) {
        this.country_code = country_code;
        this.country_name = country_name;
        this.wikipedia = wikipedia;
        this.lat = lat;
        this.lng = lng;
        this.country_favourite = country_favourite;
    }

    @PrimaryKey (autoGenerate = true)
    private int rowID;
    private String country_code;
    private String country_name;
    private String wikipedia;
    private float lat;
    private float lng;
    private boolean country_favourite;

    public String getCountry_code() {
        return country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int newID) {
        rowID = newID;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public boolean isCountry_favourite() {
        return country_favourite;
    }

    @Override
    public String toString() {
        return country_name;
    }
}
