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

import com.example.musicapp.countryData.CountryRoomDatabase;
import com.example.musicapp.countryData.Country;

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

        final ImageButton imageButton = view.findViewById(R.id.imageButton);
        final TextView countryName = view.findViewById(R.id.country_name);
        TextView codeCountry = view.findViewById(R.id.country_code);
        TextView wikipedia = view.findViewById(R.id.wikipedia);
        TextView lat = view.findViewById(R.id.lat);
        TextView lng = view.findViewById(R.id.lng);

        imageButton.setClickable(country.isCountry_favourite());
        countryName.setText(country.getCountry_name());
        codeCountry.setText(country.getCountry_code());
        wikipedia.setText(country.getWikipedia());
        lat.setText(Float.toString(country.getLat()));
        lng.setText(Float.toString(country.getLng()));

        ////////the user can mark and unmark as favourite/liked. The list of favourite/liked items must be accessible in another view.
        boolean isFavourite = country.isCountry_favourite();
        if (isFavourite)
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
                boolean isFavourite = country.isCountry_favourite();
                if (isFavourite) {
                    ///////the user can delete the country favourite and set the favourite icon would be unshaded to let the user know
                    /////// The country selected would be removed from the database and from the favourite list on the first screen.
                    imageButton.setImageResource(R.drawable.ic_favorite_border_red);
                    country.setCountry_favourite(false);
                    Toast.makeText(getContext(), "Deleted " + country + " to: ", Toast.LENGTH_SHORT).show();
                    CountryRoomDatabase
                            .getDatabase(getContext())
                            .countryDao().delete(country);
                } else {
                    ///////the user can mark the countries as its favourite by clicking the icon button which shades to red to let the user know.
                    /////// the country marked as favourite displays into favourite list in the 1st screen
                    imageButton.setImageResource(R.drawable.favourite_icon);
                    country.setCountry_favourite(true);
                    Toast.makeText(getContext(), "Checked " + country + " to: ", Toast.LENGTH_SHORT).show();
                    CountryRoomDatabase
                            .getDatabase(getContext())
                            .countryDao().insert(country);

                }

            }
        });

        return view;
    }



}
