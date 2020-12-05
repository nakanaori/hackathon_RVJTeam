package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.provider.FirebaseInitProvider;
import com.u_binusportal.Constant;
import com.u_binusportal.OTPGenerate;
import com.u_binusportal.forTesting.DatabaseTest;
import com.u_binusportal.R;
import com.u_binusportal.component.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private EditText LoginPhoneNumberField;
    private Button LoginButton;
    private TextView RegisterLink;
    private ProgressDialog Progress;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String phonenumber;
    private String name;
    private String email;
    private String id;
    private Uri image;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginPhoneNumberField = findViewById(R.id.login_phone_number);
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
        phonenumber = LoginPhoneNumberField.getText().toString().trim().replaceFirst("0","+62");

        if (TextUtils.isEmpty(phonenumber)) {
            LoginPhoneNumberField.requestFocus();
            LoginPhoneNumberField.setError("Nomor telepon tidak boleh kosong");
        } else if (TextUtils.getTrimmedLength(phonenumber) < 13 || TextUtils.getTrimmedLength(phonenumber) > 15) {
            LoginPhoneNumberField.requestFocus();
            LoginPhoneNumberField.setError("Jumlah digit diantara 11 dan 13");
        } else {
            db.collection("Users").document(phonenumber).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(!documentSnapshot.exists()){
                        Toast.makeText(LoginActivity.this, "Akun ini belum terdaftar", Toast.LENGTH_SHORT).show();
                    }else{
                        name = documentSnapshot.getString("name");
                        email = documentSnapshot.getString("email");

                        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signIn(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(LoginActivity.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Toast.makeText(LoginActivity.this, "Kode OTP telah dikirimkan", Toast.LENGTH_SHORT).show();
                                Intent otpIntent = new Intent(LoginActivity.this, OTPGenerate.class);
                                otpIntent.putExtra("auth", s);
                                otpIntent.putExtra("name", name);
                                otpIntent.putExtra("email",email);
                                otpIntent.putExtra("phoneNumber", phonenumber);
                                otpIntent.putExtra("token",forceResendingToken);
                                startActivity(otpIntent);
                            }
                        };

                        PhoneAuthOptions options =
                                PhoneAuthOptions.newBuilder(firebaseAuth)
                                        .setPhoneNumber(phonenumber)
                                        .setTimeout(60L, TimeUnit.SECONDS)
                                        .setActivity(LoginActivity.this)
                                        .setCallbacks(callbacks)
                                        .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);
                    }
                }
            });
        }
    }

    private void signIn(final PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    db.collection("Users").document(phonenumber).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = new User(documentSnapshot.getString("id"),
                                    documentSnapshot.getString("name"),
                                    documentSnapshot.getString("email"),
                                    documentSnapshot.getString("phoneNumber"),
                                    documentSnapshot.getString("image") == null ? null : Uri.parse(documentSnapshot.getString("image")));
                            Constant.currentUser = user;
                        }
                    });
                }
            }
        });
    }

}
