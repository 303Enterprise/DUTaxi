package com.room303.dutaxi.ui.main.tripsfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RecyclerAdapter;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RecyclerClickListener;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RequestItem;

import java.util.ArrayList;
import java.util.Random;

public class TripsFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private BottomSheetDialog bottomSheetDialog;

    private static ArrayList<RequestItem> generateRandomRequests(int size) {
        Random rand = new Random();
        String[] placesToGo = {
                "ДУ",
                "Двойка",
                "Главное здание",
                "Б4",
                "ИФ",
                "Межлаука",
                "Пушкина",
                "УНИКС",
                "Кольцо"
        };
        ArrayList<RequestItem> requestItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int minute;
            requestItems.add(
                    new RequestItem(
                            placesToGo[rand.nextInt(placesToGo.length)],
                            placesToGo[rand.nextInt(placesToGo.length)],
                            (7 + rand.nextInt(12)) + ":" + ((minute = rand.nextInt(12) * 5) < 10 ? "0" + minute : minute),
                            Integer.toString(rand.nextInt(3) + 1)
                    )
            );
        }
        return requestItems;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trips, container, false);
        ArrayList<RequestItem> requestItems = generateRandomRequests(20);
        RecyclerView recyclerView = rootView.findViewById(R.id.tripsFragmentRecyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), requestItems);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(getContext(), recyclerView, new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        showTripsBottomSheet();
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), "*Long click indicator*", Toast.LENGTH_SHORT).show();
                        // do whatever
                    }
                })
        );
        return rootView;
    }

    private void showTripsBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(
                getContext(), R.style.BottomSheetTheme);
        View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.trips_bottom_sheet, getActivity().findViewById(R.id.trips_bottom_sheet_main_layout));
        initUiBottomSheet(bottomSheetView);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void initUiBottomSheet(View rootView) {
        ImageButton cancel = rootView.findViewById(R.id.trips_bottom_sheet_button_cancel);
        cancel.setOnClickListener(this);
        LinearLayout layoutHost = rootView.findViewById(R.id.trips_bottom_sheet_image_host);
        LinearLayout layoutMember1 = rootView.findViewById(R.id.trips_bottom_sheet_image_member1);
        LinearLayout layoutMember2 = rootView.findViewById(R.id.trips_bottom_sheet_image_member2);
        LinearLayout layoutMember3 = rootView.findViewById(R.id.trips_bottom_sheet_image_member3);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trips_bottom_sheet_button_cancel:
                bottomSheetDialog.hide();
                break;
        }
    }
}