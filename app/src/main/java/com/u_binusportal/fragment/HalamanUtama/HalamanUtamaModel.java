package com.u_binusportal.fragment.HalamanUtama;

import android.content.Context;
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

public class HalamanUtamaModel {

    protected String title;
    protected String description;
    protected int imageResources;

    public HalamanUtamaModel(String t, String d, int i) {
        this.title = t;
        this.imageResources = i;
        this.description = d;
    }

    protected String getTitle () {
        return this.title;
    }

    protected String getDesc() {
        return this.description;
    }

    protected void setImageR (int i) {
        this.imageResources = i;
    }

    protected void setTitle (String t) {
        this.title = t;
    }

    protected void setDesc(String d) {
        this.description = d;
    }

    protected int getImageR () {
        return this.imageResources;
    }

}
