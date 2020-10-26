package com.room303.dutaxi.ui.main.accountfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.room303.dutaxi.R;

public class AccountFragment extends Fragment {

    private ImageView userPortrait;
    private TextView userName;
    private TextView userPhone;
    private TextView userVkref;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initUi(rootView);
        return rootView;
    }

    private void initUi(View rootView) {
        userPortrait = rootView.findViewById(R.id.account_user_portrait);
        userName = rootView.findViewById(R.id.account_user_name);
        userPhone = rootView.findViewById(R.id.account_user_phone);
        userVkref = rootView.findViewById(R.id.account_user_vkref);
    }
}