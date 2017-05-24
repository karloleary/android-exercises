package com.karloleary.tourguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class FragmentsAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Restaurants", "Events"};

    // ****************************************************

    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new RestaurantsFragment();
        else if (position == 1)
            return new EventsFragment();
        else
            return new EventsFragment();
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
