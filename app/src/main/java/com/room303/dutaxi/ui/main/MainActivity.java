package com.room303.dutaxi.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.room303.dutaxi.R;
import com.room303.dutaxi.requestitem.RequestItem;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final RequestItem requestItem = new RequestItem();
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private FrameLayout mainContainer;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    private Toolbar mainToolbar;
    private Toolbar createTripToolbar;
    private Button leftButton;
    private Button middleButton;
    private Button rightButton;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_button_left: // Create Trip Button
                //setSupportActionBar(createTripToolbar);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, createTripFragment);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case R.id.bottom_button_middle:
                break;
            case R.id.bottom_button_right:
                break;
        }
    }

    private void initUi() {
        mainToolbar = findViewById(R.id.toolbar_main);
        Toolbar sampleToolbar = findViewById(R.id.toolbar_main_layout);
        setSupportActionBar(sampleToolbar);
        //setSupportActionBar(mainToolbar);
        createTripToolbar = findViewById(R.id.toolbar_create_trip);
        //setSupportActionBar(createTripToolbar);
        mainContainer = findViewById(R.id.main_container);
        leftButton = findViewById(R.id.bottom_button_left);
        leftButton.setOnClickListener(this);
        middleButton = findViewById(R.id.bottom_button_middle);
        middleButton.setOnClickListener(this);
        rightButton = findViewById(R.id.bottom_button_right);
        rightButton.setOnClickListener(this);
    }
}
