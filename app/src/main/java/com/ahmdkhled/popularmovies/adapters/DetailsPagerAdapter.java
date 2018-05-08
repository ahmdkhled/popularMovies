package com.ahmdkhled.popularmovies.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ahmdkhled.popularmovies.fragments.DetailsFragment;
import com.ahmdkhled.popularmovies.fragments.ReviewsFragment;
import com.ahmdkhled.popularmovies.fragments.TrailersFragment;

/**
 * Created by Ahmed Khaled on 2/17/2018.
 */

public class DetailsPagerAdapter extends BasePagerAdapter {

    private Fragment[] fragments=new Fragment[]{
            new DetailsFragment(),
            new TrailersFragment(),
            new ReviewsFragment()};

    private String[] tabNames=new String[]{
      "details","trailers","reviews"
    };

    public DetailsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    Fragment[] getFragments() {
        return fragments;
    }

    @Override
    String[] getTabNames() {
        return tabNames;
    }
}
