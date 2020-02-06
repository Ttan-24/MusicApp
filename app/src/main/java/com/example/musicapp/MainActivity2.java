package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {

    private final Country [] Countries = new Country[]{
            new Country("1", "USA", "+44", 123, 2,false),
            new Country("2", "South Korea", "+91", 234, 3,false),
            new Country("3", "Japan", "+44", 456, 4,true),
    };

    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = findViewById(R.id.listView);

        listView.setAdapter(
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Countries));

    }
}
