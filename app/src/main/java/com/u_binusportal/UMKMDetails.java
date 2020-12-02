package com.u_binusportal;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class UMKMDetails extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailumkm);
        ListView productListView = (ListView)findViewById(R.id.listviewproduk);

        Product a = new Product("0","a","desca",10000);
        Product b = new Product("1","b","descb",20000);
        Product c = new Product("2","c","descc",30000);
        Product d = new Product("3","d","descd",40000);
        Product e = new Product("4","e","desce",50000);
        Product f = new Product("5","f","descf",60000);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(a);
        productList.add(b);
        productList.add(c);
        productList.add(d);
        productList.add(e);
        productList.add(f);

        ProductListAdapter productListAdapter = new ProductListAdapter(this, R.layout.product_rows, productList);
        productListView.setAdapter(productListAdapter);

    }


}
