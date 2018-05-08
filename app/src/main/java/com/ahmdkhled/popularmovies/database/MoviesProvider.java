package com.ahmdkhled.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Ahmed Khaled on 2/22/2018.
 */

public class MoviesProvider extends ContentProvider{

    private SQLiteDatabase database;



    public static final int MOVIES =1;

    private static UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);

    static{

        uriMatcher.addURI(MoviesContract.AUTHORITY, MoviesContract.MOVIES_PATH, MOVIES);
    }

    @Override
    public boolean onCreate() {
        MoviesDb moviesDb=new MoviesDb(getContext());
        database=moviesDb.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor=null;
        switch (uriMatcher.match(uri)){
            case MOVIES:
                cursor=database.query(MoviesContract.MOVIES,projection,selection,selectionArgs,null,null,sortOrder);

        }

        return cursor;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case MOVIES:return MoviesContract.MOVIES_MIME;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long id=database.insert(MoviesContract.MOVIES,null,contentValues);
        if (id>0){
            return ContentUris.withAppendedId(MoviesContract.MOVIES_URI,id);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return database.delete(MoviesContract.MOVIES,selection,selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        return database.update(MoviesContract.MOVIES,contentValues,selection,selectionArgs);
    }

}
