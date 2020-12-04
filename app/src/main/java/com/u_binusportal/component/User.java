package com.u_binusportal.component;

import android.net.Uri;

import java.util.UUID;

public class User {
    private String userId;
    private String userName;
    private String userEmail;
    private String userTelephoneNumber;
    private Uri userImage;

    public User(String userId, String userName, String userEmail, String userTelephoneNumber, Uri userImage) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userTelephoneNumber = userTelephoneNumber;
        this.userImage = userImage;
    }

    public Uri getUserImage() {
        return userImage;
    }

    public void setUserImage(Uri userImage) {
        this.userImage = userImage;
    }

    public User(String userName, String userEmail, String userTelephoneNumber, Uri userImage) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.userEmail = userEmail;
        this.userTelephoneNumber = userTelephoneNumber;
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    public void setUserTelephoneNumber(String userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
    }
}
