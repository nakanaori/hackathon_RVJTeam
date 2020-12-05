package com.u_binusportal.activity.entriesActivities.entries;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.u_binusportal.Constant;
import com.u_binusportal.OTPGenerate;
import com.u_binusportal.forTesting.DatabaseTest;
import com.u_binusportal.R;
import com.u_binusportal.component.User;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    private EditText NameField;
    private EditText PhoneNumberField;
    private EditText EmailField;
    private String email;
    private String name;
    private String phonenumber;

    private Button RegisterButton;
    private ProgressDialog Progress;
    private FirebaseAuth firebaseAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private SharedPreferences preferences;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_user);
        firebaseAuth = FirebaseAuth.getInstance();
        preferences = RegisterActivity.this.getSharedPreferences(getString(R.string.preference_key), MODE_PRIVATE);
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
        email = EmailField.getText().toString().trim();
        name = NameField.getText().toString().trim();
        phonenumber = PhoneNumberField.getText().toString().trim().replaceFirst("0","+62");

        if (TextUtils.isEmpty(name)) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(name) > 50) {
            NameField.requestFocus();
            NameField.setError("Nama tidak boleh lebih dari 50 karakter");

        } else if (TextUtils.isEmpty(phonenumber)) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon tidak boleh kosong");

        } else if (TextUtils.getTrimmedLength(phonenumber) < 13 || TextUtils.getTrimmedLength(phonenumber) > 15) {
            PhoneNumberField.requestFocus();
            PhoneNumberField.setError("Nomor telepon harus diantara 11-13 digit");
        } else {
            db.collection("Users").document(phonenumber).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.exists()){
                        Toast.makeText(RegisterActivity.this, "User telah didaftarkan. Silahkan masuk", Toast.LENGTH_SHORT).show();
                    }else{
                        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signIn(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(RegisterActivity.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Toast.makeText(RegisterActivity.this, "Kode OTP telah dikirimkan", Toast.LENGTH_SHORT).show();
                                Intent otpIntent = new Intent(RegisterActivity.this, OTPGenerate.class);
                                otpIntent.putExtra("auth", s);
                                otpIntent.putExtra("name", name);
                                otpIntent.putExtra("email",email);
                                otpIntent.putExtra("phoneNumber", phonenumber);
                                startActivity(otpIntent);
                            }
                        };

                        PhoneAuthOptions options =
                                PhoneAuthOptions.newBuilder(firebaseAuth)
                                        .setPhoneNumber(phonenumber)
                                        .setTimeout(60L, TimeUnit.SECONDS)
                                        .setActivity(RegisterActivity.this)
                                        .setCallbacks(callbacks)
                                        .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            }
        }

        private void signIn(final PhoneAuthCredential credential){
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User newUser = new User(name, email, phonenumber,null);
                        Map<String, Object> res = new HashMap<>();
                        res.put("id",newUser.getUserId());
                        res.put("name",newUser.getUserName());
                        res.put("email", newUser.getUserEmail());
                        res.put("image", newUser.toString());
                        res.put("phoneNumber",newUser.getUserTelephoneNumber());
                        db.collection("Users").document(phonenumber).set(newUser);
                        Constant.currentUser = newUser;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.apply();
                        Toast.makeText(RegisterActivity.this, "Sukses Masuk!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(RegisterActivity.this, FragmentHandler.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}

