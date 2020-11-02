package com.room303.dutaxi.ui.main.tripsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RecyclerAdapter;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RequestItem;

import java.util.ArrayList;

public class TripsFragment extends Fragment {

    private NavController navController;

    public TripsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<RequestItem> requestItems = new ArrayList<>();

        // TODO
        //  make commit button be in a more common place
        View rootView = inflater.inflate(R.layout.fragment_trips, container, false);

        requestItems.add(new RequestItem("123", "123", "123", "123"));
        requestItems.add(new RequestItem("123", "123", "123", "123"));
        requestItems.add(new RequestItem("123", "123", "123", "123"));
        requestItems.add(new RequestItem("123", "123", "123", "123"));
        requestItems.add(new RequestItem("123", "123", "123", "123"));
        requestItems.add(new RequestItem("123", "123", "123", "123"));
        RecyclerView recyclerView = rootView.findViewById(R.id.tripsFragmentRecyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), requestItems);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_nav_host:
                    navController.navigate(R.id.action_tripsFragment_to_createTripFragment);
                    return true;
                case R.id.bottom_nav_account:
                    navController.navigate(R.id.action_tripsFragment_to_accountFragment);
                    return true;
            }
            return false;
        });
    }
}