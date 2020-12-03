package com.u_binusportal;

import android.net.Uri;

import java.net.URI;

public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private int productImage;
    private Uri imgURI;

    public Product(String productId, String productName, String productDescription, long productPrice, Uri ImageURI, int productImage) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.imgURI = ImageURI;
    }

    public Product(String productId, String productName, String productDescription, long productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public Uri getProductURI() {
        return this.imgURI;
    }

    public void setProductURI(Uri u) {
        this.imgURI = u;
    }

}
