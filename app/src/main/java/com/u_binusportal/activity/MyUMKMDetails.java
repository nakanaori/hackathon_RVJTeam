package com.u_binusportal.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.u_binusportal.Constant;
import com.u_binusportal.R;
import com.u_binusportal.adapter.ProductListAdapter;
import com.u_binusportal.component.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MyUMKMDetails extends AppCompatActivity {

    // View
    private ListView productListView;
    private ProductListAdapter productListAdapter;
    private Button buttonEdit;
    private TextView name;
    private TextView description;
    private ImageView image;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Product> productList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_umkm_private);
        productListView = (ListView)findViewById(R.id.listviewproduk);
        buttonEdit = (Button)findViewById(R.id.btnUbah);
        name = (TextView)findViewById((R.id.namaUMKMPrivate));
        description = (TextView) findViewById(R.id.deskripsiUMKMPrivate);
        image = (ImageView)findViewById(R.id.umkmImage);
        Log.v("test","name" + name == null ?  "null" : "gk null");
        name.setText(Constant.currentUmkm.getUmkmName());
        description.setText(Constant.currentUmkm.getUmkmDescription());
        image.setImageURI(Constant.currentUmkm.getUmkmImage());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               move();
            }
        });

        productList = new ArrayList<>();
        Log.v("id", "umkmId = " + Constant.currentUmkm.getUmkmId());
        db.collection("Produk").document(Constant.currentUmkm.getUmkmId()).collection("UMKMProduk").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.v("sukses", "Sukses ambil data");
                if(!queryDocumentSnapshots.isEmpty()){
                    Log.v("Sukses","data tidak kosong");
                    List<DocumentSnapshot> products = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot product : products){
                        Product detail = new Product(product.getString("id"),
                                product.getString("name"),
                                product.getString("description"),
                                Long.parseLong(product.get("price").toString()),
                                product.get("imageInt") == null ? 0 : Integer.parseInt(product.get("imageInt").toString()),
                                product.getString("image") == null ? null : Uri.parse(product.getString("image")),
                                product.getString("umkmId"));
                        productList.add(detail);
                    }
                    Log.v("masuk sini","masuk sini");
                    productListAdapter = new ProductListAdapter(MyUMKMDetails.this, R.layout.product_rows, productList);
                    productListView.setAdapter(productListAdapter);
                }
            }
        });


    }

    private void move() {
        startActivity(new Intent(getApplication(), EditProfileUMKM.class));
    }



}
