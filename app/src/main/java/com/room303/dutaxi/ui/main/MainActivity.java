package com.room303.dutaxi.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.accountfragment.AccountFragment;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;
import com.room303.dutaxi.ui.main.tripfragment.TripsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private final AccountFragment accountFragment = new AccountFragment();
    private final TripsFragment tripsFragment = new TripsFragment();
    private FrameLayout mainContainer;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    private Button leftButton;
    private Button middleButton;
    private Button rightButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeFragment(tripsFragment);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_button_left: // CreateTripFragment button
                changeFragment(createTripFragment);
                break;
            case R.id.bottom_button_middle: // TripsFragment button
                changeFragment(tripsFragment);
                break;
            case R.id.bottom_button_right: // AccountFragment button
                changeFragment(accountFragment);
                break;
        }
    }

    private void changeFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void initUi() {
        mainContainer = findViewById(R.id.main_container);
        leftButton = findViewById(R.id.bottom_button_left);
        leftButton.setOnClickListener(this);
        middleButton = findViewById(R.id.bottom_button_middle);
        middleButton.setOnClickListener(this);
        rightButton = findViewById(R.id.bottom_button_right);
        rightButton.setOnClickListener(this);
    }
}
