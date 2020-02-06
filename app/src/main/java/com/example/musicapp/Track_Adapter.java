package com.example.musicapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

// the Track_Adapter can only be used to manage items of type Track
public class Track_Adapter extends ArrayAdapter<Track> {
    public Track_Adapter (Context context, Track[] Tracks){
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

        CheckBox checkbox = view.findViewById(R.id.checkbox);
        TextView trackName = view.findViewById(R.id.track_name);
        TextView commonId = view.findViewById(R.id.common_id);
        TextView countryCode = view.findViewById(R.id.country_code);
        TextView pos = view.findViewById(R.id.position);
        TextView dateLiked = view.findViewById(R.id.date_liked);

        checkbox.setChecked(track.isFavourite());
        trackName.setText(track.getTrack_name());
        commonId.setText(Integer.toString(track.getCommon_id()));
        countryCode.setText(track.getCountry_code());
        pos.setText(Integer.toString(track.getPosition()));
        dateLiked.setText(Integer.toString(track.getDate_liked()));


        checkbox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(CompoundButton cb, boolean b) {
                        Toast.makeText(getContext(), "Checked " + track + " to: " + b, Toast.LENGTH_SHORT).show();
                    }
                });

        /*ImageButton.setChecked(track.isFavourite());
        track_name.setText(track.getTrack_name());
        common_id.setText(track.getCommontrack_id());

        ImageButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(CompoundButton cb, boolean b) {
                        Toast.makeText(getContext(), "Checked " + track + " to: " + b, Toast.LENGTH_SHORT).show();
                    }
                }); */

        /*ImageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {
                //Set the button's appearance
                button.setSelected(!button.isSelected());

                if (button.isSelected()) {
                    track.setFavourite(true);
                } else {
                    track.setFavourite(false);
                }

            }

        });  */
        return view;
    }

}
