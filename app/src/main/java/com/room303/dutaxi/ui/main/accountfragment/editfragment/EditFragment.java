package com.room303.dutaxi.ui.main.accountfragment.editfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.room303.dutaxi.R;

public class EditFragment extends Fragment implements View.OnClickListener {
    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    private TextView toolbarTopic;
    private EditText input;
    private TextView description;
    private ImageButton cancel;
    private ImageButton commit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        initUi(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        String amount = EditFragmentArgs.fromBundle(getArguments()).getTitle();
        toolbarTopic.setText(amount);
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onPause() {
        super.onPause();
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_button_cancel:
                navController.navigate(R.id.action_editFragment_to_accountFragment);
                break;
            case R.id.toolbar_button_commit:
                navController.navigate(R.id.action_editFragment_to_accountFragment);
                break;
        }
    }

    private void initUi(View rootView) {
        toolbarTopic = rootView.findViewById(R.id.toolbar_text_title);
        cancel = rootView.findViewById(R.id.toolbar_button_cancel);
        cancel.setOnClickListener(this);
        commit = rootView.findViewById(R.id.toolbar_button_commit);
        commit.setOnClickListener(this);
        input = rootView.findViewById(R.id.edit_input);
        input.requestFocus();
        ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .toggleSoftInput(0, 0);
        description = rootView.findViewById(R.id.edit_description);
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
    }
}
