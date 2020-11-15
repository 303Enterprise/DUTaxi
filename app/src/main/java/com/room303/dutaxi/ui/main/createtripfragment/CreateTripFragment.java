package com.room303.dutaxi.ui.main.createtripfragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;

import java.util.Calendar;

public class CreateTripFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private int systemVolumeLevel;

    private Spinner departureInput;
    private Spinner destinationInput;
    private RadioGroup freeseatsInput;
    private TimePicker timePicker;
    private Button sendRequest;

    private int currentMinute;
    private int currentHour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_trip, container, false);
        initUi(rootView);
        fragmentManager = getParentFragmentManager();

        departureInput.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View selectedView, int position, long id) {
                if (position == 1) {
                    destinationInput.setSelection(1);
                }
                if (position > 0) {
                    destinationInput.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Calendar cal = Calendar.getInstance();
        currentHour = cal.get(Calendar.HOUR_OF_DAY);
        currentMinute = cal.get(Calendar.MINUTE);

        timePicker = rootView.findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(currentHour);
            timePicker.setMinute(currentMinute);
        } else {
            timePicker.setCurrentHour(currentHour);
            timePicker.setCurrentMinute(currentMinute);
        }

        mediaPlayer = MediaPlayer.create(getContext(), R.raw.click_3);
        audioManager = (AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);
        mediaPlayer.setAudioStreamType(audioManager.STREAM_MUSIC);
        timePicker.setOnTimeChangedListener((timePicker, i, i1) -> {
            systemVolumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            float volumeLevel = calculateVolumeLevel(systemVolumeLevel);
            mediaPlayer.setVolume(volumeLevel, volumeLevel); //set volume takes two paramater
            mediaPlayer.seekTo(0);
            mediaPlayer.start();
        });

        return rootView;
    }

    private float calculateVolumeLevel(int systemVolumeLevel) {
        float section = 1f / 14f;
        float value = section * (systemVolumeLevel - 1);
        if(value == 1) {
            return 0.05f;
        }
        else
            return 1 - value;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
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

    private void initUi(View rootView) {
        departureInput = rootView.findViewById(R.id.departure_input);
        destinationInput = rootView.findViewById(R.id.destination_input);
        destinationInput.setSelection(1);
        freeseatsInput = rootView.findViewById(R.id.freeseats_input);
        sendRequest = rootView.findViewById(R.id.create_trip_send_request_button);
        sendRequest.setOnClickListener(this);
    }

    private void setTimeDisplay(int hour, int minute) {
        String timeString = hour + ":" + minute;
        if (timeString.charAt(1) == ':') {
            timeString = '0' + timeString;
        }
        if (timeString.length() == 4) {
            timeString += '0';
        }
        //timeDisplayTextView.setText(timeString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_trip_send_request_button:
                switch (freeseatsInput.getCheckedRadioButtonId()) {
                    case R.id.freesets_button_one:
                        break;
                    case R.id.freesets_button_two:
                        break;
                    case R.id.freesets_button_three:
                        break;
                }
                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_trips);
                break;
        }
    }
}