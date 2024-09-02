package com.uni.plantsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PlantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        if (savedInstanceState == null) {
            String plant = getIntent().getStringExtra("plant");
            PlantDescriptionFragment descriptionFragment = new PlantDescriptionFragment();
            Bundle args = new Bundle();
            args.putString("plant", plant);
            descriptionFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, descriptionFragment)
                    .commit();
        }
    }
}