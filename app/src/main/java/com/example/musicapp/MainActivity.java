package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.data.BlogRoomDatabase;
import com.example.musicapp.data.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        button1 = findViewById(R.id.select_country);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                Intent toy = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(toy);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        List<Country> allEntries = BlogRoomDatabase
                .getDatabase(this)
                .blogDao()
                .getAllEntries();
        final Country[] countryArray = new Country[allEntries.size()];
        int i = 0;
        for (Country country : allEntries
             ) {
            countryArray[i] = country;
            i++;
        }
        final ArrayAdapter entriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, countryArray);
        listView.setAdapter(entriesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked item: " + countryArray[i].getCountry_name(),    // If i clicked on a country then it will take me to song tracks
                        Toast.LENGTH_SHORT).show();
                //Country entry = (Country) listView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("Code", countryArray[i].getCountry_code().toString());
                Log.d("tag", "adding code: " + countryArray[i].getCountry_code().toString());
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });
    }
}
