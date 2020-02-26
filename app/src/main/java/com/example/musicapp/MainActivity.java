package com.example.musicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.countryData.CountryRoomDatabase;
import com.example.musicapp.countryData.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public Button countryButton;
    public Button trackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        ////////When select button is clicked it takes the user from 1 screen to another screen
        countryButton = findViewById(R.id.select_country);
        countryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent changeScreen = new Intent(MainActivity.this, ViewAllCountries.class);
                startActivity(changeScreen);
            }
        });

        trackButton = findViewById(R.id.favorite_tracks);
        trackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent changeScreen = new Intent(MainActivity.this, ViewFavoriteTrackList.class);
                startActivity(changeScreen);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        ///////////Displays all the entries of the favourite database
        List<Country> allEntries = CountryRoomDatabase
                .getDatabase(this)
                .countryDao()
                .getAllEntries();
        final Country[] countryArray = new Country[allEntries.size()];
        int i = 0;
        for (Country country : allEntries
             ) {
            countryArray[i] = country;
            i++;
        }

        //////////Sets the adapter for the favourite countries
        final ArrayAdapter entriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countryArray);
        listView.setAdapter(entriesAdapter);

        ////////// If the user clicks in the favourite list of countries then it will take the user directly to the song tracks screen
        ///////// intent put extra lets to choose the countryData to put in another activity by storing it in the intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked item: " + countryArray[i].getCountry_name(),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ViewTopTracks.class);
                intent.putExtra("Code", countryArray[i].getCountry_code().toString());
                Log.d("tag", "adding code: " + countryArray[i].getCountry_code().toString());
                startActivity(intent);
            }
        });
    }
}
