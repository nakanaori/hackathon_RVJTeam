package com.u_binusportal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.u_binusportal.R;
import com.u_binusportal.adapter.ProductListAdapter;
import com.u_binusportal.component.Product;

import java.util.ArrayList;

public class UMKMDetailsTokoTertentu extends AppCompatActivity {
    // View
    private ListView productListView;
    private ProductListAdapter productListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailumkm);
        productListView = (ListView)findViewById(R.id.listviewproduk);

        Product a = new Product("a","desca",10000, "test");

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(a);

        productListAdapter = new ProductListAdapter(this, R.layout.product_rows, productList);
        productListView.setAdapter(productListAdapter);
    }
}
