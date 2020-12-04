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
import com.u_binusportal.activity.MoreInformations;
import com.u_binusportal.activity.MyUMKMDetails;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

public class UserUMKMProfileFragment extends Fragment {
    private ImageView ProfileImage;
    private TextView Name;
    private TextView PhoneNumber;
    private View EditProfile;
    private View LogOut;
    private View EditUMKM;
    private View MoreInformation;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_user_umkm, container, false);

        ProfileImage = rootView.findViewById(R.id.profile_pic2);
        Name = rootView.findViewById(R.id.profile_name2);
        PhoneNumber = rootView.findViewById(R.id.profile_phone_number2);
        EditProfile = rootView.findViewById(R.id.menu_edit_profile2);
        EditUMKM = rootView.findViewById(R.id.menu_edit_umkm);
        LogOut = rootView.findViewById(R.id.menu_logout2);
        MoreInformation = rootView.findViewById(R.id.menu_information2);

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileUser.class));
            }
        });

        EditUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyUMKMDetails.class));
            }
        });

        MoreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoreInformations.class));
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserTesting.isUserHasUMKM = false;
                UserTesting.hasUser = false;
                startActivity(new Intent(getActivity(), FragmentHandler.class));
            }
        });

//        retrieveDataAndSetupTheViews();

        return rootView;
    }
}
