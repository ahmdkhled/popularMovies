package com.ahmdkhled.popularmovies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.network.Urls;

/**
 * Created by Ahmed Khaled on 2/16/2018.
 */

public class RatedMovFragment extends BaseMovFragment {

    @Override
    String getUrl() {
        return Urls.getTopRatedUrl();
    }
}
