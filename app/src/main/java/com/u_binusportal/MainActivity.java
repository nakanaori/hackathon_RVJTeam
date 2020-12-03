package com.u_binusportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.u_binusportal.activity.entriesActivities.entries.LoginActivity;
import com.u_binusportal.handlers.FragmentHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_app);

        call();
    }
    public void call() {
        Intent i = new Intent(this, LoginActivity.class );
        startActivity(i);
    }

    // ini cuma untuk testing fragment dengan navigasi aja
    public void toFragment(View view) {
        Intent i = new Intent(this, FragmentHandler.class );
        startActivity(i);
    }
}