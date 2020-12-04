package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.u_binusportal.forTesting.DatabaseTest;
import com.u_binusportal.R;
import com.u_binusportal.component.User;

public class LoginActivity extends AppCompatActivity {

    private EditText LoginPhoneNumberField;
    private EditText LoginPasswordField;
    private Button LoginButton;
    private TextView RegisterLink;
    private ProgressDialog Progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DatabaseTest.addUser(new User("dodo", "dodo", "dodo", "081291358587",null));
        LoginPhoneNumberField = findViewById(R.id.login_phone_number);
        LoginPasswordField = findViewById(R.id.login_password);
        LoginButton = findViewById(R.id.login_button);
        RegisterLink = findViewById(R.id.sign_up_hyperlink);

        RegisterLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Progress = new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
//
    }

    private void checkLogin() {
        String phonenumber = LoginPhoneNumberField.getText().toString().trim();
        String password = LoginPasswordField.getText().toString().trim();
        if (TextUtils.isEmpty(phonenumber)) {
            LoginPhoneNumberField.requestFocus();
            LoginPhoneNumberField.setError("Nomor telepon tidak boleh kosong");
        } else if (TextUtils.getTrimmedLength(phonenumber) < 11 || TextUtils.getTrimmedLength(phonenumber) > 13) {
            LoginPhoneNumberField.requestFocus();
            LoginPhoneNumberField.setError("Jumlah digit diantara 11 dan 13");
        } else if (TextUtils.isEmpty(password)) {
            LoginPasswordField.requestFocus();
            LoginPasswordField.setError("Kata sandi tidak boleh kosong");
        } else {
            if (DatabaseTest.isRegistered(phonenumber, password)) {
                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

}
