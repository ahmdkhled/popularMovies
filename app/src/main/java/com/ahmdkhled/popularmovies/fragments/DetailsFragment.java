package com.ahmdkhled.popularmovies.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.events.MoviesChangeEvent;
import com.ahmdkhled.popularmovies.model.Movie;
import com.ahmdkhled.popularmovies.network.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Ahmed Khaled on 2/17/2018.
 */

public class DetailsFragment extends Fragment {

    TextView name,overview,releaseDate;
    Movie movie;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.details_fragment,container,false);
        name=view.findViewById(R.id.movie_name);
        overview=view.findViewById(R.id.overview);
        releaseDate=view.findViewById(R.id.release_date);

        if ((getActivity()) != null) {
            movie=((DetailActivity)getActivity()).movie;
        }

        name.setText(movie.getOriginalTitle());
        overview.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
        Log.d("URLL", Urls.getReviewsUrl(movie.getId()));



        return view ;
    }






}
