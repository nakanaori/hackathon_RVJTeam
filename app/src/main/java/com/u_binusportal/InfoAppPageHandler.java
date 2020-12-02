package com.u_binusportal;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.u_binusportal.pageFragments.PageOne;
import com.u_binusportal.pageFragments.PageThree;
import com.u_binusportal.pageFragments.PageTwo;

import java.util.ArrayList;
import java.util.List;

public class InfoAppPageHandler extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v("Joevian", "success");
        super.onCreate(savedInstanceState);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageOne());
        list.add(new PageTwo());
        list.add(new PageThree());

        int sz = list.size();
        Log.v("test" + sz, "size");

        setContentView(R.layout.intro_app);
        pager = findViewById(R.id.vw_page);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(),1, list);

        pager.setAdapter(pagerAdapter);

    }

}
