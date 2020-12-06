package com.u_binusportal;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.component.Umkm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Async extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
            Constant.db_static.collection("Umkm").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    Constant.totalUmkm = new HashMap<>();
                    Constant.UmkmArrayList = new ArrayList<>();
                    List<DocumentSnapshot> lists = task.getResult().getDocuments();
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
                        Constant.UmkmArrayList.add(umkm);
                        Constant.totalUmkm.put(list.getString("userId"), umkm);
                    }
                }
            });
            return null;
        }
    }

