package com.room303.dutaxi.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.accountfragment.AccountFragment;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;
import com.room303.dutaxi.ui.main.tripsfragment.TripsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private final AccountFragment accountFragment = new AccountFragment();
    private final TripsFragment tripsFragment = new TripsFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    /**
     * TODO
     *  - somehow fix back stack bug with saving current states of fragments
     *  - make animations of navigation actions
     *  - make menus items click listeners and logic
     *  - make recycler view in trips fragment
     *  - make planning trip to tomorrow in CreateTripFragment
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            // just set status bar color to colorPrimary maybe?
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_trips);
    }
}
