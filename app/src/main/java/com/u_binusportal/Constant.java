package com.u_binusportal;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.u_binusportal.component.UMKMListItemForLV;
import com.u_binusportal.component.Umkm;
import com.u_binusportal.component.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constant {
    public static StorageReference strRef = FirebaseStorage.getInstance().getReference();;
    public static FirebaseFirestore db_static = FirebaseFirestore.getInstance();
    public static User currentUser;
    public static Umkm currentUmkm;
    public static HashMap<String, Umkm> totalUmkm;
    public static ArrayList<Umkm> UmkmArrayList;


}
