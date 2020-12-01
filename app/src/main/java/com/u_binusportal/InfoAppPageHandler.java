package com.u_binusportal;

import android.os.Bundle;

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

    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_app);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageOne());
        list.add(new PageTwo());
        list.add(new PageThree());

        pager = findViewById(R.id.vw_page);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);

        pager.setAdapter(pagerAdapter);
    }

}
