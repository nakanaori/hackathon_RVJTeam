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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.pchmn.materialchips.ChipsInput;
import com.u_binusportal.Constant;
import com.u_binusportal.MainActivity;
import com.u_binusportal.R;
import com.u_binusportal.activity.entriesActivities.entries.RegisterUMKMActivity;
import com.u_binusportal.component.Category;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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
    private TextView trigger;
    private String imgUri;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_umkm);
        context = this;

        editedName = (EditText) findViewById(R.id.editUMKMName);
        editedAddress = (EditText) findViewById(R.id.editUMKMAddress);
        editedDescription = (EditText) findViewById(R.id.editUMKMDescription);
        editedImage = (ImageView) findViewById(R.id.editUmkmImage);
        editButton = (Button) findViewById(R.id.editUmkmButton);
        editProduk = (Button)findViewById(R.id.aturProdukButton);
        trigger = (TextView)findViewById(R.id.tv_categories_editUMKM);

        editedName.setText(Constant.currentUmkm.getUmkmName());
        editedAddress.setText(Constant.currentUmkm.getUmkmAddress());
        editedDescription.setText(Constant.currentUmkm.getUmkmDescription());
        editedImage.setImageURI(Constant.currentUmkm.getUmkmImage());


        editedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageOptionDialog();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editedName.getText().toString();
                String address = editedAddress.getText().toString();
                String description = editedDescription.getText().toString();
                String imageUri = imgUri;
                Constant.currentUmkm.setUmkmName(name);
                Constant.currentUmkm.setUmkmAddress(address);
                Constant.currentUmkm.setUmkmDescription(description);
                Constant.currentUmkm.setUmkmImage(imageUri == null ? null : Uri.parse(imageUri));
                db.collection("Umkm").document(Constant.currentUser.getUserId()).set(Constant.currentUmkm.storeToHash());
                // update
                finish();
            }
        });

        editProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), EditProduct.class));
            }
        });

        final boolean [] checkedItems = new boolean[13];
        final String [] categories = getResources().getStringArray(R.array.category);
        final ArrayList<Integer> selectedCategory = new ArrayList<>();
        final List<String> listOfSelectedCategory = new ArrayList<>();
        final List<String> tempSelected = new ArrayList<>();

        trigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final android.app.AlertDialog.Builder dialog_cat = new android.app.AlertDialog.Builder(EditProfileUMKM.this);
                dialog_cat.setTitle("Choose any categories");
                dialog_cat.setMultiChoiceItems(categories, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked) {
                            if(!selectedCategory.contains(position)) {
                                selectedCategory.add(position);
                            } else {
                                selectedCategory.remove(position);
                            }
                        }
                    }
                });

                dialog_cat.setCancelable(false);
                dialog_cat.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String res = "";
                        tempSelected.clear();
                        trigger.setText("");
                        for (int idx : selectedCategory) {
                            res = res + categories[idx];
                            if(idx == selectedCategory.get(selectedCategory.size()-1)) break;
                            res = res + ", ";

                            // ini refresh category terpilih
                            if(tempSelected.isEmpty()) tempSelected.add(categories[idx]);
                            if(!tempSelected.contains(categories[idx]))
                                tempSelected.add(categories[idx]);
                        }
                        trigger.setText(res);
                    }
                });

                dialog_cat.setNegativeButton("Return", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                android.app.AlertDialog mDialog = dialog_cat.create();
                mDialog.show();
            }
        });

        listOfSelectedCategory.addAll(tempSelected);
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
            imgUri = selectedImage.toString();
            editedImage.setImageURI(selectedImage);
        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgUri = bitmapToUriConverter(bitmap).toString();
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

    public Uri bitmapToUriConverter(Bitmap mBitmap) {
        Uri uri = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 100, 100);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            Bitmap newBitmap = Bitmap.createScaledBitmap(mBitmap, 200, 200,
                    true);
            File file = new File(this.getFilesDir(), "Image"
                    + new Random().nextInt() + ".jpeg");
            FileOutputStream out = this.openFileOutput(file.getName(),
                    Context.MODE_WORLD_READABLE);
            newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //get absolute path
            String realPath = file.getAbsolutePath();
            File f = new File(realPath);
            uri = Uri.fromFile(f);

        } catch (Exception e) {
            Log.e("Your Error Message", e.getMessage());
        }
        return uri;
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private void capturePictureFromCamera() {
        Intent cameraIntent = new Intent();
        cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
}
