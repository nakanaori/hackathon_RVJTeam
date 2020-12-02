package com.u_binusportal.fragment.HalamanUtama;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.u_binusportal.R;

import java.util.ArrayList;

public class HalamanUtamaFragment extends Fragment {

    // ini nanti ambil dari database dengan umkm id

    private String [] storeName = {"Kentucky Fried Chicken", "Mc'Donalds", "Doner Kebab", "Alam Jelita Fried Rice"};
    private String [] cat = {
            "Kategori: Makanan, Minuman, Kosmetik", "Kategori: Makanan, Minuman, Kosmetik",
            "Kategori: Makanan, Minuman, Kosmetik", "Kategori: Makanan, Minuman, Kosmetik"
    };
    private int [] images = {R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon};

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater,
                         ViewGroup container, Bundle savedInstanceState) {
        View root = layoutInflater.inflate(R.layout.halaman_utama, container, false);
        ListView listview = (ListView) root.findViewById(R.id.list_view_hu);
        ArrayList<HalamanUtamaModel> items = new ArrayList<>();

        int i=0;
        for(i=0; i<4; i++) {
            items.add(new HalamanUtamaModel(storeName[i], cat[i], images[i]));
        }

        PopularUMKMAdapter adapter =
                new PopularUMKMAdapter(getActivity(), items);
        listview.setAdapter(adapter);

        return root;
    }
}
