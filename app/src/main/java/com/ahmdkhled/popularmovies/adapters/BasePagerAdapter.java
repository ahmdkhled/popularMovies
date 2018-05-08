package com.ahmdkhled.popularmovies.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ahmed Khaled on 2/18/2018.
 */

public abstract class BasePagerAdapter extends FragmentPagerAdapter {

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragments()[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return getTabNames()[position];
    }

    @Override
    public int getCount() {
        return getFragments().length;
    }

    abstract Fragment[] getFragments();
    abstract String[] getTabNames();
}
