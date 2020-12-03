package com.u_binusportal.activity.fragmentActivities.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.u_binusportal.forTesting.DatabaseTestingJoe;
import com.u_binusportal.R;
import com.u_binusportal.adapter.UMKMListAdapter;
import com.u_binusportal.component.UMKMListItemForLV;
import com.u_binusportal.component.Umkm;

import java.util.ArrayList;

public class HalamanUtamaFragment extends Fragment {

    // ini nanti ambil dari database dengan umkm id
    private ListView listView;
    private final DatabaseTestingJoe database = new DatabaseTestingJoe();

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                         ViewGroup container, Bundle savedInstanceState) {
        View root = layoutInflater.inflate(R.layout.halaman_utama, container, false);
        inisialisasi(root);
        return root;
    }

    private void inisialisasi(View v) {
        ListView listview = (ListView) v.findViewById(R.id.list_view_hu);
        ArrayList<UMKMListItemForLV> items = new ArrayList<>();

        ArrayList<Umkm> temp = database.getUmkm();

        for (Umkm x : temp)
            items.add(x.convertToBeShownInLV());

        UMKMListAdapter adapter = new UMKMListAdapter(getActivity(), items);
        listview.setAdapter(adapter);
        // INI NANTI NGEDIRECT KE PROFIL UMKM TERTENTU
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }
}
