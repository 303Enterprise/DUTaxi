package com.room303.dutaxi.ui.main.tripsfragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RecyclerAdapter;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RecyclerClickListener;
import com.room303.dutaxi.ui.main.tripsfragment.recyclerview.RequestItem;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class TripsFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "TripsFragment debug";
    private NavController navController;
    private BottomSheetDialog bottomSheetDialog;
    private Toolbar toolbar;

    private static ArrayList<RequestItem> generateRandomRequests(int size) {
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
        ArrayList<RequestItem> requestItems = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int minute;
            requestItems.add(
                    new RequestItem(
                            placesToGo[rand.nextInt(placesToGo.length)],
                            placesToGo[rand.nextInt(placesToGo.length)],
                            (7 + rand.nextInt(12)) + ":" + ((minute = rand.nextInt(12) * 5) < 10 ? "0" + minute : minute),
                            Integer.toString(rand.nextInt(3) + 1)
                    )
            );
        }
        return requestItems;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trips, container, false);

        ArrayList<RequestItem> requestItems = generateRandomRequests(20);
        RecyclerView recyclerView = rootView.findViewById(R.id.tripsFragmentRecyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), requestItems);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerClickListener(getContext(), recyclerView, new RecyclerClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        showTripsBottomSheet();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getContext(), "*Long click indicator*", Toast.LENGTH_SHORT).show();
                    }
                })
        );
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // if y < 0 then it is scroll upwards
                // if y > 0 then it is scroll downwards

                Log.d(TAG, "onScrolled: " + dy);

                if (dy > 0) { //down
                    toolbar.setVisibility(View.GONE);
                } else { // up
                    toolbar.setVisibility(View.VISIBLE);
                }
            }
        });
        return rootView;
    }

    private void showTripsBottomSheet() {
        bottomSheetDialog = new BottomSheetDialog(getContext(), R.style.BottomSheetTheme);
        View bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext())
                .inflate(
                        R.layout.trips_bottom_sheet,
                        getActivity().findViewById(R.id.trips_bottom_sheet_main_layout)
                );
        initUiBottomSheet(bottomSheetView);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private TextView phoneHost;

    private void initUiBottomSheet(View rootView) {
        ImageButton cancel = rootView.findViewById(R.id.trips_bottom_sheet_button_cancel);
        cancel.setOnClickListener(this);

        TextView departure = rootView.findViewById(R.id.trips_bottom_sheet_departure);
        TextView destination = rootView.findViewById(R.id.trips_bottom_sheet_destination);
        TextView time = rootView.findViewById(R.id.trips_bottom_sheet_time);
        TextView timeRemain = rootView.findViewById(R.id.trips_bottom_sheet_time_remain);

        LinearLayout layoutHost = rootView.findViewById(R.id.trips_bottom_sheet_layout_host);
        layoutHost.setOnClickListener(this);
        CircleImageView imageHost = rootView.findViewById(R.id.trips_bottom_sheet_image_host);
        TextView nameHost = rootView.findViewById(R.id.trips_bottom_sheet_text_name_host);

        LinearLayout layoutMember1 = rootView.findViewById(R.id.trips_bottom_sheet_layout_member1);
        layoutMember1.setOnClickListener(this);
        CircleImageView imageMember1 = rootView.findViewById(R.id.trips_bottom_sheet_image_member1);
        TextView nameMember1 = rootView.findViewById(R.id.trips_bottom_sheet_text_name_member1);

        LinearLayout layoutMember2 = rootView.findViewById(R.id.trips_bottom_sheet_layout_member2);
        layoutMember2.setOnClickListener(this);
        CircleImageView imageMember2 = rootView.findViewById(R.id.trips_bottom_sheet_image_member2);
        TextView nameMember2 = rootView.findViewById(R.id.trips_bottom_sheet_text_name_member2);

        LinearLayout layoutMember3 = rootView.findViewById(R.id.trips_bottom_sheet_layout_member3);
        layoutMember3.setOnClickListener(this);
        CircleImageView imageMember3 = rootView.findViewById(R.id.trips_bottom_sheet_image_member3);
        TextView nameMember3 = rootView.findViewById(R.id.trips_bottom_sheet_text_name_member3);

        phoneHost = rootView.findViewById(R.id.trips_bottom_sheet_text_phone);
        phoneHost.setOnClickListener(this);

        Button buttonJoin = rootView.findViewById(R.id.trips_bottom_sheet_button_join);
        buttonJoin.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        toolbar = getActivity().findViewById(R.id.trips_toolbar);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_nav_host:
                    navController.navigate(R.id.action_tripsFragment_to_createTripFragment);
                    return true;
                case R.id.bottom_nav_account:
                    navController.navigate(R.id.action_tripsFragment_to_accountFragment);
                    return true;
            }
            return false;
        });
    }

    private static final String userUri = "https://vk.com/a3b_ecmb";
    private static final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(userUri));

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trips_bottom_sheet_button_cancel:
                bottomSheetDialog.cancel();
                break;
            case R.id.trips_bottom_sheet_layout_host:
                startActivity(browserIntent);
                break;
            case R.id.trips_bottom_sheet_layout_member1:
                startActivity(browserIntent);
                break;
            case R.id.trips_bottom_sheet_layout_member2:
                startActivity(browserIntent);
                break;
            case R.id.trips_bottom_sheet_layout_member3:
                startActivity(browserIntent);
                break;
            case R.id.trips_bottom_sheet_text_phone:
                Toast.makeText(getContext(), "Номер скопирован!", Toast.LENGTH_SHORT).show();
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", phoneHost.getText().toString());
                clipboard.setPrimaryClip(clip);
                break;
            case R.id.trips_bottom_sheet_button_join:
                bottomSheetDialog.cancel();
                break;
        }
    }
}