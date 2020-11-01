package com.room303.dutaxi.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.accountfragment.AccountFragment;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;
import com.room303.dutaxi.ui.main.tripfragment.TripsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private final AccountFragment accountFragment = new AccountFragment();
    private final TripsFragment tripsFragment = new TripsFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            // just set status bar color to colorPrimary maybe?
        }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_trip);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_nav_host:
                    navController.navigate(R.id.to_createTripFragment);
                    //changeFragment(createTripFragment);
                    return true;
                case R.id.bottom_nav_trip:
                    navController.navigate(R.id.to_tripsFragment);
                    //changeFragment(tripsFragment);
                    return true;
                case R.id.bottom_nav_account:
                    navController.navigate(R.id.to_accountFragment);
                    //changeFragment(accountFragment);
                    return true;
            }
            return false;
        });
    }

    private void changeFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
