package com.room303.dutaxi.ui.main.accountfragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.accountfragment.recyclerview.HistoryItem;
import com.room303.dutaxi.ui.main.accountfragment.recyclerview.RecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private NavController navController;

    private ImageView userPortrait;
    private Button userName;
    private LinearLayout userPhoneLayout;
    private TextView userPhone;
    private LinearLayout userVkrefLayout;
    private TextView userVkref;
    private Button buttonHistory;

    private ConstraintLayout toolbarLayout;
    private Toolbar toolbar;

    private static final int TAKE_PHOTO_CODE = 0;
    private static final int GET_PHOTO_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_account, menu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_user_portrait:
                selectImage(getContext());
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

        toolbarLayout = rootView.findViewById(R.id.account_toolbar_layout);
        toolbar = rootView.findViewById(R.id.account_toolbar);
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take photo", "Choose from gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose profile photo");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, TAKE_PHOTO_CODE);
                }
                else if (options[item].equals("Choose from gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, GET_PHOTO_CODE);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // метод, выполняющийся, после загрузки фотографиии пользователем
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case TAKE_PHOTO_CODE: // если фото было сделвно
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        userPortrait.setImageBitmap(bitmap);
                    }
                    break;
                case GET_PHOTO_CODE: // если фото было загружено из галереи
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        userPortrait.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    }
}