package com.room303.dutaxi.ui.main.createtripfragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;
import com.room303.dutaxi.ui.main.tripfragment.TripsFragment;

import java.util.Calendar;

/**
 * TODO
 *  - Make sending data to Firebase when commitButton is pressed
 *  - Make returning to TripsFragment when cancelButton is pressed
 *  - Make taking data about telephone number and vk reference from local database,
 *  when registration will be done
 *  - Maybe make better TimePicker
 *  - Replace hardcoded strings with strings from resources
 *  - Add material design
 */

public class CreateTripFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private FragmentManager fragmentManager;

    private EditText phoneInput;
    private EditText vkrefInput;
    private Button timeInputButton;
    private RadioGroup freeseatsInput;
    private Spinner departureInput;
    private Spinner destinationInput;
    private ImageButton cancelButton;
    private ImageButton commitButton;
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
        fragmentManager = getParentFragmentManager();

        departureInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedView, int position, long id) {
                if(position == 0) {
                    destinationInput.setSelection(1);
                }
                if(position == 1) {
                    destinationInput.setSelection(0);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_button_cancel:
                fragmentManager.popBackStack();
                cleanForm();
                // go to TripsFragment
                break;
            case R.id.toolbar_button_commit:
                if (phoneInput.getText().toString().length() == 11 &&
                        vkrefInput.getText().toString().length() != 0 &&
                        freeSeats != -1) {
                    fragmentManager.popBackStack();
                    cleanForm();
                    // go to TripsFragment
                    // and create a new trip in the list
                    // if everything correct
                }
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_nav_trips:
                    navController.navigate(R.id.action_createTripFragment_to_tripsFragment);
                    return true;
                case R.id.bottom_nav_account:
                    navController.navigate(R.id.action_createTripFragment_to_accountFragment);
                    return true;
            }
            return false;
        });
    }

    private void cleanForm() {
        departureInput.setSelection(0);
        destinationInput.setSelection(0);
        phoneInput.setText("");
        vkrefInput.setText("");
        freeseatsInput.check(R.id.freesets_button_three);
    }

    private void initUi(View rootView) {
        cancelButton = rootView.findViewById(R.id.toolbar_button_cancel);
        cancelButton.setOnClickListener(this);
        commitButton = rootView.findViewById(R.id.toolbar_button_commit);
        commitButton.setOnClickListener(this);
        departureInput = rootView.findViewById(R.id.departure_input);
        destinationInput = rootView.findViewById(R.id.destination_input);
        destinationInput.setSelection(1);
        phoneInput = rootView.findViewById(R.id.phone_input);
        vkrefInput = rootView.findViewById(R.id.vkref_input);
        freeseatsInput = rootView.findViewById(R.id.freeseats_input);
        timeInputButton = rootView.findViewById(R.id.time_input);
        timeDisplay = rootView.findViewById(R.id.time_display);
    }

    private void setTimeDisplay(int hour, int minute) {
        String timeString = hour + ":" + minute;
        if(timeString.charAt(1) == ':') {
            timeString = '0' + timeString;
        }
        if(timeString.length() == 4) {
            timeString += '0';
        }
        timeDisplay.setText(timeString);
    }
}