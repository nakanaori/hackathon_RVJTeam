package com.u_binusportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.activity.entriesActivities.entries.LoginActivity;
import com.u_binusportal.activity.entriesActivities.entries.RegisterActivity;
import com.u_binusportal.activity.entriesActivities.entries.RegisterUMKMActivity;
import com.u_binusportal.component.Umkm;
import com.u_binusportal.handlers.FragmentHandler;
import com.u_binusportal.handlers.InfoAppPageHandler;

import org.w3c.dom.Document;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = MainActivity.this.getSharedPreferences("everLogged", MODE_PRIVATE);
        if(preferences.getBoolean("ever_logged_in",true)){
            //pertama kali menggunakan app ini
            startActivity(new Intent(this, InfoAppPageHandler.class));
            preferences.edit().putBoolean("ever_logged_in",false).apply();
        } else{
            setContentView(R.layout.group_fragment);
            call();
        }
    }
    public void call() {
        Intent i = new Intent(this, FragmentHandler.class );
        startActivity(i);
    }

    // ini cuma untuk testing fragment dengan navigasi aja
    public void toFragment(View view) {
        Intent i = new Intent(this, FragmentHandler.class );
        startActivity(i);
    }
}