package com.karloleary.tourguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.karloleary.multiscreen.R;

public class FeaturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourguide_features);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        int feature_type = 0;

        if (getIntent().getIntExtra("FEATURE_TYPE", -1) > -1)
            feature_type = getIntent().getIntExtra("FEATURE_TYPE", 0);

        else if (prefs.getInt("FEATURE_TYPE", -1) > -1)
            feature_type = prefs.getInt("FEATURE_TYPE", 0);

        TabLayout.Tab tab = tabLayout.getTabAt(feature_type);
        tab.select();
    }
}
