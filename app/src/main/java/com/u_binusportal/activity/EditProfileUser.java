package com.u_binusportal.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.pchmn.materialchips.ChipsInput;
import com.u_binusportal.R;
import com.u_binusportal.activity.fragmentActivities.fragment.UserUMKMProfileFragment;
import com.u_binusportal.component.Category;
import com.u_binusportal.forTesting.UserTesting;

import java.util.ArrayList;

public class EditProfileUser extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 9;
    private static final int CAMERA_REQUEST = 11;

    private EditText editedName;
    private EditText editedEmail;
    private EditText editedNoPhone;
    private ImageView editedImage;
    private Context context;
    private ArrayList<Category> categoryList;
    private Button editButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        context = this;

        editedName = (EditText)findViewById(R.id.editUserName);
        editedEmail = (EditText)findViewById(R.id.editUserEmail);
        editedNoPhone = (EditText)findViewById(R.id.editUserNoTelp);
        editedImage = (ImageView) findViewById(R.id.editUserImage);
        editButton = (Button) findViewById(R.id.editUserButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("EditedText", editedName.getText().toString());
                // update disini
                finish();
            }
        });

        editedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageOptionDialog();
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
        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            editedImage.setImageURI(selectedImage);
        }else if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            editedImage.setImageBitmap(bitmap);
        }
    }

    private void showImageOptionDialog(){
        final String[] options = getResources().getStringArray(R.array.image_options);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.alert_dialog_title)
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which){
                            case 0:
                                getImageFromGallery();
                                break;
                            case 1:
                                capturePictureFromCamera();
                                break;
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void capturePictureFromCamera(){
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}
