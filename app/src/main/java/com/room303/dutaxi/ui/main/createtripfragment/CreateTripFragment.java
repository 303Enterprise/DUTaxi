package com.room303.dutaxi.ui.main.createtripfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.room303.dutaxi.R;

public class CreateTripFragment extends Fragment {

    private static final String FIRST_DESCRIPTION = "SAMPLE DESCRIPTION";

    private TextView description;

    public CreateTripFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_trip, container, false);
        description = rootView.findViewById(R.id.description);
        description.setText(FIRST_DESCRIPTION);

        return rootView;
    }
}