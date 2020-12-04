package com.u_binusportal.activity.fragmentActivities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.u_binusportal.R;
import com.u_binusportal.activity.entriesActivities.entries.LoginActivity;
import com.u_binusportal.activity.entriesActivities.entries.RegisterActivity;

public class UnregisteredUserFragment extends Fragment {
    private Button RegisterButton;
    private Button LoginButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.unregistered_user_profile, container, false);

        RegisterButton = rootView.findViewById(R.id.daftar);
        LoginButton = rootView.findViewById(R.id.masuk);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
