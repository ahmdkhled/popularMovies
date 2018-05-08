package com.ahmdkhled.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 1/26/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private ArrayList<Movie> moviesList;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public MovieAdapter(Context context, ArrayList<Movie> moviesList,OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.context = context;
        this.moviesList = moviesList;
        this.onRecyclerItemClickListener=onRecyclerItemClickListener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View item=inflater.inflate(R.layout.movie_item,parent,false);
        return new MovieHolder(item);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Picasso.with(context).load(moviesList.get(position).getPosterPath()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        if (moviesList==null){return 0;}
        else{return moviesList.size();}
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView poster;

        MovieHolder(View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.poster_IV);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecyclerItemClickListener.OnRecyclerItemClick(getAdapterPosition());
        }
    }

    public interface OnRecyclerItemClickListener{
        void OnRecyclerItemClick(int position);
    }
}
