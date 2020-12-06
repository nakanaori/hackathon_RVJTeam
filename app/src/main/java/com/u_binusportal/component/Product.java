package com.u_binusportal.component;

import android.net.Uri;
import java.util.UUID;

public class Product {
    private String productId;
    private String productName;
    private String productDescription;
    private long productPrice;
    private int productImage;
    private Uri imgURI;
    private String umkmId;

    public Product(String productId, String productName, String productDescription, long productPrice, int productImage, Uri imgURI, String umkmId) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.imgURI = imgURI;
        this.umkmId = umkmId;
    }

    public Product(String productName, String productDescription, long productPrice,
                   Uri ImageURI, int productImage, String umkmID) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.imgURI = ImageURI;
        this.umkmId = umkmID;
    }

    public Product(String productName, String productDescription, long productPrice, String umkmID) {
        this.productId = UUID.randomUUID().toString();
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.umkmId = umkmID;
    }

    public Uri getImgURI() {
        return imgURI;
    }

    public void setImgURI(Uri imgURI) {
        this.imgURI = imgURI;
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
