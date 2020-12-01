package com.u_binusportal.fragment.HalamanUtama;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;

public class HalamanUtamaFragment extends AppCompatActivity {

    ListView listView;
    String [] storeName = {"Kentucky Fried Chicken", "Mc'Donalds", "Doner Kebab", "Alam Jelita Fried Rice"};
    String [] cat = {
            "Kategori: Makanan, Minuman, Kosmetik",
            "Kategori: Makanan, Minuman, Kosmetik",
            "Kategori: Makanan, Minuman, Kosmetik"
    };
    int [] images = {R.drawable.home_icon, R.drawable.home_icon, R.drawable.home_icon};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_utama);
        listView = findViewById(R.id.list_view_hu);
    }

    class HUAdapter extends ArrayAdapter<String> {

        Context context;
        String [] rTitle;
        String [] rDescription;
        int [] rImages;


        public HUAdapter(@NonNull Context context, String [] title, String [] desc, int [] img) {
            super(context, R.layout.custom_list_item_home, R.id.textViewHeader, title);
            this.context = context;
            this.rTitle = title;
            this.rDescription = desc;
            this.rImages = img;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.custom_list_item_home, parent, false);
            ImageView imgs = item.findViewById(R.id.imageViewListHU);
            TextView iTitle = item.findViewById(R.id.textViewHeader);
            TextView iCat = item.findViewById(R.id.textViewCategory);

            imgs.setImageResource(rImages[position]);
            iTitle.setText(rTitle[position]);
            iCat.setText(rDescription[position]);
            return item;
        }
    }

}
