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

    private Button leftButton;
    private Button middleButton;
    private Button rightButton;

    { // giving CreateTripFragment instance of RequestItem to complete it with user data
        createTripFragment.setArguments(new Bundle());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myActionBar = findViewById(R.id.toolbar);
        setSupportActionBar(myActionBar);

        mainContainer = findViewById(R.id.main_container);
        leftButton = findViewById(R.id.bottom_button_left);
        middleButton = findViewById(R.id.bottom_button_middle);
        rightButton = findViewById(R.id.bottom_button_right);
        leftButton.setOnClickListener(this);
        middleButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_createtrip, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_button_left:
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
}
