package com.example.musicapp.trackData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TrackDao {

    @Insert(onConflict = REPLACE)
    void insert(Track entry);

    @Query("SELECT * FROM trackEntries ORDER BY track_name")
    List<Track> getAllEntries();

    @Query("SELECT * from trackEntries where track_name == :track_name")
    public boolean isFavorite(String track_name);

    @Query("SELECT COUNT(1) FROM trackEntries WHERE id = :track_name;")
    public boolean exists(String track_name);

    @Query("SELECT COUNT(1) FROM trackEntries WHERE id = :id;")
    public boolean exists(int id);

    @Query("SELECT COUNT(*) FROM trackEntries" )
    int count();

    @Delete
    void delete(Track entry);
}
