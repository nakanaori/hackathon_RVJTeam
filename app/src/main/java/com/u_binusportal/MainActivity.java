package com.u_binusportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private SharedPreferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = MainActivity.this.getSharedPreferences("everLogged", MODE_PRIVATE);
        Constant.updateUmkm();
        if(firebaseAuth.getCurrentUser() != null){
            userIsLogin();
        }
        if(preferences.getBoolean("ever_logged_in",true)){
            //pertama kali menggunakan app ini
            startActivity(new Intent(MainActivity.this, InfoAppPageHandler.class));
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

    public void userIsLogin(){
        db.collection("Users").document(firebaseAuth.getCurrentUser().getPhoneNumber()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.v("Kosong","Kosong ");
                User user = new User(documentSnapshot.getString("id"),
                        documentSnapshot.getString("name"),
                        documentSnapshot.getString("email"),
                        documentSnapshot.getString("phoneNumber"),
                        documentSnapshot.getString("image") == null ? null : Uri.parse(documentSnapshot.getString("image")));
                Constant.currentUser = user;
                db.collection("Umkm").document(Constant.currentUser.getUserId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Log.v("sini","masuk sini" + Constant.currentUser.getUserId());
                        Log.v("sini","document.exists" + documentSnapshot.exists());
                        if(documentSnapshot.exists()){
                            Constant.currentUmkm = new Umkm(documentSnapshot.getString("id"),
                                    documentSnapshot.getString("name"),
                                    documentSnapshot.getString("description"),
                                    (List<String>) documentSnapshot.get("category"),
                                    documentSnapshot.getString("address"),
                                    documentSnapshot.getString("image") == null ? null : Uri.parse(documentSnapshot.getString("image")),
                                    documentSnapshot.getString("imgId") == null ? 0 : Integer.parseInt(documentSnapshot.getString("imgId")),
                                    documentSnapshot.getString("userId")
                            );
                        }
                    }
                });
            }
        });
    }
}