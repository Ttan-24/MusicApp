package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private final Track [] Tracks = new Track[]{
            new Track(1, "Helena", "+44", 123, 2,false),
            new Track(2, "Rock_me", "+91", 234, 3,false),
            new Track(3, "Black_Parade", "+44", 456, 4,true),
    };

    private ListView listView;
//private ListView listView1;

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
                Toast.makeText(MainActivity2.this, "Clicked item: " + Tracks[i],
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Track_Display.class);
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });
    }


}
