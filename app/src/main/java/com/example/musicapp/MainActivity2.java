package com.example.musicapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.data.BlogDao;
import com.example.musicapp.data.BlogRoomDatabase;
import com.example.musicapp.data.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import androidx.annotation.NonNull;
//import android.view.MenuItem;

/*

Activity 1 - Reads from room DB but only populates listview with favourited countries, has add country button
Activity 2 - Shows all countries, with favourite check boxes, checking/unchecked boxes updated countries table.
 */

public class MainActivity2 extends AppCompatActivity {
    //private TextView mTextMessage;


    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    }; */


    /*public boolean countryIsFavourite(String countryName) {
        boolean isFavourite = BlogRoomDatabase.getDatabase(getApplicationContext()).blogDao().isFavorite(countryName);
        Log.d("", "hmmmm" + isFavourite);
        return isFavourite;
    }*/

    private final Country [] Countries = new Country[]{
            new Country("AR", "Argentina", "https://en.wikipedia.org/wiki/Argentina", 123, 2,BlogRoomDatabase.getDatabase(this).blogDao().exists("Argentina")),
            new Country("AU", "Australia", "https://en.wikipedia.org/wiki/Australia", 234, 3,BlogRoomDatabase.getDatabase(this).blogDao().exists("Australia")),
            new Country("AT", "Austria", "https://en.wikipedia.org/wiki/Austria", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Austria")),
            new Country("BE", "Belgium", "https://en.wikipedia.org/wiki/Belgium", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Belgium")),
            new Country("CA", "Canada", "https://en.wikipedia.org/wiki/Canada", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Canada")),
            new Country("DK", "Denmark", "https://en.wikipedia.org/wiki/Denmark", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Denmark")),
            new Country("EG", "Egypt", "https://en.wikipedia.org/wiki/Egypt", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Egypt")),
            new Country("FI", "Finland", "https://en.wikipedia.org/wiki/Finland", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Finland")),
            new Country("FR", "France", "https://en.wikipedia.org/wiki/France", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("France")),
            new Country("DE", "Germany", "https://en.wikipedia.org/wiki/Germany", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Germany")),
            new Country("GR", "Greece", "https://en.wikipedia.org/wiki/Greece", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Greece")),
            new Country("HU", "Hungary", "https://en.wikipedia.org/wiki/Hungary", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Hungary")),
            new Country("IN", "India", "https://en.wikipedia.org/wiki/India", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("India")),
            new Country("IE", "Ireland", "https://en.wikipedia.org/wiki/Ireland", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Ireland")),
            new Country("IT", "Italy", "https://en.wikipedia.org/wiki/Italy", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Italy")),
            new Country("JP", "Japan", "https://en.wikipedia.org/wiki/Japan", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Japan")),
            new Country("SK", "South Korea", "https://en.wikipedia.org/wiki/South_Korea", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("South Korea")),
            new Country("ES", "Spain", "https://en.wikipedia.org/wiki/Spain", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Spain")),
            new Country("MA", "Morocco", "https://en.wikipedia.org/wiki/Morocco", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Morocco")),
            new Country("NL", "Netherlands", "https://en.wikipedia.org/wiki/Netherlands", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Netherlands")),
            new Country("UK", "United Kingdom", "https://en.wikipedia.org/wiki/United_Kingdom", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("United Kingdom")),
            new Country("US", "United States of America", "https://en.wikipedia.org/wiki/United_States", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("United States of America")),
    };

   /* @Override
    protected void onResume() {
        super.onResume();

        List<Country> allEntries = BlogRoomDatabase
                .getDatabase(this)
                .blogDao()
                .getAllEntries();
        final ArrayAdapter entriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, allEntries);
        listView.setAdapter(entriesAdapter);
    }*/

    private ListView listView;
   // private TextView TextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //TextName = findViewById(R.id.country_name);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new Country_Adapter(this, Countries));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity2.this, "Clicked item: " + Countries[i],    // If i clicked on a country then it will take me to song tracks
                        Toast.LENGTH_SHORT).show();
                //Country entry = (Country) listView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);  // Takes the user from one screen to another
                intent.putExtra("Code",Countries[i].getCountry_code().toString());   // Getting the country code from here for the track list of that country
                Log.d("tag", "adding code: " + "goes here");
                Log.d("tag", "adding code: " + Countries[i].getCountry_code().toString());
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });
    }
}