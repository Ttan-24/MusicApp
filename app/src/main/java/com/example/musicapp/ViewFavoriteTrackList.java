package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.musicapp.trackData.Track;
import com.example.musicapp.trackData.TrackRoomDatabase;

import java.util.List;

public class ViewFavoriteTrackList extends AppCompatActivity {

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        listView = findViewById(R.id.listView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ///////////Displays all the entries of the favourite database
        List<Track> allEntries = TrackRoomDatabase
                .getDatabase(this)
                .trackDao()
                .getAllEntries();
        final Track[] trackArray = new Track[allEntries.size()];
        int i = 0;
        for (Track track : allEntries
        ) {
            trackArray[i] = track;
            i++;
        }

        //////////Sets the adapter for the favourite countries
        final ArrayAdapter entriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, trackArray);
        listView.setAdapter(entriesAdapter);

        ////////// If the user clicks in the favourite list of countries then it will take the user directly to the song tracks screen
        ///////// intent put extra lets to choose the countryData to put in another activity by storing it in the intent
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(ViewFavoriteTrackList.this, "Clicked item: " + trackArray[i],
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Track_Display.class);
                // puts extra information of track inside the intent
                intent.putExtra("trackName",trackArray[i].getTrack_name());
                intent.putExtra("trackID",Integer.toString(trackArray[i].get_id()));
                intent.putExtra("trackArtist",trackArray[i].getTrackArtist());
                startActivity(intent);
            }
        });
    }
}


