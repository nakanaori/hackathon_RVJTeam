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
import com.u_binusportal.activity.EditProfileUser;
import com.u_binusportal.activity.entriesActivities.entries.RegisterUMKMActivity;

public class UserProfileFragment extends Fragment {
    private ImageView ProfileImage;
    private TextView Name;
    private TextView PhoneNumber;
    private View EditProfile;
    private View LogOut;
    private View DaftarUMKM;
//
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_user, container, false);

        // Make the XML components to java objects
        ProfileImage = rootView.findViewById(R.id.profile_pic);
        Name = rootView.findViewById(R.id.profile_name);
        PhoneNumber = rootView.findViewById(R.id.profile_phone_number);
        EditProfile = rootView.findViewById(R.id.menu_edit_profile);
        DaftarUMKM = rootView.findViewById(R.id.menu_daftar_umkm);
        LogOut = rootView.findViewById(R.id.menu_logout);


        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileUser.class));
            }
        });

        DaftarUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterUMKMActivity.class));
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),));
            }
        });

//        retrieveDataAndSetupTheViews();

        return rootView;
    }
}
