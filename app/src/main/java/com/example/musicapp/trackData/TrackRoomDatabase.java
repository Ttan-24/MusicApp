package com.example.musicapp.trackData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Track.class}, version = 1)
public abstract class TrackRoomDatabase extends RoomDatabase {
    public abstract TrackDao trackDao(); // provides an abstract method that returns an instance of that interface. It's abstract because this method
    // will never be called directly. Instead, there will be some generated code that's created by the Room database in the background and that's the
    // version of the method that you'll be calling

    private static volatile TrackRoomDatabase INSTANCE;  // it can only be referenced from main memory

    public static TrackRoomDatabase getDatabase(final Context context) {    // to create the database object - need context
        if (INSTANCE == null) {                                            // check whether the instance is null
            synchronized (TrackRoomDatabase.class) {                        // access is synchronised so that we will have no race conditions between different
                // different areas of the application
                if (INSTANCE == null) {                                    // check again if the instance is still null and then create the database reference
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TrackRoomDatabase.class, "track_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
