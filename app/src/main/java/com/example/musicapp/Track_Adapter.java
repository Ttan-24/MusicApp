package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;

import com.example.musicapp.countryData.CountryRoomDatabase;
import com.example.musicapp.trackData.Track;
import com.example.musicapp.trackData.TrackRoomDatabase;

import java.util.ArrayList;

// the Track_Adapter can only be used to manage items of type Track
public class Track_Adapter extends ArrayAdapter<Track> {
    public Track_Adapter (Context context, ArrayList<Track> Tracks){
        super(context, R.layout.activity_track, Tracks);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.activity_track, null);
        }

        final Track track = getItem(position);

        final ImageButton imageButton = view.findViewById(R.id.imageButton2);
        TextView trackName = view.findViewById(R.id.track_name);
        TextView commonId = view.findViewById(R.id.common_id);
        TextView countryCode = view.findViewById(R.id.country_code);
        //TextView pos = view.findViewById(R.id.position);
        //TextView dateLiked = view.findViewById(R.id.date_liked);

        imageButton.setClickable(track.isFavourite());
        trackName.setText(track.getTrack_name());
        commonId.setText(Integer.toString(track.getCommon_id()));
        countryCode.setText(track.getCountry_code());
        //pos.setText(Integer.toString(track.getPosition()));
        //dateLiked.setText(Integer.toString(track.getDate_liked()));


        //imageButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Toast.makeText(getContext(), "Checked " + track + " to: " , Toast.LENGTH_SHORT).show();
//
  //          }
    //    });


        ////////the user can mark and unmark as favourite/liked. The list of favourite/liked items must be accessible in another view.
        boolean isTrackFavourite = TrackRoomDatabase.getDatabase(getContext()).trackDao().exists(track.get_id());
        if (isTrackFavourite)
        {
            imageButton.setImageResource(R.drawable.favourite_icon);
        }
        else
        {
            imageButton.setImageResource(R.drawable.ic_favorite_border_red);
        }

        ////on clicking the image button icon
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrackFavourite = track.isFavourite();
                if (isTrackFavourite) {
                    ///////the user can delete the country favourite and set the favourite icon would be unshaded to let the user know
                    /////// The country selected would be removed from the database and from the favourite list on the first screen.
                    imageButton.setImageResource(R.drawable.ic_favorite_border_red);
                    track.setFavourite(false);
                    Toast.makeText(getContext(), "Deleted " + track + " to: ", Toast.LENGTH_SHORT).show();
                    TrackRoomDatabase
                            .getDatabase(getContext())
                            .trackDao().delete(track);
                } else {
                    ///////the user can mark the countries as its favourite by clicking the icon button which shades to red to let the user know.
                    /////// the country marked as favourite displays into favourite list in the 1st screen
                    imageButton.setImageResource(R.drawable.favourite_icon);
                    track.setFavourite(true);
                    Toast.makeText(getContext(), "Checked " + track + " to: ", Toast.LENGTH_SHORT).show();
                    TrackRoomDatabase
                            .getDatabase(getContext())
                            .trackDao().insert(track);

                }

            }
        });
        return view;
    }

}
