package com.u_binusportal.entries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText NameField;
    private EditText PasswordField;
    private EditText ConfirmPasswordField;
    private EditText PhoneNumberField;
    private EditText EmailField;

    private Button RegisterButton;
    private ProgressDialog Progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);

        NameField = findViewById(R.id.input_nama_daftar_user);
        PasswordField = findViewById(R.id.input_password_daftar_user);
        ConfirmPasswordField = findViewById(R.id.input_konfirmasi_password_daftar_user);
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

    private void startRegisterUser(){
        String email = EmailField.getText().toString().trim();
        String password = PasswordField.getText().toString().trim();
        String confirm = ConfirmPasswordField.getText().toString().trim();
        String name = NameField.getText().toString().trim();
        String phonenumber = PhoneNumberField.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            NameField.requestFocus();
            NameField.setError("Empty name");
        } else if (TextUtils.isEmpty(password)){

            // Set the error message
            PasswordField.requestFocus();
            PasswordField.setError("Empty password");

        } else if (TextUtils.isEmpty(confirm)) {

            // Set the error message
            ConfirmPasswordField.requestFocus();
            ConfirmPasswordField.setError("Empty password");

        } else if (TextUtils.isEmpty(phonenumber)){
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Empty phone number");

        } else if (TextUtils.isEmpty(email)) {

            // Set the error message
            EmailField.requestFocus();
            EmailField.setError("Empty email");

        }  else {

            if (!password.equals(confirm)) { // Password and confirm aren't equal

                // Set the error message
                ConfirmPasswordField.requestFocus();
                ConfirmPasswordField.setError("Password didn't match");
            }else{
                Progress.setMessage("Signing up ...");
                Progress.show();
            }
        }

}}
