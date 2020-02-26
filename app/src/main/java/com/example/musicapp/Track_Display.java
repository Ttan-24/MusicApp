package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Track_Display extends AppCompatActivity {
    String trackName;
    String trackLyrics;
    String trackArtistName;
    TextView trackNameText;
    TextView trackLyricsText;
    TextView trackArtistNameText;
    String trackID;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("tag", "create");
        Intent getExtraData = getIntent();
        trackName = getExtraData.getStringExtra("trackName");
        trackArtistName = getExtraData.getStringExtra("trackArtist");
        trackID = getExtraData.getStringExtra("trackID");
        Log.d("tag", "onCreate: " + trackName);
        setContentView(R.layout.track__info);
        trackNameText = findViewById(R.id.trackName);
        trackNameText.setText(trackName);
        trackArtistNameText = findViewById(R.id.artistName);
        trackArtistNameText.setText(trackArtistName);

        String urlAddress = createUrl(trackID);
        Log.d("myurl", "urlAddress: " + urlAddress);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        new Track_Display.DownloadWebpageTask().execute(urlAddress);

        try {
            data = downloadUrl(urlAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        trackLyrics = processData(data);

        trackLyricsText = findViewById(R.id.Lyrics);
        trackLyricsText.setText(trackLyrics);
    }

    private String processData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject trackObject = jsonObject.getJSONObject("message");
            JSONObject bodyObject = trackObject.getJSONObject("body");
            JSONObject lyricsObject = bodyObject.getJSONObject("lyrics");

            String lyrics = lyricsObject.getString("lyrics_body");
            return lyrics;

        } catch (JSONException jsone) {
            throw new RuntimeException(jsone);
        }
    }

    private String createUrl(final String trackID) {

        return "https://api.musixmatch.com/ws/1.1/track.lyrics.get?apikey=818ca763742f1f51d06c0ec29c1b2211&track_id=" +
                trackID;

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
