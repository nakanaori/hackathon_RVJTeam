package com.u_binusportal;

import android.net.Uri;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.component.UMKMListItemForLV;
import com.u_binusportal.component.Umkm;
import com.u_binusportal.component.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constant {
    public static FirebaseFirestore db_static = FirebaseFirestore.getInstance();
    public static User currentUser;
    public static Umkm currentUmkm;
    public static HashMap<String, Umkm> totalUmkm = new HashMap<>();
    public static ArrayList<Umkm> UmkmArrayList = new ArrayList<>();

    public static void updateUmkm(){
        db_static.collection("UMKM").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                totalUmkm.clear();
                UmkmArrayList.clear();
                List<DocumentSnapshot> lists = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot list : lists){
                    Umkm umkm = new Umkm(list.getString("id"),
                            list.getString("name"),
                            list.getString("description"),
                            (String[]) list.get("category"),
                            list.getString("address"),
                            list.getString("image") == null ? null : Uri.parse(list.getString("image")),
                            Integer.parseInt(list.getString("imgId")),
                            list.getString("userId")
                    );
                    UmkmArrayList.add(umkm);
                    totalUmkm.put(list.getString("userId"), umkm);
                }
            }
        });
    }
}
