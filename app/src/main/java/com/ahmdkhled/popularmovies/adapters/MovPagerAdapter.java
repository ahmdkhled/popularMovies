package com.ahmdkhled.popularmovies.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ahmdkhled.popularmovies.fragments.FavouriteMovFragment;
import com.ahmdkhled.popularmovies.fragments.popularMovFragment;
import com.ahmdkhled.popularmovies.fragments.BaseMovFragment;
import com.ahmdkhled.popularmovies.fragments.RatedMovFragment;

/**
 * Created by Ahmed Khaled on 2/16/2018.
 */

public class MovPagerAdapter extends BasePagerAdapter {

    private Fragment[] fragments=new Fragment[]{

            new popularMovFragment(),
            new RatedMovFragment(),
            new FavouriteMovFragment()
    };

    private String[] tabNames=new String[]{
            "popular","topRated","Favourite"
    };

    public MovPagerAdapter(FragmentManager fm) {
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
