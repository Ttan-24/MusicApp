package com.example.musicapp;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
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

        CheckBox ImageButton = view.findViewById(R.id.ImageButton);
        TextView track_name = view.findViewById(R.id.track_name);
        TextView common_id = view.findViewById(R.id.common_id);

        ImageButton.setChecked(track.isFavourite());
        track_name.setText(track.getTrack_name());
        common_id.setText(track.getCommontrack_id());

        ImageButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(CompoundButton cb, boolean b) {
                        Toast.makeText(getContext(), "Checked " + track + " to: " + b, Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }

}
