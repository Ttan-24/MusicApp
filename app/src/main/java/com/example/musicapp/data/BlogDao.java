package com.example.musicapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BlogDao {
    @Insert
    void insert(Country entry);

    @Query("SELECT * FROM entries ORDER BY country_code")
    List<Country> getAllEntries();

    @Delete
    void delete(Country entry);
}
