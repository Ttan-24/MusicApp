package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.NonNull;

//import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
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

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new Country_Adapter(this, Countries));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked item: " + Countries[i],
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });

    }

}
