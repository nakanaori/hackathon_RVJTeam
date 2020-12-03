package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pchmn.materialchips.ChipsInput;
import com.u_binusportal.component.Category;
import com.u_binusportal.R;

import java.util.ArrayList;

public class RegisterUMKMActivity extends AppCompatActivity {

    private EditText UMKMNameField;
    private EditText AddressField;
    private EditText DescriptionField;
    private Spinner Category;

    private Button RegisterUMKMButton;
    private ProgressDialog Progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_umkm);

        UMKMNameField = findViewById(R.id.addUmkmName);
        AddressField = findViewById(R.id.addUmkmAddress);
        DescriptionField = findViewById(R.id.addUmkmDescription);
//        Category = findViewById(R.id.)

        RegisterUMKMButton = findViewById(R.id.registerUmkm);

        Progress = new ProgressDialog(this);

        ChipsInput category = (ChipsInput) findViewById(R.id.addUmkmCategory);
        ArrayList<com.u_binusportal.component.Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("C001","Makanan"));
        categoryList.add(new Category("C002","Minuman"));
        categoryList.add(new Category("C003","Pakaian"));
        categoryList.add(new Category("C004","Hobi"));
        categoryList.add(new Category("C005","Kosmetik"));
        categoryList.add(new Category("C006","Pertanian"));
        categoryList.add(new Category("C007","Elektronik"));
        categoryList.add(new Category("C008","Perabotan Rumah Tangga"));
        categoryList.add(new Category("C009","Jasa"));
        categoryList.add(new Category("C010","Survernir"));
        categoryList.add(new Category("C011","Kesehatan"));
        categoryList.add(new Category("C012","Lain-lain"));
        category.setFilterableList(categoryList);


        RegisterUMKMButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startRegisterUser();
            }
        });
    }

    private void startRegisterUMKM() {
        String name = UMKMNameField.getText().toString().trim();
        String description = DescriptionField.getText().toString().trim();
        String address = AddressField.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            UMKMNameField.requestFocus();
            UMKMNameField.setError("Empty name");
        } else if (TextUtils.isEmpty(description)) {
            DescriptionField.requestFocus();
            DescriptionField.setError("Empty description");
        } else if (TextUtils.isEmpty(address)) {
            AddressField.requestFocus();
            AddressField.setError("Empty address");
        }

    }
}
