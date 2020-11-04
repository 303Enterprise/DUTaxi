package com.room303.dutaxi.ui.main.accountfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.room303.dutaxi.R;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private NavController navController;
    private ImageView userPortrait;
    private Button userName;
    private LinearLayout userPhoneLayout;
    private TextView userPhone;
    private LinearLayout userVkrefLayout;
    private TextView userVkref;
    private Button buttonHistory;

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
                Toast.makeText(getContext(), "Dialog to choose a new photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.account_user_phone_layout:
                navController.navigate(
                        passArgsToEditFragment(
                                "Change the phone number",
                                userPhone.getText().toString()
                                        .replace("+", "")
                                        .replaceAll(" ", "")
                                        .replaceAll("-", "")
                                        .replace("(", "")
                                        .replace(")",""),
                                editPhoneDescription
                        )
                );
                break;
            case R.id.account_user_vkref_layout:
                navController.navigate(
                        passArgsToEditFragment(
                                "Change the VK link",
                                userVkref.getText().toString(),
                                editLinkDescription));
                break;
            case R.id.account_button_show_history:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getContext(), R.style.BottomSheetTheme);
                View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                        .inflate(R.layout.bottom_sheet, getActivity().findViewById(R.id.bottom_sheet_main_layout));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                break;
        }
    }

    String editLinkDescription =
            "Please, use your actual VK link.\n" +
                    "Remember, that if you are setting false one \n" +
                    "you wouldn't be available to create requests";

    String editPhoneDescription =
            "While setting phone number, please \n" +
                    "do not use service characters as '+', '(', ')' etc. \n" +
                    "Remember, that if you are setting false number \n" +
                    "you wouldn't be available to create requests";

    private NavDirections passArgsToEditFragment(String toolbarTitle, String textToEdit, String description) {
        return AccountFragmentDirections.Companion
                .actionAccountFragmentToEditFragment(
                        toolbarTitle,
                        textToEdit,
                        description
                );
    }

    private void initUi(View rootView) {
        userPortrait = rootView.findViewById(R.id.account_user_portrait);
        userPortrait.setOnClickListener(this);
        userName = rootView.findViewById(R.id.account_user_name);
        userPhoneLayout = rootView.findViewById(R.id.account_user_phone_layout);
        userPhoneLayout.setOnClickListener(this);
        userPhone = rootView.findViewById(R.id.account_user_phone);
        userVkrefLayout = rootView.findViewById(R.id.account_user_vkref_layout);
        userVkrefLayout.setOnClickListener(this);
        userVkref = rootView.findViewById(R.id.account_user_vkref);
        buttonHistory = rootView.findViewById(R.id.account_button_show_history);
        buttonHistory.setOnClickListener(this);
    }

}