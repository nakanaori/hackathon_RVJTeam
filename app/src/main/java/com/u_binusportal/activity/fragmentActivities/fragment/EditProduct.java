package com.u_binusportal.activity.fragmentActivities.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;

public class EditProduct extends AppCompatActivity {
    private ImageView profileImage;
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

        profileImage = findViewById(R.id.productNewImage);
        addProductName = findViewById(R.id.addProductName);
        addProductDesc = findViewById(R.id.addProductDescription);
        addProductPrice = findViewById(R.id.addProductPrice);
        deleteProductName = findViewById(R.id.deleteProductName);
        addButton = findViewById(R.id.add_product);
        deleteButton = findViewById(R.id.delete_product);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
