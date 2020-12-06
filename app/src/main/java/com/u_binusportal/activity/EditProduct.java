package com.u_binusportal.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.component.Product;

import java.util.HashMap;


public class EditProduct extends AppCompatActivity {
    private ImageView productImage;
    private EditText addProductName;
    private EditText addProductDesc;
    private EditText addProductPrice;
    private EditText deleteProductName;
    private Button addButton;
    private Button deleteButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                Product newProd = getProductInserted();
                HashMap<String, Object> hash = storeToHashmap(newProd);
                db.collection("Produk").document(Constant.currentUmkm.getUmkmId())
                        .collection("UMKMProduk").document(newProd.getProductId()).set(hash);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ini logic delete product umkm tersebut

            }
        });
    }

    private Product getProductInserted() {
        String pName = this.addProductName.getText().toString();
        String pDesc = this.addProductDesc.getText().toString();
        long pPrice = Long.parseLong(this.addProductPrice.getText().toString());
        String umkmID = Constant.currentUmkm.getUmkmId();

        // TIGA LINE INI BELUM KETEMU CARANYA
        ImageView img = null;
        int imageResources = Integer.parseInt(null);
        Uri imageUri = null;

        return new Product(pName, pDesc, pPrice, imageUri, imageResources, umkmID);
    }

    private HashMap<String, Object> storeToHashmap(Product product) {
        HashMap<String, Object> newHash = new HashMap<>();
        newHash.put("id", product.getProductId());
        newHash.put("name", product.getProductName());
        newHash.put("description", product.getProductDescription());
        newHash.put("price", product.getProductPrice());

        // ini ragu juga, apakah "image" yang kau mau Uri / ImageView ??
        newHash.put("imageInt", product.getProductImage());
        newHash.put("image", product.getImgURI());
        //
        newHash.put("umkmId", product.getUmkmId());
        return newHash;
    }

}
