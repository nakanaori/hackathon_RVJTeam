package com.u_binusportal.activity.fragmentActivities.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.Async;
import com.u_binusportal.Constant;
import com.u_binusportal.activity.UMKMDetailsTokoTertentu;
import com.u_binusportal.R;
import com.u_binusportal.adapter.UMKMListAdapter;
import com.u_binusportal.component.Umkm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HalamanUtamaFragment extends Fragment {

    // ini nanti ambil dari database dengan umkm id
    private ListView listView;
    private UMKMListAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                         ViewGroup container, Bundle savedInstanceState) {

        View root = layoutInflater.inflate(R.layout.halaman_utama, container, false);

        try {
            inisialisasi(root);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return root;
    }

    private void inisialisasi(View v) throws InterruptedException {
        ListView listview = (ListView) v.findViewById(R.id.list_view_hu);

        adapter = new UMKMListAdapter(getActivity(), R.layout.custom_list_item_home, Constant.UmkmArrayList);
        Log.v("test", Constant.UmkmArrayList == null ? "null" : "gk null");
        listview.setAdapter(adapter);

//         INI NANTI NGEDIRECT KE PROFIL UMKM TERTENTU
//        // BELUM DIMASUKKAN LOGIC
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent umkmIntent = new Intent(getActivity(), UMKMDetailsTokoTertentu.class);
                umkmIntent.putExtra("umkm", (Parcelable) adapter.getItem(i));
                startActivity(umkmIntent);
            }
        });
    }
}
