package com.u_binusportal.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;
import com.u_binusportal.component.Product;

public class EditProduct extends AppCompatActivity {
    private ImageView productImage;
    private EditText addProductName;
    private EditText addProductDesc;
    private EditText addProductPrice;
    private EditText deleteProductName;
    private Button addButton;
    private Button deleteButton;

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
                // ini logic nambah product umkm tersebut

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ini logic delete product umkm tersebut

            }
        });
    }
}
