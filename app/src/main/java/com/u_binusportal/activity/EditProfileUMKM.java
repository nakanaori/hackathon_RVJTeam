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
import com.u_binusportal.MainActivity;
import com.u_binusportal.R;
import com.u_binusportal.component.Category;

import java.util.ArrayList;

public class EditProfileUMKM extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 9;
    private static final int CAMERA_REQUEST = 11;
    private EditText editedName;
    private EditText editedAddress;
    private EditText editedDescription;
    private ChipsInput editedCategegory;
    private ImageView editedImage;
    private Button editButton, editProduk;
    private ChipsInput category;
    private ArrayList<Category> categoryList;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_umkm);
        context = this;

        category = (ChipsInput) findViewById(R.id.editUMKMCategory);
        categoryList = new ArrayList<>();
        categoryList.add(new Category("C001", "Makanan"));
        categoryList.add(new Category("C002", "Minuman"));
        categoryList.add(new Category("C003", "Pakaian"));
        categoryList.add(new Category("C004", "Hobi"));
        categoryList.add(new Category("C005", "Kosmetik"));
        categoryList.add(new Category("C006", "Pertanian"));
        categoryList.add(new Category("C007", "Elektronik"));
        categoryList.add(new Category("C008", "Perabotan Rumah Tangga"));
        categoryList.add(new Category("C009", "Jasa"));
        categoryList.add(new Category("C010", "Survernir"));
        categoryList.add(new Category("C011", "Kesehatan"));
        categoryList.add(new Category("C012", "Lain-lain"));
        category.setFilterableList(categoryList);

        editedName = (EditText) findViewById(R.id.editUMKMName);
        editedAddress = (EditText) findViewById(R.id.editUMKMAddress);
        editedDescription = (EditText) findViewById(R.id.editUMKMDescription);
        editedCategegory = (ChipsInput) findViewById(R.id.editUMKMCategory);
        editedImage = (ImageView) findViewById(R.id.editUmkmImage);
        editButton = (Button) findViewById(R.id.editUmkmButton);
        editProduk = (Button)findViewById(R.id.aturProdukButton);

        editedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageOptionDialog();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("EditedText", editedName.getText().toString());
            }
        });

        editProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pindah ke dodo
                Intent i = new Intent(getApplication(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void getImageFromGallery() {
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
            editedImage.setImageURI(selectedImage);
        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            editedImage.setImageBitmap(bitmap);
        }
    }

    private void showImageOptionDialog() {
        final String[] options = getResources().getStringArray(R.array.image_options);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.alert_dialog_title).setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
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

    private void capturePictureFromCamera() {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}
