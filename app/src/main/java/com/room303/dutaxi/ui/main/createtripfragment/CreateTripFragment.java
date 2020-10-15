package com.room303.dutaxi.ui.main.createtripfragment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.room303.dutaxi.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateTripFragment extends Fragment {

    private static final String FIRST_DESCRIPTION = "SAMPLE DESCRIPTION";

    private EditText phoneInput;
    private EditText vkrefInput;
    private Button timeInputButton;
    private RadioGroup freeseatsInput;

    private int tripMinute;
    private int tripHour;
    private boolean isTimePicked;

    public CreateTripFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_trip, container, false);
        isTimePicked = false;
        phoneInput = rootView.findViewById(R.id.phone_input);
        timeInputButton = rootView.findViewById(R.id.time_input);
        timeInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTimePicked) {
                    Calendar cal = Calendar.getInstance();
                    tripHour = cal.get(Calendar.HOUR_OF_DAY);
                    tripMinute = cal.get(Calendar.MINUTE);
                }

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour, int minute) {
                        isTimePicked = true;
                        tripHour = hour;
                        tripMinute = minute;
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getActivity(), timeSetListener, tripHour, tripMinute, true);
                timePickerDialog.show();
            }
        });

        freeseatsInput = rootView.findViewById(R.id.freeseats_input);
        freeseatsInput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.freesets_button_one:

                        break;
                    case R.id.freesets_button_two:

                        break;
                    case R.id.freesets_button_three:
                        break;
                }
            }
        });


        return rootView;
    }


}