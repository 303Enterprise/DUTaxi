package com.room303.dutaxi.ui.main.accountfragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.room303.dutaxi.R;

public class AccountFragment extends Fragment {

    private ImageView userPortrait;
    private TextView userName;
    private TextView userPhone;
    private TextView userVkref;
    private Button buttonHistory;

    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initUi(rootView);
        setToolbar();
        return rootView;
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar_main);
    }

    private void initUi(View rootView) {
        userPortrait = rootView.findViewById(R.id.account_user_portrait);
        userName = rootView.findViewById(R.id.account_user_name);
        userPhone = rootView.findViewById(R.id.account_user_phone);
        userVkref = rootView.findViewById(R.id.account_user_vkref);
        buttonHistory = rootView.findViewById(R.id.account_button_show_history);
    }
}