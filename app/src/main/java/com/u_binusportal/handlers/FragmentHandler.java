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
import com.u_binusportal.component.Umkm;
import com.u_binusportal.component.User;
import com.u_binusportal.forTesting.UserTesting;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class FragmentHandler extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean umkmExists;
    private Fragment selectedFragment;

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
                selectedFragment = null;
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
                        if(Constant.currentUser != null){
                            if(Constant.currentUmkm != null){
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

