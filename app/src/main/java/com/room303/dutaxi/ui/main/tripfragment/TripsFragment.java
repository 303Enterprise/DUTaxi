package com.room303.dutaxi.ui.main.tripfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.room303.dutaxi.R;

public class TripsFragment extends Fragment {
    public TripsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO
        //  change R.layout.trip_item back to R.layout.fragment_trips
        View rootView = inflater.inflate(R.layout.trip_item, container, false);
        return rootView;
    }
}