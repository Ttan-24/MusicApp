package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
                Intent toy = new Intent(MainActivity.this,Country.class);
                startActivity(toy);
            }
        });

    }

    //public void selectCountry(View view) {
    //    startActivity(new Intent(this, MainActivity.class));
    //}

}
