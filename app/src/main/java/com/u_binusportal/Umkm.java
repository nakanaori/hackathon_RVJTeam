package com.u_binusportal;

import android.net.Uri;

import java.util.UUID;

public class Umkm {
    private String umkmId;
    private String umkmName;
    private String umkmDescription;
    private String[] umkmCategory;
    private String umkmAddress;
    private Uri umkmImage;

    public Uri getUmkmImage() {
        return umkmImage;
    }

    public void setUmkmImage(Uri umkmImage) {
        this.umkmImage = umkmImage;
    }

    public Umkm(String umkmName, String umkmDescription, String[] umkmCategory, String umkmAddress, Uri umkmImage) {
        this.umkmId = UUID.randomUUID().toString();
        this.umkmName = umkmName;
        this.umkmDescription = umkmDescription;
        this.umkmCategory = umkmCategory;
        this.umkmAddress = umkmAddress;
        this.umkmImage = umkmImage;
    }

    public String getUmkmId() {
        return umkmId;
    }

    public void setUmkmId(String umkmId) {
        this.umkmId = umkmId;
    }

    public String getUmkmName() {
        return umkmName;
    }

    public void setUmkmName(String umkmName) {
        this.umkmName = umkmName;
    }

    public String getUmkmDescription() {
        return umkmDescription;
    }

    public void setUmkmDescription(String umkmDescription) {
        this.umkmDescription = umkmDescription;
    }

    public String[] getUmkmCategory() {
        return umkmCategory;
    }

    public void setUmkmCategory(String[] umkmCategory) {
        this.umkmCategory = umkmCategory;
    }

    public String getUmkmAddress() {
        return umkmAddress;
    }

    public void setUmkmAddress(String umkmAddress) {
        this.umkmAddress = umkmAddress;
    }
}
