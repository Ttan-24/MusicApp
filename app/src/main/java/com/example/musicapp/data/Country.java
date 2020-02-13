package com.example.musicapp.data;

import androidx.annotation.NonNull;
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

    @NonNull
    @PrimaryKey
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

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public boolean isCountry_favourite() {
        return country_favourite;
    }

    public void setCountry_favourite(boolean country_favourite) {
        this.country_favourite = country_favourite;
    }



    @Override
    public String toString() {
        return country_name;
    }
}
