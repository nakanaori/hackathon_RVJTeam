package com.u_binusportal.activity.entriesActivities.entries;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;

public class UnregisteredUserActivity extends AppCompatActivity {
    private Button RegisterButton;
    private Button LoginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unregistered_user_profile);
        RegisterButton = findViewById(R.id.daftar);
        LoginButton = findViewById(R.id.masuk);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnregisteredUserActivity.this, RegisterActivity.class);
                startActivities(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnregisteredUserActivity.this, LoginActivity.class);
                startActivities(intent);
            }
        });
    }
}
