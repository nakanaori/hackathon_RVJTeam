package com.u_binusportal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
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
import com.u_binusportal.component.User;
import com.u_binusportal.forTesting.UserTesting;
import com.u_binusportal.handlers.FragmentHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OTPGenerate extends AppCompatActivity {

    private PinView pinView;
    private Button btnVerif, btnResend;
    private String OTP;
    private FirebaseAuth firebaseAuth;
    private Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String phoneNumber;
    private String name;
    private String email;
    private PhoneAuthProvider.ForceResendingToken token;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_otp);
        inisialisasi();
        context = this;
    }

    public void inisialisasi() {
        pinView = findViewById(R.id.firstPinView);
        pinView.setTextColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.newBlue, getTheme()));
        pinView.setLineColor(
                ResourcesCompat.getColorStateList(getResources(), R.color.newBlue, getTheme()));

        token = getIntent().getParcelableExtra("token");
        OTP = getIntent().getStringExtra("auth");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");


        firebaseAuth = FirebaseAuth.getInstance();

        pinView.setItemCount(6);
        pinView.setItemHeight(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        pinView.setItemWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_size));
        pinView.setItemRadius(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_radius));
        pinView.setItemSpacing(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_spacing));
        pinView.setLineWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_item_line_width));
        pinView.setAnimationEnable(true);
        pinView.setCursorVisible(true);
        pinView.setCursorColor(
                ResourcesCompat.getColor(getResources(), R.color.newBlue, getTheme()));
        pinView.setCursorWidth(getResources().getDimensionPixelSize(R.dimen.pv_pin_view_cursor_width));

        pinView.setItemBackgroundColor(0);
        pinView.setItemBackground(getResources().getDrawable(R.color.white));
        pinView.setItemBackgroundResources(R.color.white);

        pinView.setHideLineWhenFilled(false);

        btnVerif = findViewById(R.id.verifBTN);
        btnVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check apakah betul, kalau betul
                String verifCode = pinView.getText().toString();
                if(!verifCode.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP, verifCode);
                    signIn(credential);
                }else{
                    Toast.makeText(OTPGenerate.this, "Tolong masukkan kode OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnResend = findViewById(R.id.resendOTP);
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signIn(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(context, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        Toast.makeText(context, "Kode OTP telah dikirimkan", Toast.LENGTH_SHORT).show();
                    }
                };

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(firebaseAuth)
                                .setForceResendingToken(token)
                                .setPhoneNumber(phoneNumber)
                                .setActivity(OTPGenerate.this)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setCallbacks(callbacks).build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });
    }


    private void signIn(final PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    db.collection("Users").document(phoneNumber).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()){
                                User newUser = new User(name, email, phoneNumber,null);
                                db.collection("Users").document(phoneNumber).set(newUser.storeToHash());
                                Constant.currentUser = newUser;
                            } else {
                                User user = new User(documentSnapshot.getString("id"),
                                        documentSnapshot.getString("name"),
                                        documentSnapshot.getString("email"),
                                        documentSnapshot.getString("phoneNumber"),
                                        documentSnapshot.getString("image") == null ? null : Uri.parse(documentSnapshot.getString("image")));
                                Constant.currentUser = user;
                            }
                        }
                    });
                    moveToFragment();
                }else{
                    Toast.makeText(OTPGenerate.this, "Kode OTP Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void moveToFragment() {
        // INI INTENTNYA
        UserTesting.hasUser = true;
        Intent i = new Intent(this, FragmentHandler.class);
        startActivity(i);
    }


}
