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
import com.ahmdkhled.popularmovies.adapters.TraillersAdapter;
import com.ahmdkhled.popularmovies.model.Movie;
import com.ahmdkhled.popularmovies.model.Trailer;
import com.ahmdkhled.popularmovies.network.Connection;
import com.ahmdkhled.popularmovies.network.Network;
import com.ahmdkhled.popularmovies.network.TrailersParser;
import com.ahmdkhled.popularmovies.network.Urls;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/21/2018.
 */

public class TrailersFragment extends Fragment implements Connection.OnDataLoading{

    private static final String ARR_KEY = "arr_key";
    RecyclerView recyclerView;
    ArrayList<Trailer> trailers;
    Movie movie;
    String url;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.traillers_fragment,container,false);
        recyclerView=view.findViewById(R.id.traillers_recycler);

        if ((getActivity()) != null) {
            movie=((DetailActivity)getActivity()).movie;
        }
        if (movie!=null){
            url=Urls.getTrailersUrl(movie.getId());
        }

        if (savedInstanceState!=null){
            trailers=savedInstanceState.getParcelableArrayList(ARR_KEY);
            showList(trailers);
        }else {
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
        trailers= TrailersParser.parse(result);
        showList(trailers);
    }

    public void showList(ArrayList<Trailer> trailers){
        TraillersAdapter traillersAdapter=new TraillersAdapter(getContext(),trailers);
        recyclerView.setAdapter(traillersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onFailedToLoad() {
        Toast.makeText(getContext(),"failed to load data ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARR_KEY,trailers);
    }
}
