package com.u_binusportal.handlers;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.activity.fragmentActivities.fragment.HalamanUtamaFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.PencarianFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UnregisteredUserFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UserProfileFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UserUMKMProfileFragment;
import com.u_binusportal.component.User;
import com.u_binusportal.forTesting.UserTesting;

public class FragmentHandler extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean umkmExists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.group_fragment); // main page
        final BottomNavigationView botNav = findViewById(R.id.bottom_menu_navigation); // set bar di bawah
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HalamanUtamaFragment()).commit();

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch(item.getItemId()) {
                    case R.id.bot_navigation_home:
                        botNav.getMenu().getItem(0).setChecked(true);
                        selectedFragment = new HalamanUtamaFragment();
                        break;
                    case R.id.bot_navigation_search:
                        botNav.getMenu().getItem(1).setChecked(true);
                        selectedFragment = new PencarianFragment();
                        break;
                    case R.id.bot_navigation_profil:
                        botNav.getMenu().getItem(2).setChecked(true);
                        Log.v("User Sekarang",Constant.currentUser == null ? "usernya null" : "usernya gk null" + Constant.currentUser.toString());
                        if(firebaseAuth.getCurrentUser() != null){
                            db.collection("Users").document(firebaseAuth.getCurrentUser().getPhoneNumber()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    User user = new User(documentSnapshot.getString("id"),
                                            documentSnapshot.getString("name"),
                                            documentSnapshot.getString("email"),
                                            documentSnapshot.getString("phoneNumber"),
                                            documentSnapshot.getString("image") == null ? null : Uri.parse(documentSnapshot.getString("image")));
                                    Constant.currentUser = user;
                                }
                            });
                            db.collection("UMKM").document(firebaseAuth.getCurrentUser().getPhoneNumber()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if(documentSnapshot.exists()){
                                       umkmExists = true;
                                    }else{
                                        umkmExists = false;
                                    }
                                }
                            });
                            if(umkmExists){
                                selectedFragment = new UserUMKMProfileFragment();
                            }else{
                                selectedFragment = new UserProfileFragment();
                            }
                        }else{
                            selectedFragment = new UnregisteredUserFragment();
                        }
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}

