package com.example.moze.Settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.moze.R;

public class SettingsFragment extends Fragment {

    private TextView tvChangePassword, tvChangeEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View add_view = inflater.inflate(R.layout.fragment_settings, container, false);
        tvChangePassword = (TextView) add_view.findViewById(R.id.tvChangePassword);
        tvChangeEmail = (TextView) add_view.findViewById(R.id.tvChangeEmail);

        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tvChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return add_view;

    }
}
