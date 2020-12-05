package com.u_binusportal.activity.pageFragmentsActivities.pageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;

import com.u_binusportal.R;
import com.u_binusportal.handlers.FragmentHandler;

public class PageThree extends Fragment {

    View x;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.awal_3, container, false);

        x = rootView.findViewById(R.id.containerPage);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                move();
            }
        });

        return rootView;
    }

    private void move() {
        startActivity(new Intent(getActivity(), FragmentHandler.class));
    }


}
