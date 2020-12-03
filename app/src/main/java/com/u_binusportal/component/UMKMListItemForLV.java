package com.u_binusportal.component;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

public class UMKMListItemForLV {

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

}
