package com.u_binusportal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.chaos.view.PinView;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

public class OTPGenerate extends AppCompatActivity {

    private PinView pinView;
    private Button btnVerif, btnResend;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_otp);
        inisialisasi();
    }

    public void inisialisasi() {
        pinView = findViewById(R.id.firstPinView);
        pinView.setTextColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.newBlue, getTheme()));
        pinView.setLineColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.newBlue, getTheme()));

        pinView.setItemCount(6);
        pinView.setItemHeight(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        pinView.setItemWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        pinView.setItemRadius(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_radius));
        pinView.setItemSpacing(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
        pinView.setLineWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
        pinView.setAnimationEnable(true);
        pinView.setCursorVisible(true);
        pinView.setCursorColor(
                ResourcesCompat.getColor(getResources(), R.color.newBlue, getTheme()));
        pinView.setCursorWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));

        pinView.setItemBackgroundColor(0);
        pinView.setItemBackground(getResources().getDrawable(R.color.white));
        pinView.setItemBackgroundResources(R.color.white);

        pinView.setHideLineWhenFilled(false);
        pinView.setPasswordHidden(true);
        pinView.setTransformationMethod(new PasswordTransformationMethod());

        btnVerif = findViewById(R.id.verifBTN);
        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check apakah betul, kalau betul
                moveToFragment();
            }
        });

        btnResend = findViewById(R.id.resendOTP);
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // buat ulang
            }
        });
    }

    private void moveToFragment() {
        // INI INTENTNYA
        UserTesting.hasUser = true;
        Intent i = new Intent(this, FragmentHandler.class);
        startActivity(i);
    }


}
