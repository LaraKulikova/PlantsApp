package com.uni.plantsapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlantDescriptionFragment extends Fragment {

    private TextView plantDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_description, container, false);
        plantDescription = view.findViewById(R.id.plant_description);

        if (getArguments() != null) {
            String plant = getArguments().getString("plant");
            plantDescription.setText("Описание: " + plant);
        }

        return view;
    }
}
