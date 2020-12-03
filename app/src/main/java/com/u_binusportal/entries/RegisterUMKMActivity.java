package com.u_binusportal.entries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;

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

        UMKMNameField = findViewById(R.id.input_nama_daftar_user);
        AddressField = findViewById(R.id.input_alamat_daftar_umkm);
        DescriptionField = findViewById(R.id.umkm_description);
//        Category = findViewById(R.id.)

        RegisterUMKMButton = findViewById(R.id.button_daftar_umkm);

        Progress = new ProgressDialog(this);

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
