package com.example.musicapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface BlogDao {


    @Insert (onConflict = REPLACE)
    void insert(Country entry);

    @Query("SELECT * FROM entries ORDER BY country_code")
    List<Country> getAllEntries();

    //@Query("SELECT * from entries where country_favourite == 1")
    //public boolean isFavorite(String country_name);

    @Delete
    void delete(Country entry);
}
