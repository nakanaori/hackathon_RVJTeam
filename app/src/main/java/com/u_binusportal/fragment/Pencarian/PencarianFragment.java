package com.u_binusportal.fragment.Pencarian;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.u_binusportal.DatabaseTestingJoe;
import com.u_binusportal.R;
import com.u_binusportal.UMKMListAdapter;
import com.u_binusportal.UMKMListItemForLV;
import com.u_binusportal.Umkm;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PencarianFragment extends Fragment {

    // view-view nya
    private EditText bar;
    private ImageButton searchButton;
    private ListView listview;

    private final DatabaseTestingJoe database = new DatabaseTestingJoe();
    private String queryUser;
    private UMKMListAdapter adapter; // adapter

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pencarian, container, false);
        inisialisasi(root);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void inisialisasi(View v) {
        bar = (EditText) v.findViewById(R.id.editTextTextPersonName);
        searchButton = (ImageButton) v.findViewById(R.id.imageButton);
        listview = (ListView) v.findViewById(R.id.lv_pencarian);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find();
            }
        });
        // INI NANTI NGEDIRECT KE PROFIL UMKM TERTENTU
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }

    private void find() {
        String dicari = bar.getText().toString();
        if(dicari.length() == 0) {
            queryUser = "";
        } else {
            queryUser = dicari;
        }

        // mulai cari dari sini
        ArrayList<Umkm> d_Umkm = database.getUmkm();
        ArrayList<UMKMListItemForLV> toBeShown = new ArrayList<>();

        if(queryUser.isEmpty() || queryUser.equals("")) {
            adapter = new UMKMListAdapter(getActivity(), new ArrayList<UMKMListItemForLV>());
            listview.setAdapter(adapter);
            return;
        }

        // cek setiap data yg ada di database

        for (Umkm x : d_Umkm) {
            Umkm currentUmkm = x;

            // 1.
            // CEK KALAU DIA NYARI NAMA UMKM / MISAL (KENTUCKY -> KENTUCKY FRIED CHICKEN)
            if(x.getUmkmName().toLowerCase().contains(queryUser)) {
                toBeShown.add(x.convertToBeShownInLV());
                continue;
            }

            // 2.
            // CEK APAKAH ADA SUBSTRING KATEGORI DI
            // QUERY (DIBANDINGKAN DENGAN KATEGORI DATA)

            String temp[] = queryUser.split(" ");
            int len = temp.length;
            int len2 = x.getUmkmCategory().length;
            boolean flag = false;

            for (String s : temp) {
                for (int j = 0; j < len2; j++) {
                    if (x.getUmkmCategory()[j].toLowerCase().contains(s)) {
                        toBeShown.add(x.convertToBeShownInLV());
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }

        }

        adapter = new UMKMListAdapter(getActivity(), toBeShown);
        listview.setAdapter(adapter); return;
    }

}
