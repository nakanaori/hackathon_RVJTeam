package com.u_binusportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.activity.entriesActivities.entries.LoginActivity;
import com.u_binusportal.activity.entriesActivities.entries.RegisterActivity;
import com.u_binusportal.activity.entriesActivities.entries.RegisterUMKMActivity;
import com.u_binusportal.component.Umkm;
import com.u_binusportal.component.User;
import com.u_binusportal.handlers.FragmentHandler;
import com.u_binusportal.handlers.InfoAppPageHandler;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        Constant.db_static.collection("Umkm").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Constant.totalUmkm = new HashMap<>();
                Constant.UmkmArrayList = new ArrayList<>();
                List<DocumentSnapshot> lists = task.getResult().getDocuments();
                for(DocumentSnapshot list : lists){
                    Umkm umkm = new Umkm(list.getString("id"),
                            list.getString("name"),
                            list.getString("description"),
                            (List<String>) list.get("category"),
                            list.getString("address"),
                            list.getString("image") == null ? null : Uri.parse(list.getString("image")),
                            list.getString("imgId") == null ? 0 : Integer.parseInt(list.getString("imgId")),
                            list.getString("userId")
                    );
                    Log.v("Konstan","test" + umkm.getUmkmId());
                    Constant.UmkmArrayList.add(umkm);
                    Constant.totalUmkm.put(list.getString("userId"), umkm);
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), SplashScreen.class));
            }
        }, 5000);



    }


}