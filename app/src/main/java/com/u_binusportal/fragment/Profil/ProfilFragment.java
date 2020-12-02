package com.u_binusportal.fragment.Profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.u_binusportal.R;

public class ProfilFragment extends Fragment {
    private ImageView ProfileImage;
    private TextView Name;
    private TextView PhoneNumber;
    private View EditProfile;
    private View LogOut;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_profile, container, false);

        // Make the XML components to java objects
        ProfileImage = rootView.findViewById(R.id.profile_pic);
        Name = rootView.findViewById(R.id.profile_name);
        PhoneNumber = rootView.findViewById(R.id.profile_phone_number);
        EditProfile = rootView.findViewById(R.id.menu_edit_profile);
        LogOut = rootView.findViewById(R.id.menu_logout);

        // Set up the firebase attributes
        mAuth = FirebaseAuth.getInstance();
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users");

        // Set up the buttons
        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to EditProfileActivity
                startActivity(new Intent(getActivity(), EditProfileActivity.class));

            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();

            }
        });

        retrieveDataAndSetupTheViews();

        return rootView;
    }
}
