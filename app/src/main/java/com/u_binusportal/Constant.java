package com.u_binusportal;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
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
    public static  StorageReference strRef = FirebaseStorage.getInstance().getReference();;
    public static FirebaseFirestore db_static = FirebaseFirestore.getInstance();
    public static User currentUser;
    public static Umkm currentUmkm;
    public static HashMap<String, Umkm> totalUmkm;
    public static ArrayList<Umkm> UmkmArrayList;

    public static void updateUmkm(){
        db_static.collection("Umkm").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                totalUmkm = new HashMap<>();
                UmkmArrayList = new ArrayList<>();
                List<DocumentSnapshot> lists = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot list : lists){
                    Umkm umkm = new Umkm(list.getString("id"),
                            list.getString("name"),
                            list.getString("description"),
                            (List<String>) list.get("category"),
                            list.getString("address"),
                            list.getString("image") == null ? null : Uri.parse(list.getString("image")),
                            list.getString("imgId") == null ? 0 : Integer.parseInt(list.getString("imgId")),
                            list.getString("userId")
                    );
                    Log.v("Konstan","test" + umkm.getUmkmId());
                    UmkmArrayList.add(umkm);
                    totalUmkm.put(list.getString("userId"), umkm);
                }
            }
        });
    }
}
