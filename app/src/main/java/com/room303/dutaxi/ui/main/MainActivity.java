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
    private boolean isCreateTripFragmentDisplayed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeFragment(tripsFragment);
        setContentView(R.layout.activity_main);
        initUi();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // being called by invalidateOptionsMenu();
        // hides the menu button at main toolbar
        // if now CreateTripFragment is being displayed
        return !isCreateTripFragmentDisplayed;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_button_left: // CreateTripFragment button
                isCreateTripFragmentDisplayed = true;
                setToolbarForCreateTripActivity();
                changeFragment(createTripFragment);
                break;
            case R.id.bottom_button_middle: // TripsFragment button
                isCreateTripFragmentDisplayed = false;
                setToolbarMain();
                changeFragment(tripsFragment);
                break;
            case R.id.bottom_button_right: // AccountFragment button
                isCreateTripFragmentDisplayed = false;
                setToolbarMain();
                changeFragment(accountFragment);
                break;
        }
    }

    private void changeFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @SuppressLint("RestrictedApi")
    private void setToolbarForCreateTripActivity() {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) { // true guaranteed
            toolbar.invalidateOptionsMenu();
            // telling to call onPrepareOptionsMenu();
        }
        LinearLayout toolbarLayout = findViewById(R.id.toolbar_main_layout);
        toolbarLayout.setVisibility(View.GONE);
        ConstraintLayout layoutToInstall = findViewById(R.id.toolbar_create_trip_layout);
        layoutToInstall.setVisibility(View.VISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void setToolbarMain() {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null) { // true guaranteed
            toolbar.invalidateOptionsMenu();
            // telling to call onPrepareOptionsMenu();
        }
        LinearLayout toolbarLayout = findViewById(R.id.toolbar_main_layout);
        toolbarLayout.setVisibility(View.VISIBLE);
        ConstraintLayout layoutToInstall = findViewById(R.id.toolbar_create_trip_layout);
        layoutToInstall.setVisibility(View.GONE);
    }


    private void initUi() {
        Toolbar mainToolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(mainToolbar);
        mainContainer = findViewById(R.id.main_container);
        leftButton = findViewById(R.id.bottom_button_left);
        leftButton.setOnClickListener(this);
        middleButton = findViewById(R.id.bottom_button_middle);
        middleButton.setOnClickListener(this);
        rightButton = findViewById(R.id.bottom_button_right);
        rightButton.setOnClickListener(this);
    }
}
