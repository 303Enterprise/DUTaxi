package com.room303.dutaxi.ui.main.accountfragment.editfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.room303.dutaxi.R;

public class EditFragment extends Fragment implements View.OnClickListener {
    private NavController navController;

    private EditText input;
    private TextView description;
    private ImageButton cancel;
    private ImageButton commit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        cancel = rootView.findViewById(R.id.toolbar_button_cancel);
        commit = rootView.findViewById(R.id.toolbar_button_commit);

        input = rootView.findViewById(R.id.edit_input);
        description = rootView.findViewById(R.id.edit_description);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_button_cancel:
                break;
            case R.id.toolbar_button_commit:
                break;
        }
    }
}
