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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.activity.EditProfileUser;
import com.u_binusportal.activity.MoreInformations;
import com.u_binusportal.activity.entriesActivities.entries.RegisterUMKMActivity;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

public class UserProfileFragment extends Fragment {
    private ImageView ProfileImage;
    private TextView Name;
    private TextView PhoneNumber;
    private View EditProfile;
    private View LogOut;
    private View DaftarUMKM;
    private View MoreInformation;
    private FirebaseAuth db = FirebaseAuth.getInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_user, container, false);

        ProfileImage = rootView.findViewById(R.id.profile_pic);
        Name = rootView.findViewById(R.id.profile_name);
        PhoneNumber = rootView.findViewById(R.id.profile_phone_number);
        EditProfile = rootView.findViewById(R.id.menu_edit_profile);
        DaftarUMKM = rootView.findViewById(R.id.menu_daftar_umkm);
        LogOut = rootView.findViewById(R.id.menu_logout);
        MoreInformation = rootView.findViewById(R.id.menu_information);

        Name.setText(Constant.currentUser.getUserName());

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

        MoreInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MoreInformations.class));
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ubah jadi no user
                db.signOut();
                Constant.currentUser = null;
                Constant.currentUmkm = null;
                startActivity(new Intent(getActivity(), FragmentHandler.class));
            }
        });

//        retrieveDataAndSetupTheViews();

        return rootView;
    }
}
