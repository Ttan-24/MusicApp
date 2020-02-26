package com.example.musicapp.trackData;

public class TrackRoomDatabase {
    private static final TrackRoomDatabase ourInstance = new TrackRoomDatabase();

    public static TrackRoomDatabase getInstance() {
        return ourInstance;
    }

    private TrackRoomDatabase() {
    }
}
