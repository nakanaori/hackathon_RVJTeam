package com.u_binusportal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.internal.$Gson$Preconditions;
import com.u_binusportal.R;
import com.u_binusportal.adapter.ProductListAdapter;
import com.u_binusportal.component.Product;
import com.u_binusportal.component.Umkm;

import java.util.ArrayList;
import java.util.List;

public class UMKMDetailsTokoTertentu extends AppCompatActivity {
    // View
    private ListView productListView;
    private ProductListAdapter productListAdapter;
    private Umkm umkm;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailumkm);
        productListView = (ListView)findViewById(R.id.listviewproduk);
        umkm = getIntent().getParcelableExtra("umkm");
        final ArrayList<Product> productList = new ArrayList<>();
        //ambil product umkm dari server
        db.collection("Produk").document(umkm.getUmkmId()).collection("UMKMProduct").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> products = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot product : products){
                    Product detail = new Product(product.getString("id"),
                            product.getString("name"),
                            product.getString("description"),
                            Long.parseLong(product.getString("price")),
                            Integer.parseInt(product.getString("imageInt")),
                            product.getString("image") == null ? null : Uri.parse(product.getString("image")),
                            product.getString("umkmId"));
                    productList.add(detail);
                }
            }
        });

        productListAdapter = new ProductListAdapter(this, R.layout.product_rows, productList);
        productListView.setAdapter(productListAdapter);
    }
}
