package com.room303.dutaxi.ui.main.accountfragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private ImageView userPortrait;
    private LinearLayout userName;
    private LinearLayout userPhone;
    private TextView userVkref;
    private Button buttonHistory;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initUi(rootView);
        return rootView;
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
                    navController.navigate(R.id.action_accountFragment_to_createTripFragment);
                    return true;
                case R.id.bottom_nav_trips:
                    navController.navigate(R.id.action_accountFragment_to_tripsFragment);
                    return true;
            }
            return false;
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_user_portrait:
            case R.id.account_user_name:
            case R.id.account_user_phone:
            case R.id.account_user_vkref:
            case R.id.account_button_show_history:
        }
    }

    private void initUi(View rootView) {
        userPortrait = rootView.findViewById(R.id.account_user_portrait);
        userName = rootView.findViewById(R.id.account_user_name);
        userPhone = rootView.findViewById(R.id.account_user_phone);
        userVkref = rootView.findViewById(R.id.account_user_vkref);
        buttonHistory = rootView.findViewById(R.id.account_button_show_history);
    }

}