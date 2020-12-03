package com.u_binusportal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.u_binusportal.R;
import com.u_binusportal.component.UMKMListItemForLV;

import java.util.ArrayList;
import java.util.List;

// class ini akan dipakai untuk halaman utama dan pencarian

public class UMKMListAdapter extends ArrayAdapter<UMKMListItemForLV> {

    private Context c;
    private List<UMKMListItemForLV> item;

    public UMKMListAdapter(@NonNull Context context, @NonNull ArrayList<UMKMListItemForLV> objects) {
        super(context, R.layout.custom_list_item_home, objects);
        this.c = context;
        this.item = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(c).inflate(R.layout.custom_list_item_home, parent, false);
        }
        UMKMListItemForLV currentItem = item.get(position);

        ImageView umkmPhotos = (ImageView) listItem.findViewById(R.id.imageViewListHU);
        umkmPhotos.setImageResource(currentItem.getImageR());

        TextView namaUMKM = (TextView) listItem.findViewById(R.id.textViewHeader);
        namaUMKM.setText(currentItem.getTitle());

        TextView catUMKM = (TextView) listItem.findViewById(R.id.textViewCategory);
        catUMKM.setText(currentItem.getDesc());

        return listItem;
    }
}
