package com.ahmdkhled.popularmovies.database;

import android.net.Uri;

/**
 * Created by Ahmed Khaled on 2/25/2018.
 */

public class MoviesContract {

    public static final String AUTHORITY="com.ahmdkhled.popularmovies.MoviesProvider";
    public static final String MOVIES_PATH ="movies";

    public static final Uri MOVIES_URI=Uri.parse("content://"+AUTHORITY+"/"+ MOVIES_PATH);

    public static final String MOVIES_MIME="vnd.android.cursor.dir/vnd.com.ahmdkhled.popularmovies.provider.movies";

    public static final String MOVIES="movies";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String POSTER_PATH ="poster_path";
    public static final String OVERVIEW="overview";
    public static final String RELESE_DATE="release_date";
    public static final String VOTE="vote";


}
