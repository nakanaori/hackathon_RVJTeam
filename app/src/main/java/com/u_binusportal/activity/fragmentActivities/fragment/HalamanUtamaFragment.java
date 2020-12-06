package com.u_binusportal.activity.fragmentActivities.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.u_binusportal.Constant;
import com.u_binusportal.activity.UMKMDetailsTokoTertentu;
import com.u_binusportal.R;
import com.u_binusportal.adapter.UMKMListAdapter;

public class HalamanUtamaFragment extends Fragment {

    // ini nanti ambil dari database dengan umkm id
    private ListView listView;

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                         ViewGroup container, Bundle savedInstanceState) {
        View root = layoutInflater.inflate(R.layout.halaman_utama, container, false);
        inisialisasi(root);
        return root;
    }

    private void inisialisasi(View v) {
        ListView listview = (ListView) v.findViewById(R.id.list_view_hu);

        Constant.updateUmkm();

        final UMKMListAdapter adapter = new UMKMListAdapter(getActivity(), R.layout.custom_list_item_home, Constant.UmkmArrayList);
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
