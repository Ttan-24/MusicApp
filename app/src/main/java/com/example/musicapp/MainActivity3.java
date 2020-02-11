package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    private final Track [] Tracks = new Track[]{
            new Track(1, "Helena", "ES", 123, 2,false),
            new Track(2, "Rock_me", "PL", 234, 3,false),
            new Track(3, "Black_Parade", "+44", 456, 4,true),
    };

    private ListView listView;
//private ListView listView1;
    String stringCountry = "";

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
                Toast.makeText(MainActivity3.this, "Clicked item: " + Tracks[i],
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Track_Display.class);
                //intent.putExtra("name",Tracks[i]);
                startActivity(intent);
            }
        });


    }

    /*private String processData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray rows = jsonObject.getJSONArray("rows");
            JSONObject row = rows.getJSONObject(0); // index 0 is first element
            JSONArray elements = row.getJSONArray("elements");
            JSONObject element = elements.getJSONObject(0);
            JSONObject distance = element.getJSONObject("distance");
            JSONObject duration = element.getJSONObject("duration");
            return distance.getString("text") + " (" + duration.getString("text") + ")";
        } catch (JSONException jsone) {
            throw new RuntimeException(jsone);
        }
    }

    private String createUrl(final String stringCountry) {
        /* try {
            //return "https://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=top&page=1&page_size=10&country=UK&apikey=818ca763742f1f51d06c0ec29c1b2211";

            return "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" +
                    URLEncoder.encode(origin, "UTF-8") +
                    "&destinations=" +
                    URLEncoder.encode(destination, "UTF-8");

        } catch (UnsupportedEncodingException uee) {
            Log.e(TAG, uee.getMessage());
            return null;
        } */


       // return "https://api.musixmatch.com/ws/1.1/chart.tracks.get?chart_name=top&page=1&page_size=10&apikey=818ca763742f1f51d06c0ec29c1b2211&country=" +
      //          stringCountry;




    //}

    /*private String downloadUrl(final String urlAddress)
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
    } */

}
