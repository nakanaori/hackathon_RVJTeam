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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.component.Product;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class EditProduct extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 9;
    private ImageView productImage;
    private EditText addProductName;
    private EditText addProductDesc;
    private EditText addProductPrice;
    private EditText deleteProductName;
    private Button addButton;
    private Button deleteButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String imgUri;
    private Uri editedUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        productImage = findViewById(R.id.productNewImage);
        addProductName = findViewById(R.id.addProductName);
        addProductDesc = findViewById(R.id.addProductDescription);
        addProductPrice = findViewById(R.id.addProductPrice);
        addButton = findViewById(R.id.add_product);

        deleteProductName = findViewById(R.id.deleteProductName);
        deleteButton = findViewById(R.id.delete_product);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Product newProd = getProductInserted();
                if(newProd.getImgURI() != null){
                    final StorageReference productRef = Constant.strRef.child("Product_" + newProd.getProductId());
                    productRef.putFile(newProd.getImgURI()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            productRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    editedUri = uri;
                                    newProd.setImgURI(editedUri);
                                    HashMap<String, Object> hash = storeToHashmap(newProd);
                                    db.collection("Produk").document(Constant.currentUmkm.getUmkmId())
                                            .collection("UMKMProduk")
                                            .document(newProd.getProductId())
                                            .set(hash)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(EditProduct.this, "Produk ditambahkan", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    finish();
                                }
                            });
                        }
                    });
                }

            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ini logic delete product umkm tersebut
            final String name = deleteProductName.getText().toString();
            db.collection("Produk").document(Constant.currentUmkm.getUmkmId()).collection("UMKMProduct").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if(!queryDocumentSnapshots.isEmpty()){
                        List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot document : snapshotList){
                            if(document.getString("name").toLowerCase().equals(name.toLowerCase())){
                                db.collection("Produk").document(Constant.currentUmkm.getUmkmId()).collection("UMKMProduct").document(document.getString("id")).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(EditProduct.this, "Produk telah dihapus", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                                break;
                            }
                        }
                    }
                }
            });
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imgUri = selectedImage.toString();
            productImage.setImageURI(selectedImage);
        }
    }

    private void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    private Product getProductInserted() {
        String pName = this.addProductName.getText().toString();
        String pDesc = this.addProductDesc.getText().toString();
        long pPrice = Long.parseLong(this.addProductPrice.getText().toString());
        String umkmID = Constant.currentUmkm.getUmkmId();
        int imageResources = 0;
        Uri imageUri = imgUri == null ? null : Uri.parse(imgUri);

        return new Product(pName, pDesc, pPrice, imageUri, imageResources, umkmID);
    }

    private HashMap<String, Object> storeToHashmap(Product product) {
        HashMap<String, Object> newHash = new HashMap<>();
        newHash.put("id", product.getProductId());
        newHash.put("name", product.getProductName());
        newHash.put("description", product.getProductDescription());
        newHash.put("price", product.getProductPrice());
        newHash.put("image", product.getImgURI() == null ? null : product.getImgURI().toString());
        newHash.put("umkmId", product.getUmkmId());
        return newHash;
    }

}
