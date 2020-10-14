package com.room303.dutaxi.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.FrameLayout;

import com.room303.dutaxi.R;
import com.room303.dutaxi.requestitem.RequestItem;
import com.room303.dutaxi.ui.main.createtripfragment.CreateTripFragment;

public class MainActivity extends AppCompatActivity {

    private final RequestItem requestItem = new RequestItem();
    private static final String TAG = "AppDebug_MainActivity";
    private final CreateTripFragment createTripFragment = new CreateTripFragment();
    private FrameLayout mainContainer;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Button leftButton;
    private Button rightButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, createTripFragment);
        fragmentTransaction.commit();
    }
}
