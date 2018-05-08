package com.ahmdkhled.popularmovies.activities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.adapters.DetailsPagerAdapter;
import com.ahmdkhled.popularmovies.database.MoviesContract;
import com.ahmdkhled.popularmovies.events.MoviesChangeEvent;
import com.ahmdkhled.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ImageView poster;
    ViewPager pager;
    TabLayout tab;
    TextView vote;
    boolean movieExist;
    public FloatingActionButton fab;
    public Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        poster=findViewById(R.id.movie_cover);
        pager=findViewById(R.id.details_pager);
        tab=findViewById(R.id.details_tab);
        vote=findViewById(R.id.vote);
        fab=findViewById(R.id.fab);



        Intent intent=getIntent();
        movie=intent.getParcelableExtra("movie");
        if (movie!=null) {
            Picasso.with(this).load(movie.getPosterPath()).into(poster);
            vote.setText(movie.getVote() + "/10");
        }

        pager.setAdapter(new DetailsPagerAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(pager);

        movieExist=movieExist(movie.getId());

        handleIcon();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movieExist){
                    deleteMovie();
                    movieExist=false;
                    handleIcon();
                    EventBus.getDefault().post(new MoviesChangeEvent());
                }else {
                    insertMovie();
                    movieExist=true;
                    handleIcon();
                    EventBus.getDefault().post(new MoviesChangeEvent());
                }
            }
        });


    }

    void insertMovie(){
        ContentResolver resolver=getContentResolver();
        if (resolver!=null){
            ContentValues contentValues=new ContentValues();

            contentValues.put(MoviesContract.ID,movie.getId());
            contentValues.put(MoviesContract.NAME,movie.getOriginalTitle());
            contentValues.put(MoviesContract.OVERVIEW,movie.getOverview());
            contentValues.put(MoviesContract.POSTER_PATH,movie.getPosterPath());
            contentValues.put(MoviesContract.RELESE_DATE,movie.getReleaseDate());
            contentValues.put(MoviesContract.VOTE,movie.getVote());
            resolver.insert(MoviesContract.MOVIES_URI,contentValues);

            Toast.makeText(getApplicationContext(),"movie was marked ",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"error while inserting ",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteMovie(){
        ContentResolver resolver=getContentResolver();
        if (resolver!=null){
            resolver.delete(MoviesContract.MOVIES_URI,MoviesContract.ID+"=?"
                    ,new String[]{String.valueOf( movie.getId())});
            Toast.makeText(getApplicationContext(),"movie was deleted ",Toast.LENGTH_SHORT).show();

        }
    }

    boolean movieExist(int targetId){
        ContentResolver resolver=getContentResolver();
        if (resolver!=null){
            Cursor cursor=resolver.query(MoviesContract.MOVIES_URI,new String[]{MoviesContract.ID},null,null,null);
            if (cursor!=null&&cursor.getCount()>0){
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(MoviesContract.ID));
                    if (id == targetId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void handleIcon(){
        if (movieExist){
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_black_24dp));
        }else{
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_border_black_24dp));
        }
    }


}
