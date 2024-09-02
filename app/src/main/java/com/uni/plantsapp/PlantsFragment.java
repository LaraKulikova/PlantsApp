package com.uni.plantsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlantsFragment extends Fragment {

    private ListView plantsListView;
    private String[] plants = {"Роза", "Тюльпан", "Подсолнух", "Ромашка"};
    private OnPlantSelectedListener listener;

    public interface OnPlantSelectedListener {
        void onPlantSelected(String plant);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlantSelectedListener) {
            listener = (OnPlantSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnPlantSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants, container, false);
        plantsListView = view.findViewById(R.id.plants_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, plants);
        plantsListView.setAdapter(adapter);

        plantsListView.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedPlant = plants[position];
            listener.onPlantSelected(selectedPlant);
        });

        return view;
    }
}