package com.u_binusportal.activity.fragmentActivities.fragment;

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
import com.u_binusportal.activity.EditProfileUMKM;
import com.u_binusportal.activity.EditProfileUser;

public class UserUMKMProfileFragment extends Fragment {
    private ImageView ProfileImage;
    private TextView Name;
    private TextView PhoneNumber;
    private View EditProfile;
    private View LogOut;
    private View EditUMKM;
    //
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_user_umkm, container, false);

        // Make the XML components to java objects
        ProfileImage = rootView.findViewById(R.id.profile_pic2);
        Name = rootView.findViewById(R.id.profile_name2);
        PhoneNumber = rootView.findViewById(R.id.profile_phone_number2);
        EditProfile = rootView.findViewById(R.id.menu_edit_profile2);
        EditUMKM = rootView.findViewById(R.id.menu_edit_umkm);
        LogOut = rootView.findViewById(R.id.menu_logout2);

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileUser.class));
            }
        });

        EditUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity()), EditProfileUMKM.class);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        retrieveDataAndSetupTheViews();

        return rootView;
    }
}
