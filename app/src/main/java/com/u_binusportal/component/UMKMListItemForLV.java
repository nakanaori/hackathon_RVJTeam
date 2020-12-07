package com.u_binusportal.component;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.u_binusportal.R;

import java.util.Objects;

public class UMKMListItemForLV implements Parcelable {

    protected String title;
    protected String description;
    protected Uri uri_images;
    protected int imgR;

    public UMKMListItemForLV(String t, String d, Uri u, int i) {
        this.title = t;
        this.uri_images = u;
        this.description = d;
        this.imgR = i;
    }

    public UMKMListItemForLV(String t, String d, int i) {
        this.title = t;
        this.description = d;
        this.imgR = i;
    }

    public String getTitle () {
        return this.title;
    }

    public String getDesc() {
        return this.description;
    }

    protected void setImageURI (Uri i) {
        this.uri_images = i;
    }

    protected void setTitle (String t) {
        this.title = t;
    }

    protected void setDesc(String d) {
        this.description = d;
    }

    public Uri getImageURI () {
        return this.uri_images;
    }

    public int getImageR () {
        return this.imgR;
    }

    protected void setImageR (int i) {
        this.imgR = i;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.uri_images, flags);
        dest.writeInt(this.imgR);
    }

    protected UMKMListItemForLV(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.uri_images = in.readParcelable(Uri.class.getClassLoader());
        this.imgR = in.readInt();
    }

    public static final Parcelable.Creator<UMKMListItemForLV> CREATOR = new Parcelable.Creator<UMKMListItemForLV>() {
        @Override
        public UMKMListItemForLV createFromParcel(Parcel source) {
            return new UMKMListItemForLV(source);
        }

        @Override
        public UMKMListItemForLV[] newArray(int size) {
            return new UMKMListItemForLV[size];
        }
    };
}
