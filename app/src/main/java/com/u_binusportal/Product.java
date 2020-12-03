package com.u_binusportal;

import java.util.UUID;

public class Product {
    private String umkmId;
    private String productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private int productImage;
    private Uri imgURI;


    public Product(String umkmId,String productName, String productDescription, long productPrice, int productImage) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.umkmId = umkmId;
    }

    public Product(String umkmId, String productName, String productDescription, long productPrice) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.umkmId = umkmId;
    }

    public String getUmkmId() {
        return umkmId;
    }

    public void setUmkmId(String umkmId) {
        this.umkmId = umkmId;
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
