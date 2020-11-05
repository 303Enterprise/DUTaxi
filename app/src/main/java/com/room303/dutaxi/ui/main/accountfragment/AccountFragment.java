package com.room303.dutaxi.ui.main.accountfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.accountfragment.recyclerview.HistoryItem;
import com.room303.dutaxi.ui.main.accountfragment.recyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Random;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private NavController navController;

    private RoundedImageView userPortrait;
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
                                        .replace(")", ""),
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
                showHistoryBottomSheet();
                break;
        }
    }

    private void showHistoryBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                getContext(), R.style.BottomSheetTheme);
        View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(R.layout.history_bottom_sheet, getActivity().findViewById(R.id.history_bottom_sheet_main_layout));
        RecyclerView recyclerView = bottomSheetView.findViewById(R.id.history_bottom_sheet_recycler_view);
        ArrayList<HistoryItem> historyItems = generateRandomRequests(25);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), historyItems);
        recyclerView.setAdapter(adapter);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private static ArrayList<HistoryItem> generateRandomRequests(int size) {
        Random rand = new Random();
        String[] placesToGo = {
                "ДУ",
                "Двойка",
                "Главное здание",
                "Б4",
                "ИФ",
                "Межлаука",
                "Пушкина",
                "УНИКС",
                "Кольцо"
        };
        String[] names = {
                "Кирилл",
                "Катя",
                "Адель",
                "Александр",
                "Сергей",
                "Данил",
                "Мария",
                "Тимур",
                "Семен",
                "Карина",
                "Роман",
                "Владимир",
                "Егор",
                "Алина",
                "Полина",
                "Настя"
        };
        ArrayList<HistoryItem> HistoryItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            HistoryItems.add(
                    new HistoryItem(
                            placesToGo[rand.nextInt(placesToGo.length)],
                            placesToGo[rand.nextInt(placesToGo.length)],
                            (rand.nextInt(31) + 1) + "." + rand.nextInt(12) + "." + 2020 + "\n"
                            + rand.nextInt(24) + ":" + rand.nextInt(60),
                            new String[]{
                                    names[rand.nextInt(names.length)],
                                    names[rand.nextInt(names.length)],
                                    names[rand.nextInt(names.length)]
                            }
                    )
            );
        }
        return HistoryItems;
    }

    String editLinkDescription =
            "Please, use your actual VK link.\n" +
                    "Remember, that if you are setting a false one \n" +
                    "you wouldn't be able to create requests";

    String editPhoneDescription =
            "While setting phone number, please \n" +
                    "do not use service characters as [ + - ( ) ] etc. \n" +
                    "Remember, that if you are setting false phone number \n" +
                    "you wouldn't be able to create requests";

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