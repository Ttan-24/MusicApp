package com.example.musicapp.countryData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CountryDao {


    @Insert (onConflict = REPLACE)
    void insert(Country entry);

    @Query("SELECT * FROM entries ORDER BY country_code")
    List<Country> getAllEntries();

    @Query("SELECT * from entries where country_name == :country_name")
    public boolean isFavorite(String country_name);

    @Query("SELECT COUNT(1) FROM entries WHERE country_name = :country_name;")
    public boolean exists(String country_name);

    @Query("SELECT COUNT(*) FROM entries" )
    int count();

    @Delete
    void delete(Country entry);

}
