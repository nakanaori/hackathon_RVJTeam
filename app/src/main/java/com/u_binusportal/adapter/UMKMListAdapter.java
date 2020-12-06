package com.u_binusportal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.u_binusportal.R;
import com.u_binusportal.component.Umkm;

import java.util.ArrayList;

// class ini akan dipakai untuk halaman utama dan pencarian

public class UMKMListAdapter extends ArrayAdapter<Umkm> {

    private Context c;
    private ArrayList<Umkm> item;
    int resource;

    public UMKMListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Umkm> item) {
        super(context, resource);
        this.c = context;
        this.item = item;
        this.resource = resource;
    }

    public Umkm getItem(int position){
        return item.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = LayoutInflater.from(c).inflate(R.layout.custom_list_item_home, parent, false);
        Umkm currentItem = item.get(position);

        ImageView umkmPhotos = (ImageView) listItem.findViewById(R.id.imageViewListHU);
        umkmPhotos.setImageResource(currentItem.getImageR());

        TextView namaUMKM = (TextView) listItem.findViewById(R.id.textViewHeader);
        namaUMKM.setText(currentItem.getUmkmName());

        TextView catUMKM = (TextView) listItem.findViewById(R.id.textViewCategory);
        catUMKM.setText(currentItem.getUmkmDescription());
        return listItem;
    }
}
