package com.ahmdkhled.popularmovies.fragments;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.adapters.MovieAdapter;
import com.ahmdkhled.popularmovies.database.MoviesContract;
import com.ahmdkhled.popularmovies.events.MoviesChangeEvent;
import com.ahmdkhled.popularmovies.model.Movie;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/16/2018.
 */

public class FavouriteMovFragment extends popularMovFragment implements MovieAdapter.OnRecyclerItemClickListener{

    ArrayList<Movie> movies;
    private final String ARR_KEY="arr_key";
    EventBus bus=EventBus.getDefault();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.movies_fragment,container,false);

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progress_bar);

        if (savedInstanceState==null){
        movies=queryMovies();
        if (movies!=null){
            showGrid(movies);
        }
        }else {
            movies=savedInstanceState.getParcelableArrayList(ARR_KEY);
            showGrid(movies);
        }

        return view;
    }

    ArrayList<Movie> queryMovies(){
        ArrayList<Movie> movies=new ArrayList<>();
        ContentResolver resolver=getContext().getContentResolver();
        if (resolver!=null){
            Cursor cursor=resolver.query(MoviesContract.MOVIES_URI,null,null,null,null);
            if (cursor!=null&&cursor.getCount()>0){
                while (cursor.moveToNext()){
                    int id=cursor.getInt(cursor.getColumnIndex(MoviesContract.ID));
                    String name=cursor.getString(cursor.getColumnIndex(MoviesContract.NAME));
                    String overview=cursor.getString(cursor.getColumnIndex(MoviesContract.OVERVIEW));
                    String releaseDate=cursor.getString(cursor.getColumnIndex(MoviesContract.RELESE_DATE));
                    String posterPath=cursor.getString(cursor.getColumnIndex(MoviesContract.POSTER_PATH));
                    double vote=cursor.getDouble(cursor.getColumnIndex(MoviesContract.POSTER_PATH));
                    Movie movie=new Movie(id,name,overview,releaseDate,posterPath,vote);
                    movies.add(movie);
                }
                cursor.close();
            }

        }
        return movies;
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
        progressBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void OnRecyclerItemClick(int position) {
        Intent intent=new Intent(getContext(),DetailActivity.class);
        intent.putExtra("pos",position);
        intent.putExtra("movie",movies.get(position));
        startActivity(intent);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(ARR_KEY,movies);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MoviesChangeEvent moviesChangeEvent){
        movies=queryMovies();
        showGrid(movies);
    }



    @Override
    public void onStart() {
        super.onStart();
        if (!bus.isRegistered(this)){
            bus.register(this);
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
