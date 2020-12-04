package com.u_binusportal.handlers;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.u_binusportal.R;
import com.u_binusportal.activity.fragmentActivities.fragment.HalamanUtamaFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.PencarianFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UnregisteredUserFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UserProfileFragment;
import com.u_binusportal.activity.fragmentActivities.fragment.UserUMKMProfileFragment;
import com.u_binusportal.forTesting.UserTesting;

public class FragmentHandler extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.group_fragment); // main page
        BottomNavigationView botNav = findViewById(R.id.bottom_menu_navigation); // set bar di bawah
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HalamanUtamaFragment()).commit();

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch(item.getItemId()) {
                    case R.id.bot_navigation_home:
                        selectedFragment = new HalamanUtamaFragment();
                        break;
                    case R.id.bot_navigation_search:
                        selectedFragment = new PencarianFragment();
                        break;
                    case R.id.bot_navigation_profil:
                        if(UserTesting.hasUser && UserTesting.isUserHasUMKM) selectedFragment = new UserUMKMProfileFragment();
                        else if(UserTesting.hasUser && !UserTesting.isUserHasUMKM) selectedFragment = new UserProfileFragment();
                        else selectedFragment = new UnregisteredUserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
                return false;
            }
        });

//        NavController navController;
//        AppBarConfiguration appBarConfiguration;
//
//
//        if(hasUser && isUserHasUMKM) {
//            Log.v("masuk kondisi", "user punya umkm");
//            navController = Navigation.findNavController(this, R.id.nav_inti);
//
//            appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.id.navigation_home, R.id.navigation_search, R.id.navigation_profil_user_umkm).build();
//
//        } else if(hasUser && !isUserHasUMKM) {
//            Log.v("masuk kondisi", "user saja");
//            navController = Navigation.findNavController(this, R.id.nav_inti);
//
//            appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.id.navigation_home, R.id.navigation_search, R.id.navigation_profil_user).build();
//        } else {
//            Log.v("masuk kondisi", "no user");
//            navController = Navigation.findNavController(this, R.id.nav_inti);
//
//            appBarConfiguration = new AppBarConfiguration.Builder(
//                    R.id.navigation_home, R.id.navigation_search, R.id.navigation_profil_nouser).build();
//        }
//
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(botNav, navController);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}

