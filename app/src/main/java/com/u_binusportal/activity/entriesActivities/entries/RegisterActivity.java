package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.forTesting.DatabaseTest;
import com.u_binusportal.R;
import com.u_binusportal.component.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText NameField;
    private EditText PasswordField;
    private EditText ConfirmPasswordField;
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

    private void startRegisterUser() {
        String email = EmailField.getText().toString().trim();
        String password = PasswordField.getText().toString().trim();
        String confirm = ConfirmPasswordField.getText().toString().trim();
        String name = NameField.getText().toString().trim();
        String phonenumber = PhoneNumberField.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(name) > 50) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh lebih dari 50 karakter");

        } else if (TextUtils.isEmpty(password)) {
            PasswordField.requestFocus();
            PasswordField.setError("Kata sandi tidak boleh kosong");

        } else if (TextUtils.isEmpty(confirm)) {
            ConfirmPasswordField.requestFocus();
            ConfirmPasswordField.setError("Kata sandi tidak boleh kosong");

        } else if (TextUtils.isEmpty(phonenumber)) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(phonenumber)!=12 && TextUtils.getTrimmedLength(phonenumber)!=13) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon harus diantara 12-13 digit");
        } else {

            if (!password.equals(confirm)) { // Password and confirm aren't equal
                // Set the error message
                ConfirmPasswordField.requestFocus();
                ConfirmPasswordField.setError("Kata sandi tidak sesuai");
            } else {

//                Progress.setMessage("Signing up ...");
//                Progress.show();
                User newUser = new User(name,password,email,phonenumber,null);
                DatabaseTest.addUser(newUser);
//                Progress.dismiss();
                Toast.makeText(RegisterActivity.this,"Register Success", Toast.LENGTH_LONG).show();
            }

        }


    }
}

