package com.example.musicapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.trackData.Track;

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

public class ViewTopTracks extends AppCompatActivity {
    
    private ArrayList<Track> Tracks = new ArrayList<Track>();

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
        ////////////EXPLANATION NEEDED?
        Intent getExtraData = getIntent();
        stringCountry = getExtraData.getStringExtra("Code");
        Log.d("tag", "onCreate: " + stringCountry);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new Track_Adapter(this, Tracks));

        //////////////When the user clicks on the selected track
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int i, long l) {
                Toast.makeText(ViewTopTracks.this, "Clicked item: " + Tracks.get(i),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Track_Display.class);
                // puts extra information of track inside the intent
                intent.putExtra("trackName",Tracks.get(i).getTrack_name());
                intent.putExtra("trackID",Integer.toString(Tracks.get(i).get_id()));
                intent.putExtra("trackArtist",Tracks.get(i).getTrackArtist());
                startActivity(intent);
            }
        });



        Log.d("tag", "onCreate: " + stringCountry);

        String urlAddress = createUrl(stringCountry);
        Log.d("myurl", "urlAddress: " + urlAddress);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new ViewTopTracks.DownloadWebpageTask().execute(urlAddress);

        try {
            data = downloadUrl(urlAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < 10; i ++) {
            // Adding track to the track list
            Tracks.add(processData(data, i));

            //Tracks[i] = (processData(countryData, i));
            Log.d("add track index", Integer.toString(i));
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
            // main json object
            JSONObject jsonObject = new JSONObject(data);

            // json functions
            JSONObject trackObject = jsonObject.getJSONObject("message");
            JSONObject bodyObject = trackObject.getJSONObject("body");
            JSONArray track_list = bodyObject.getJSONArray("track_list");
            JSONObject trackObj = track_list.getJSONObject(i);
            JSONObject trackObj2 = trackObj.getJSONObject("track");

            // track information
            int common_id = Integer.parseInt(trackObj2.getString("commontrack_id"));
            int id = Integer.parseInt(trackObj2.getString("track_id"));
            String name = trackObj2.getString("track_name");
            String track_artist = trackObj2.getString("artist_name");
            Log.d("Adding new track: ", name);

            // Construct track object
            Track newTrack = new Track(common_id, id, name, track_artist, stringCountry, 123, 2, false);
            return newTrack;

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
                // ... then call the method that connects and fetches the countryData ...
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