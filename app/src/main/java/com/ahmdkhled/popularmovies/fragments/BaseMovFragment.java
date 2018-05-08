package com.ahmdkhled.popularmovies.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.adapters.MovieAdapter;
import com.ahmdkhled.popularmovies.model.Movie;
import com.ahmdkhled.popularmovies.network.Connection;
import com.ahmdkhled.popularmovies.network.MoviesParser;
import com.ahmdkhled.popularmovies.network.Network;

import java.util.ArrayList;


/**
 * Created by Ahmed Khaled on 2/16/2018.
 */

public abstract class BaseMovFragment extends Fragment implements MovieAdapter.OnRecyclerItemClickListener,Connection.OnDataLoading{

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Snackbar snackbar;
    ArrayList<Movie> movieList;

    private static final String ARR_KEY = "movie_array";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movies_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progress_bar);

        if (savedInstanceState!=null){
            movieList=savedInstanceState.getParcelableArrayList(ARR_KEY);
            progressBar.setVisibility(View.INVISIBLE);
            showGrid(movieList);
        }else{
        connect(getUrl());
        }
        Log.d("tag","onCreateView");
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARR_KEY,movieList);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tag","onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag","onDestroy");

    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent intent=new Intent(getContext(),DetailActivity.class);
        intent.putExtra("pos",position);
        intent.putExtra("movie",movieList.get(position));
        startActivity(intent);
    }

    void connect(String apiUrl){
        Log.d("tag","connection");
        if (Network.isAvailable(getContext())){
            Connection connection=new Connection(this);
            connection.execute(apiUrl);
        }else {showSnackBar("there is no connection");}
    }

    @Override
    public void onDataResolved(String result) {
        if(result!=null){
            //Log.d("tag",result);
            movieList= MoviesParser.parse(result);
            showGrid(movieList);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onFailedToLoad() {
        showSnackBar("error while fetching data");

    }

    void showSnackBar(String message){
        snackbar= Snackbar.make(getActivity().findViewById(R.id.main_activity),message,Snackbar.LENGTH_INDEFINITE)
                .setAction("retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            connect(getUrl());
                    }
                });
        snackbar.show();
        progressBar.setVisibility(View.INVISIBLE);
    }


    void showGrid(ArrayList<Movie> movieList){
        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movieList, this);
        recyclerView.setAdapter(movieAdapter);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }
        else{
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        }
    }


    abstract String getUrl();


}
