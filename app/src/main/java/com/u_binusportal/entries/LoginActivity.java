package com.u_binusportal.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.u_binusportal.R;

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

//        LoginPhoneNumberField = findViewById(R.id.login_phone_number);
//        LoginPasswordField = findViewById(R.id.login_password);
//        LoginButton = findViewById(R.id.login_button);
//        RegisterLink = findViewById(R.id.sign_up_hyperlink);
//
//        RegisterLink.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        LoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                checkLogin();
//            }
//        });
    }

    private void checkLogin(){
        String phonenumber = LoginPhoneNumberField.getText().toString().trim();
        String password = LoginPasswordField.getText().toString().trim();

        if (TextUtils.isEmpty(phonenumber)){ // Empty email field

            // Set the error message
            LoginPhoneNumberField.requestFocus();
            LoginPhoneNumberField.setError("Empty email");

        } else if (TextUtils.isEmpty(password)){ //Empty password field

            // Set the error message
            LoginPasswordField.requestFocus();
            LoginPasswordField.setError("Empty password");

        }
    }


}
