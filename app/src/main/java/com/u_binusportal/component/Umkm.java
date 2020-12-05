package com.u_binusportal.component;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class Umkm implements Parcelable {
    private String umkmId;
    private String umkmName;
    private String umkmDescription;
    private String[] umkmCategory;
    private String umkmAddress;
    private Uri umkmImage;
    private int imageR;
    private String userID;

    public Uri getUmkmImage() {
        return umkmImage;
    }

    public void setUmkmImage(Uri umkmImage) {
        this.umkmImage = umkmImage;
    }

    public Umkm(String umkmId, String umkmName, String umkmDescription, String[] umkmCategory, String umkmAddress, Uri umkmImage, int imageR, String userID) {
        this.umkmId = umkmId;
        this.umkmName = umkmName;
        this.umkmDescription = umkmDescription;
        this.umkmCategory = umkmCategory;
        this.umkmAddress = umkmAddress;
        this.umkmImage = umkmImage;
        this.imageR = imageR;
        this.userID = userID;
    }

    public Umkm(String umkmName, String umkmDescription, String[] umkmCategory, String umkmAddress, Uri umkmImage, int i, String userID) {
        this.umkmId = UUID.randomUUID().toString();
        this.umkmName = umkmName;
        this.umkmDescription = umkmDescription;
        this.umkmCategory = umkmCategory;
        this.umkmAddress = umkmAddress;
        this.umkmImage = umkmImage;
        this.imageR = i;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.umkmId);
        dest.writeString(this.umkmName);
        dest.writeString(this.umkmDescription);
        dest.writeStringArray(this.umkmCategory);
        dest.writeString(this.umkmAddress);
        dest.writeParcelable(this.umkmImage, flags);
        dest.writeInt(this.imageR);
        dest.writeString(this.userID);
    }

    protected Umkm(Parcel in) {
        this.umkmId = in.readString();
        this.umkmName = in.readString();
        this.umkmDescription = in.readString();
        this.umkmCategory = in.createStringArray();
        this.umkmAddress = in.readString();
        this.umkmImage = in.readParcelable(Uri.class.getClassLoader());
        this.imageR = in.readInt();
        this.userID = in.readString();
    }

    public static final Parcelable.Creator<Umkm> CREATOR = new Parcelable.Creator<Umkm>() {
        @Override
        public Umkm createFromParcel(Parcel source) {
            return new Umkm(source);
        }

        @Override
        public Umkm[] newArray(int size) {
            return new Umkm[size];
        }
    };
}
