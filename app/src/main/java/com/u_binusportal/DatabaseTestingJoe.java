package com.u_binusportal;

import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

// ini ceritanya database yg isinya umkm
public class DatabaseTestingJoe {

    private ArrayList<Umkm> dataUMKM;

    public DatabaseTestingJoe() {
        this.dataUMKM = new ArrayList<>();

        String catMakanan[] = {"Makanan"};
        String catPakaian[] = {"Pakaian", "Hobi"};
        String catHobby[] = {"Hobi", "Lingkungan"};
        Uri img = Uri.parse(String.valueOf(R.drawable.applogo));
        int imr = R.drawable.applogo;

        Umkm a = new Umkm("Kentucky Fried Chicken", "Ini adalah restoran KFC",
                catMakanan, "jalan raya", img, imr, "halo");

        Umkm b = new Umkm("Mc Donalds", "Ini adalah restoran Mac Donalds",
                catMakanan, "jalan raya", img, imr, "halo");

        Umkm c = new Umkm("HnM", "Ini adalah butik baju HnM",
                catPakaian, "jalan raya", img, imr, "halo");

        Umkm d = new Umkm("Gucci", "Ini adalah butik baju Gucci",
                catPakaian, "jalan raya", img, imr, "halo");

        Umkm e = new Umkm("Pet Store Jaya Baru", "Ini adalah Pet Store Jaya Baru",
                catHobby, "jalan raya", img, imr, "halo");

        Umkm f = new Umkm("Matahari", "Ini adalah butik baju Matahari",
                catPakaian, "jalan raya", img, imr, "halo");

        this.dataUMKM.add(a);
        this.dataUMKM.add(b);
        this.dataUMKM.add(c);
        this.dataUMKM.add(d);
        this.dataUMKM.add(e);
        this.dataUMKM.add(f);

    }

    public ArrayList<Umkm> getUmkm() {
        return this.dataUMKM;
    }

}
