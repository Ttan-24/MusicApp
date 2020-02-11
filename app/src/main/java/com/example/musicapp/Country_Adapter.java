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

public class Country_Adapter extends ArrayAdapter<Country> {
    public Country_Adapter (Context context, Country[] Countries){
        super(context, R.layout.activity_country, Countries);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.activity_country, null);
        }

        final Country country = getItem(position);

        final CheckBox checkbox = view.findViewById(R.id.checkbox);
        TextView countryName = view.findViewById(R.id.country_name);
        TextView codeCountry = view.findViewById(R.id.country_code);
        TextView wikipedia = view.findViewById(R.id.wikipedia);
        TextView lat = view.findViewById(R.id.lat);
        TextView lng = view.findViewById(R.id.lng);

        checkbox.setChecked(country.isCountry_favourite());
        countryName.setText(country.getCountry_name());
        codeCountry.setText(country.getCountry_code());
        wikipedia.setText(country.getWikipedia());
        lat.setText(Float.toString(country.getLat()));
        lng.setText(Float.toString(country.getLng()));


        checkbox.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override public void onCheckedChanged(CompoundButton cb, boolean b) {
                        Toast.makeText(getContext(), "Checked " + country + " to: " + b, Toast.LENGTH_SHORT).show();
                        BlogRoomDatabase
                                .getDatabase(getContext())
                                .blogDao().insert(country);//)new Country("1",country.,"http://wikipedia.com",0.0,0.0,true));//Country(String country_code, String country_name, String wikipedia, float lat, float lng, boolean country_favourite) {
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
