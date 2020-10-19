package com.room303.dutaxi.ui.main.createtripfragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.room303.dutaxi.R;
import com.room303.dutaxi.requestitem.RequestItem;

import java.util.Calendar;

public class CreateTripFragment extends Fragment implements View.OnClickListener {
    private static final String FIRST_DESCRIPTION = "SAMPLE DESCRIPTION";

    private EditText phoneInput;
    private EditText vkrefInput;
    private Button timeInputButton;
    private RadioGroup freeseatsInput;
    private Spinner departureInput;
    private Spinner destinationInput;
    private Button cancelButton;
    private Button commitButton;

    private int tripMinute;
    private int tripHour;
    private int freeSeats;
    private boolean isTimePicked;

    public CreateTripFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_trip, container, false);
        initUi(rootView);

        // requestItem existence is guaranteed
        Bundle args = getArguments();
        RequestItem requestItem = null;
        if (args != null) {
            requestItem = (RequestItem) args.get(RequestItem.class.getCanonicalName());
        }


        isTimePicked = false;
        timeInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTimePicked) { // if user didn't chosen time yet
                    Calendar cal = Calendar.getInstance();
                    tripHour = cal.get(Calendar.HOUR_OF_DAY);
                    tripMinute = cal.get(Calendar.MINUTE);
                }

                // when user clicks OK button at TimePickerDialog
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        isTimePicked = true;
                        tripHour = hour;
                        tripMinute = minute;
                    }
                };

                // creating new dialog window
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(), timeSetListener, tripHour, tripMinute, true);
                timePickerDialog.show();
            }
        });


        freeseatsInput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.freesets_button_one:
                        freeSeats = 1;
                        break;
                    case R.id.freesets_button_two:
                        freeSeats = 2;
                        break;
                    case R.id.freesets_button_three:
                        freeSeats = 3;
                        break;
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_button_cancel:
                // go to TripsFragment
                break;
            case R.id.toolbar_button_commit:
                // go to TripsFragment
                // and create a new trip in the list
                // if everything correct
                break;
        }
    }

    private void initUi(View rootView) {
        departureInput = rootView.findViewById(R.id.departure_input);
        destinationInput = rootView.findViewById(R.id.destination_input);
        phoneInput = rootView.findViewById(R.id.phone_input);
        vkrefInput = rootView.findViewById(R.id.vkref_input);
        freeseatsInput = rootView.findViewById(R.id.freeseats_input);
        timeInputButton = rootView.findViewById(R.id.time_input);
        cancelButton = rootView.findViewById(R.id.toolbar_button_cancel);
        commitButton = rootView.findViewById(R.id.toolbar_button_commit);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}