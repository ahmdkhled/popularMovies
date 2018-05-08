package com.ahmdkhled.popularmovies.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.adapters.ReviewsAdapter;
import com.ahmdkhled.popularmovies.model.Movie;
import com.ahmdkhled.popularmovies.model.Review;
import com.ahmdkhled.popularmovies.network.Connection;
import com.ahmdkhled.popularmovies.network.Network;
import com.ahmdkhled.popularmovies.network.ReviewsParser;
import com.ahmdkhled.popularmovies.network.Urls;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/18/2018.
 */

public class ReviewsFragment extends Fragment implements Connection.OnDataLoading{

    RecyclerView recyclerView;
    Movie movie;
    String url;
    ArrayList<Review> reviews;
    public static final String arr_key ="ARR_KEY";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.reviews_fragment,container,false);
        recyclerView=view.findViewById(R.id.reviews_recycler);

        if ((getActivity()) != null) {
             movie=((DetailActivity)getActivity()).movie;
        }
        if (movie!=null){
            url=Urls.getReviewsUrl(movie.getId());
        }

        if (savedInstanceState!=null){
            reviews=savedInstanceState.getParcelableArrayList(arr_key);
            showList(reviews);
        }else{
            connect(url);
        }



        return view;
    }

    void connect(String url){
        if (Network.isAvailable(getContext())){
        Connection connection=new Connection(this);
        connection.execute(url);
        }else{
            Toast.makeText(getContext(),"check network ",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDataResolved(String result) {
        reviews= ReviewsParser.parse(result);
        showList(reviews);
    }

    public void showList(ArrayList<Review> reviews){
        ReviewsAdapter reviewsAdapter=new ReviewsAdapter(getContext(),reviews);
        recyclerView.setAdapter(reviewsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onFailedToLoad() {
        Toast.makeText(getContext(),"failed to load data ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(arr_key,reviews);
    }
}
