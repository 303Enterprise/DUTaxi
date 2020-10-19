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
import android.widget.TextView;
import android.widget.TimePicker;

import com.room303.dutaxi.R;

import java.util.Calendar;

/**
 * TODO
 * - Add displaying chosen time
 * - Make sending data to Firebase when commitButton is pressed
 * - Make returning to TripsFragment when cancelButton is pressed
 * - Make taking data about telephone number and vk reference from local database,
 * when registration will be done
 * - Maybe make better TimePicker
 * - Add material design
 */

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
    private TextView timeDisplay;

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

        Calendar cal = Calendar.getInstance();
        tripHour = cal.get(Calendar.HOUR_OF_DAY);

        tripMinute = cal.get(Calendar.MINUTE);
        setTimeDisplay(tripHour, tripMinute);

        timeInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when user clicks OK button at TimePickerDialog
                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        tripHour = hour;
                        tripMinute = minute;
                        setTimeDisplay(tripHour, tripMinute);
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
        switch (v.getId()) {
            case R.id.toolbar_button_cancel:
                // go to TripsFragment
                break;
            case R.id.toolbar_button_commit:
                if (phoneInput.getText().toString().length() == 11 &&
                        vkrefInput.getText().toString().length() != 0 &&
                        freeSeats != -1) {
                    // go to TripsFragment
                    // and create a new trip in the list
                    // if everything correct
                }
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
        timeDisplay = rootView.findViewById(R.id.time_display);
    }

    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void setTimeDisplay(int hour, int minute) {
        String timeString = Integer.toString(hour) + ":" + Integer.toString(minute);
        if(timeString.charAt(1) == ':') {
            timeString = '0' + timeString;
        }
        if(timeString.length() == 4) {
            timeString += '0';
        }
        timeDisplay.setText(timeString);
    }
}