package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
        final ArrayAdapter entriesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, allEntries);
        listView.setAdapter(entriesAdapter);
    }

    //public void selectCountry(View view) {
    //    startActivity(new Intent(this, MainActivity.class));
    //}

}
