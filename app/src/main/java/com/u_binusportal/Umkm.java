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

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Uri getUmkmImage() {
        return umkmImage;
    }

    public void setUmkmImage(Uri umkmImage) {
        this.umkmImage = umkmImage;
    }


    public Umkm(String userId, String umkmName, String umkmDescription, String[] umkmCategory, String umkmAddress, Uri umkmImage) {
        this.umkmId = UUID.randomUUID().toString();
        this.umkmName = umkmName;
        this.umkmDescription = umkmDescription;
        this.umkmCategory = umkmCategory;
        this.umkmAddress = umkmAddress;
        this.umkmImage = umkmImage;
        this.userId = userId;
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

    public int getImageR() {
        return imageR;
    }

    public void setImageR(int i) {
        this.imageR = i;
    }

    public UMKMListItemForLV convertToBeShownInLV() {
        String category = "Kategori: " + traverse(umkmCategory);
        UMKMListItemForLV item = new UMKMListItemForLV(this.umkmName, category, this.umkmImage, this.imageR);
        return item;
    }

    private String traverse(String [] x) {
        String res = "";
        int y = x.length;
        for(int i=0; i<y; i++) {
            res += x[i];
            if(i != y-1) res += ", "; else res += " ";
        }
        return res;
    }
}
