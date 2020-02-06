package com.example.musicapp;

import android.os.Bundle;

//import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.NonNull;

//import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

    private final Track [] Tracks = new Track[]{
            new Track(1, "Helena", "+44", 123, 2,false),
            new Track(2, "Rock_me", "+91", 234, 3,false),
            new Track(3, "Black_Parade", "+44", 456, 4,true),
    };

    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new Track_Adapter(this, Tracks));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity.this, "Clicked item: " + Tracks[i],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
