package com.room303.dutaxi.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.room303.dutaxi.R;
import com.room303.dutaxi.requestitem.RequestItem;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final RequestItem requestItem = new RequestItem();
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private FrameLayout mainContainer;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    private Button leftButton;
    private Button middleButton;
    private Button rightButton;
    private boolean isCreateTripFragmentDisplayed = false;

    { // giving CreateTripFragment instance of RequestItem to complete it with user data
        Bundle data = new Bundle();
        data.putParcelable(requestItem.getClass().getCanonicalName(), requestItem);
        createTripFragment.setArguments(data);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            case R.id.bottom_button_left: // Create Trip Button
                isCreateTripFragmentDisplayed = true;
                changeToolbar();
                changeFragment(createTripFragment);
                break;
            case R.id.bottom_button_middle:
                break;
            case R.id.bottom_button_right:
                break;
        }
    }

    private void changeFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @SuppressLint("RestrictedApi")
    private void changeToolbar() {
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
