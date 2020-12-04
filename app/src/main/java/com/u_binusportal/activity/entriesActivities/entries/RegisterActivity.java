package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.OTPGenerate;
import com.u_binusportal.forTesting.DatabaseTest;
import com.u_binusportal.R;
import com.u_binusportal.component.User;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText NameField;
    private EditText PhoneNumberField;
    private EditText EmailField;

    private Button RegisterButton;
    private ProgressDialog Progress;

    public ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);

        NameField = findViewById(R.id.input_nama_daftar_user);
        PhoneNumberField = findViewById(R.id.input_phone_number_daftar_user);
        EmailField = findViewById(R.id.input_email_daftar_user);
        RegisterButton = findViewById(R.id.button_daftar_user);

        Progress = new ProgressDialog(this);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterUser();
            }
        });

    }

    private void startRegisterUser() {
        String email = EmailField.getText().toString().trim();
        String name = NameField.getText().toString().trim();
        String phonenumber = PhoneNumberField.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(name) > 50) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh lebih dari 50 karakter");

        } else if (TextUtils.isEmpty(phonenumber)) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(phonenumber) < 11 || TextUtils.getTrimmedLength(phonenumber) > 13) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon harus diantara 11-13 digit");
        } else {

//                Progress.setMessage("Signing up ...");
//                Progress.show();
                User newUser = new User(name,email,phonenumber,null);
                DatabaseTest.addUser(newUser);
//                Progress.dismiss();
                Toast.makeText(RegisterActivity.this,"Register Success", Toast.LENGTH_LONG).show();
                UserTesting.hasUser = true;
                
                // INI INTENTNYA UNTUK KE OTP
                startActivity(new Intent(this, OTPGenerate.class));
            }

        }
}

