package com.example.musicapp;

public class Track {
    private int common_id;
    private String track_name;
    private String country_code;
    private int position;
    private int date_liked;
    private boolean favourite;

    public Track(int common_id, String track_name, String country_code, int position, int date_liked, boolean favourite) {
        this.common_id = common_id;
        this.track_name = track_name;
        this.country_code = country_code;
        this.position = position;
        this.date_liked = date_liked;
        this.favourite = favourite;
    }

    public int getCommon_id() { return common_id;  }

    public String getTrack_name() {
        return track_name;
    }

    public void setTrackName(String name) {
        this.track_name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public int getPosition() {
        return position;
    }

    public int getDate_liked() {
        return date_liked;
    }

    public boolean isFavourite() {
        return favourite;
    }

    /*public void setFavourite(boolean choice) {
        favourite = choice;
    } */

    @Override
    public String toString() {
        return this.track_name;
    }

}