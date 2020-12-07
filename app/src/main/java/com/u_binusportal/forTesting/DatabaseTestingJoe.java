package com.u_binusportal.forTesting;

import android.net.Uri;

import com.u_binusportal.R;
import com.u_binusportal.component.Umkm;

import java.util.ArrayList;
import java.util.Arrays;
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

        List<String> aa = Arrays.asList(catMakanan);
        List<String> bb = Arrays.asList(catPakaian);
        List<String> cc = Arrays.asList(catHobby);

        Umkm a = new Umkm("Kentucky Fried Chicken", "Ini adalah restoran KFC",
                aa, "Jl. Lebak Bulus Selatan no. 21", img, imr, "halo");

        Umkm b = new Umkm("Mc Donalds", "Ini adalah restoran Mac Donalds",
                aa, "Jl. Sunter Agung no. 12", img, imr, "halo");

        Umkm c = new Umkm("HnM", "Ini adalah butik baju HnM",
                bb, "Jl. Kelapa Gading 19 no. 7B", img, imr, "halo");

        Umkm d = new Umkm("Gucci", "Ini adalah butik baju Gucci",
                bb, "Jl.Venesia Raya No. 88", img, imr, "halo");

        Umkm e = new Umkm("Pet Store Jaya Baru", "Ini adalah Pet Store Jaya Baru",
                cc, "Jl. Kemayoran Timur no. 200", img, imr, "halo");

        Umkm f = new Umkm("Matahari", "Ini adalah butik baju Matahari",
                cc, "Jl. Pengangsaan Timur no. 72", img, imr, "halo");

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
