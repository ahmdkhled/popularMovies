package com.ahmdkhled.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ahmed Khaled on 2/21/2018.
 */

public class MoviesDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="mov.db";

    private static final String CREATE_TABLE="create table "+MoviesContract.MOVIES +"("
            + MoviesContract.ID +" int PRIMARY KEY ,"
            + MoviesContract.NAME +" varchar(20) ,"
            + MoviesContract.POSTER_PATH +" varchar(50),"
            + MoviesContract.OVERVIEW + " varchar(80),"
            + MoviesContract.RELESE_DATE +" varchar(10),"
            + MoviesContract.VOTE +" real)";


    MoviesDb(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
