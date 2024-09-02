package com.uni.plantsapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PlantsFragment.OnPlantSelectedListener {

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isTablet = findViewById(R.id.fragment_container_2) != null; // Проверка на наличие второго контейнера

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_1, new PlantsFragment())
                    .commit();
        }
    }

    @Override
    public void onPlantSelected(String plant) {
        if (isTablet) {
            // Для планшета заменяем оба фрагмента
            PlantDescriptionFragment descriptionFragment = new PlantDescriptionFragment();
            Bundle args = new Bundle();
            args.putString("plant", plant);
            descriptionFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_1, new PlantsFragment())
                    .replace(R.id.fragment_container_2, descriptionFragment)
                    .commit();
        } else {
            // Для телефона проверяем ориентацию
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                // В вертикальной ориентации заменяем фрагмент в контейнере 2
                PlantDescriptionFragment descriptionFragment = new PlantDescriptionFragment();
                Bundle args = new Bundle();
                args.putString("plant", plant);
                descriptionFragment.setArguments(args);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_1, descriptionFragment)
                        .addToBackStack(null) // Добавляем в стек для возможности возврата
                        .commit();
            } else {
                // В горизонтальной ориентации  новую активность с описанием растения
                Intent intent = new Intent(this, PlantDetailActivity.class);
                intent.putExtra("plant", plant);
                startActivity(intent);
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохранение состояния выбранного растения
    }
}
