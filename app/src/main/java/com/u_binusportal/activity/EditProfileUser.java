package com.u_binusportal.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pchmn.materialchips.ChipsInput;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.activity.fragmentActivities.fragment.UserUMKMProfileFragment;
import com.u_binusportal.component.Category;
import com.u_binusportal.forTesting.UserTesting;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class EditProfileUser extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 9;
    private static final int CAMERA_REQUEST = 11;

    private EditText editedName;
    private EditText editedEmail;
    private ImageView editedImage;
    private Context context;
    private Button editButton;
    private String imageUri;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        context = this;

        editedName = (EditText)findViewById(R.id.editUserName);
        editedEmail = (EditText)findViewById(R.id.editUserEmail);
        editedImage = (ImageView) findViewById(R.id.editUserImage);
        editButton = (Button) findViewById(R.id.editUserButton);

        editedName.setText(Constant.currentUser.getUserName());
        editedEmail.setText(Constant.currentUser.getUserEmail());
        editedImage.setImageURI(Constant.currentUser.getUserImage());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updatedName = editedName.getText().toString();
                String updatedEmail = editedEmail.getText().toString();
                Uri updatedImage = imageUri == null ? null : Uri.parse(imageUri);
                Constant.currentUser.setUserName(updatedName);
                Constant.currentUser.setUserEmail(updatedEmail);
                Constant.currentUser.setUserImage(updatedImage);
                db.collection("Users").document(Constant.currentUser.getUserTelephoneNumber()).set(Constant.currentUser.storeToHash());
                finish();
            }
        });

        editedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });
    }

    private void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageUri = selectedImage.toString();
            editedImage.setImageURI(selectedImage);
        }
    }

}
