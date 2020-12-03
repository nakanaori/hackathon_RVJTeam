package com.u_binusportal;

import android.widget.Toast;

import com.u_binusportal.entries.LoginActivity;

import java.util.ArrayList;

public class DatabaseTest {
    public static ArrayList<User> users = new ArrayList<User>();

    public static void addUser(User newUser){
        users.add((new User("dodo", "dodo", "dodo", "081291358587", null)));
        users.add(newUser);
    }

    public static boolean isRegistered(String phonenumber, String password){

        for (int i = 0; i < users.size() ; i++) {
            if(users.get(i).getUserTelephoneNumber().equals(phonenumber) && users.get(i).getUserPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
