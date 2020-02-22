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
            new Country("US", "USA", "https://www.wikipedia.org/", 123, 2,BlogRoomDatabase.getDatabase(this).blogDao().exists("USA")),
            new Country("SK", "South Korea", "+91", 234, 3,BlogRoomDatabase.getDatabase(this).blogDao().exists("South Korea")),
            new Country("JP", "Japan", "+44", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("Japan")),
            new Country("IL", "United Kingdom", "+44", 456, 4,BlogRoomDatabase.getDatabase(this).blogDao().exists("United Kingdom")),
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
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("Code",Countries[i].getCountry_code().toString());
                Log.d("tag", "adding code: " + "goes here");
                Log.d("tag", "adding code: " + Countries[i].getCountry_code().toString());
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });
    }
}