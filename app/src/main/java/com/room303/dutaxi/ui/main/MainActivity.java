package com.room303.dutaxi.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.room303.dutaxi.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "AppDebug_MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
