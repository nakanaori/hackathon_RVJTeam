package com.u_binusportal;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pchmn.materialchips.ChipsInput;

import java.util.ArrayList;

public class DaftarUMKM extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_umkm);

        ChipsInput category = (ChipsInput) findViewById(R.id.addUmkmCategory);
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("C001","Makanan"));
        categoryList.add(new Category("C002","Minuman"));
        categoryList.add(new Category("C003","Pakaian"));
        categoryList.add(new Category("C004","Hobi"));
        categoryList.add(new Category("C005","Kosmetik"));
        categoryList.add(new Category("C006","Pertanian"));
        categoryList.add(new Category("C007","Elektronik"));
        categoryList.add(new Category("C008","Perabotan Rumah Tangga"));
        categoryList.add(new Category("C009","Jasa"));
        categoryList.add(new Category("C010","Survernir"));
        categoryList.add(new Category("C011","Kesehatan"));
        categoryList.add(new Category("C012","Lain-lain"));
        category.setFilterableList(categoryList);
    }
}
