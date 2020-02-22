package com.example.musicapp;

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
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity3 extends AppCompatActivity {

   //ArrayList<Track> tracksList = null;

    /*private ArrayList<Track> Tracks = new ArrayList<Track>(Arrays.asList(
            new Track(1, "Helena", "ES", 123, 2, false),
            new Track(2, "Rock_me", "PL", 234, 3, false),
            new Track(3, "Black_Parade", "+44", 456, 4, true)
    ));*/

    private ArrayList<Track> Tracks = new ArrayList<Track>(Arrays.asList(
            new Track(1, "Helena", "ES", 123, 2, false),
            new Track(2, "Rock_me", "PL", 234, 3, false),
            new Track(3, "Black_Parade", "+44", 456, 4, true)
    ));

    //public Track[] Tracks = new Track[10];
    //Tracks.add(new Track(1, "Helena", "ES", 123, 2, false));

    private ListView listView;
    //private ListView listView1;
    String stringCountry = "";
    private String data;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent getExtraData = getIntent();
        stringCountry = getExtraData.getStringExtra("Code");
        Log.d("tag", "onCreate: " + stringCountry);

        //String urlAddress = createUrl(stringCountry);
        //Log.d("myurl", "urlAddress: " + urlAddress);

        //new DownloadWebpageTask().execute(urlAddress);
        //BottomNavigationView navView = findViewById(R.id.nav_view);
        //mTextMessage = findViewById(R.id.message);
        //navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new Track_Adapter(this, Tracks));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(MainActivity3.this, "Clicked item: " + Tracks.get(i),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Track_Display.class);
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });



        Log.d("tag", "onCreate: " + stringCountry);

        String urlAddress = createUrl(stringCountry);
        Log.d("myurl", "urlAddress: " + urlAddress);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new MainActivity3.DownloadWebpageTask().execute(urlAddress);

        try {
            data = downloadUrl(urlAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < 10; i ++) {
            Tracks.add(processData(data, i));

            //Tracks[i] = (processData(data, i));
            Log.d("gonna add", Integer.toString(i));
            //Tracks[i] = new Track(1, "My Song", "ES", 123, 2, false);
            //Log.d("array", arr[i]);

            //TextView.append(arr[i]);
            //myTextView.append(myArray[i]);
        }
        Log.d("array", Tracks.toString());

        //Log.d("tras", );
        listView = findViewById(R.id.listView);
        listView.setAdapter(new Track_Adapter(this, Tracks));

    }


    private Track processData(String data, int i) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject trackObject = jsonObject.getJSONObject("message");
            JSONObject bodyObject = trackObject.getJSONObject("body");
            JSONArray track_list = bodyObject.getJSONArray("track_list");
            //JSONObject trackObj1 = null;
            // for loop
           // tracksList = new ArrayList<>();
           // for (int m = 0; m < track_list.length(); m++){
                //trackObj1 = (JSONObject) track_list.get(i);

            JSONObject trackObj = track_list.getJSONObject(i);
            JSONObject trackObj2 = trackObj.getJSONObject("track");
            String name = trackObj2.getString("track_name");
            //int common_id = Integer.parseInt(trackObj2.getString("commontrack_id"));
            Log.d("Adding new track: ", name);
            Track newTrack = new Track(1, name, "XX", 123, 2, false);
            return newTrack;

                //tracksList.add(new Track(Integer.valueOf(trackObj1.optString("track_id")), trackObj1.optString("track_name", trackObj1.optString("track_rating")));
            //}

            //listView.setAdapter(new Track_Adapter(MainActivity3.this, tracksList));
            //JSONObject trackObj = track_list.getJSONObject(i);
            //JSONObject trackObj2 = trackObj.getJSONObject("track");
           // String name = trackObj2.getString("track_name");
            //Log.d("json", name);
            // JSONArray rows = jsonObject.getJSONArray("rows");
            //JSONObject row = rows.getJSONObject(0); // index 0 is first element
            //JSONArray elements = row.getJSONArray("elements");
            //JSONObject element = elements.getJSONObject(0);
            //JSONObject distance = element.getJSONObject("distance");
            //JSONObject duration = element.getJSONObject("duration");
            //return distance.getString("text") + " (" + duration.getString("text") + ")";
            //return name;


        } catch (JSONException jsone) {
            throw new RuntimeException(jsone);
        }
    }

    private String createUrl(final String stringCountry) {

        return "https://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=top&page=1&page_size=10&apikey=818ca763742f1f51d06c0ec29c1b2211&country=" +
                stringCountry;

    }

    private String downloadUrl(final String urlAddress)
            throws IOException {
        InputStream inputStream = null;

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // this is the default anyway
            conn.setDoInput(true); // connections can be used for input or output
            conn.connect(); // connects and starts the query
            int response = conn.getResponseCode(); // should be 200 if all is OK
            Log.d("response", "The response is: " + response);
            inputStream = conn.getInputStream();

            // handle response
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
            // the string builder is used to collect all the read bytes into a single string
            final StringBuilder stringBuilder = new StringBuilder();
            String line; // used as a temporary buffer
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } finally {
            // Makes sure that the InputStream is closed after the app is finished using it.
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override protected String doInBackground(String... urls) {
            // execute in background, in separate thread – cannot edit the UI
            try {
                // ... then call the method that connects and fetches the data ...
                return downloadUrl(urls[0]);
            } catch (IOException ioe) {
                return "Error: " + ioe;
            }
        }
        @Override protected void onPostExecute(String result) {
            // execute after the thread finishes – can edit the UI
            // ... and finally, update the TextView accordingly
            //textViewJSON.setText(result);
        }
    }
}