package com.u_binusportal.handlers;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.u_binusportal.R;
import com.u_binusportal.adapter.SlidePagerAdapter;
import com.u_binusportal.activity.pageFragmentsActivities.pageFragment.PageOne;
import com.u_binusportal.activity.pageFragmentsActivities.pageFragment.PageThree;
import com.u_binusportal.activity.pageFragmentsActivities.pageFragment.PageTwo;

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
