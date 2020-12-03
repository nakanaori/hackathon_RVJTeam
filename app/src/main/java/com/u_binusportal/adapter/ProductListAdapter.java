package com.u_binusportal.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.u_binusportal.component.Product;
import com.u_binusportal.R;

import java.util.ArrayList;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private Context context;
    int resource;

    public ProductListAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String productName = getItem(position).getProductName();
        String productDesciption = getItem(position).getProductDescription();
        String productId = getItem(position).getProductId();
        long productPrice = getItem(position).getProductPrice();
        int productImage = getItem(position).getProductImage();
        Uri productUri = getItem(position).getProductURI();

        Product product = new Product(productName, productDesciption, productPrice, productUri, productImage, "TEST");
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);

        TextView txtProductName = (TextView) convertView.findViewById(R.id.productName);
        TextView txtProductDescription = (TextView) convertView.findViewById(R.id.productDescription);
        TextView txtProductPrice = (TextView) convertView.findViewById(R.id.productPrice);
        ImageView imgProductImage = (ImageView) convertView.findViewById(R.id.productImage);

        txtProductName.setText(productName);
        txtProductDescription.setText(productDesciption);
        txtProductPrice.setText(Long.toString(productPrice));
        imgProductImage.setImageResource(productImage);

        return convertView;
    }
}
