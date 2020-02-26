package com.example.musicapp.trackData;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trackEntries")
public class Track {




    @NonNull
    @PrimaryKey
    protected int common_id;
    protected int id;
    protected String track_name;
    protected String track_artist;
    private String country_code;
    private int position;
    private int date_liked;

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    private boolean favourite;

    public int getCommon_id() { return common_id;  }

    public int get_id() { return id;  }

    public String getTrack_name() {
        return track_name;
    }

    public String getTrackArtist() {
        return track_artist;
    }

      public void setTrackName(String name) {
        this.track_name = name;
    }

    public void setTrackArtist(String name) {
        this.track_artist = name;
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

    public Track(int common_id, int id, String track_name, String track_artist, String country_code, int position, int date_liked, boolean favourite) {
        this.common_id = common_id;
        this.id = id;
        this.track_name = track_name;
        this.track_artist = track_artist;
        this.country_code = country_code;
        this.position = position;
        this.date_liked = date_liked;
        this.favourite = favourite;
    }

    /*public void setFavourite(boolean choice) {
        favourite = choice;
    } */

    @Override
    public String toString() {
        return this.track_name;
    }

}