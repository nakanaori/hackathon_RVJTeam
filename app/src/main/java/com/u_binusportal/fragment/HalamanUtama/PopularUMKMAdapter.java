package com.u_binusportal.fragment.HalamanUtama;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.u_binusportal.R;

import java.util.ArrayList;
import java.util.List;


public class PopularUMKMAdapter extends ArrayAdapter<HalamanUtamaModel> {

//    private static int resource = R.layout.custom_list_item_home;
    private Context c;
    private List<HalamanUtamaModel> item;
//    private Activity c;

    public PopularUMKMAdapter(@NonNull Context context, @NonNull ArrayList<HalamanUtamaModel> objects) {
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
        HalamanUtamaModel currentItem = item.get(position);

        ImageView umkmPhotos = (ImageView) listItem.findViewById(R.id.imageViewListHU);
        umkmPhotos.setImageResource(currentItem.getImageR());

        TextView namaUMKM = (TextView) listItem.findViewById(R.id.textViewHeader);
        namaUMKM.setText(currentItem.getTitle());

        TextView catUMKM = (TextView) listItem.findViewById(R.id.textViewCategory);
        catUMKM.setText(currentItem.getDesc());

        return listItem;
    }
}
