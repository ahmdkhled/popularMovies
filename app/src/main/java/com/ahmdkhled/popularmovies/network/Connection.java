package com.ahmdkhled.popularmovies.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ahmed Khaled on 1/26/2018.
 */

public class Connection extends AsyncTask<String,Void,String> {

    private OnDataLoading onDataLoading;

    public Connection(OnDataLoading onDataLoading) {
        this.onDataLoading = onDataLoading;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL(strings[0]);
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String result=bufferedReader.readLine();
            //Log.d("tag",strings[0]);
            return result;
        } catch (Exception e) {
            onDataLoading.onFailedToLoad();
            Log.d("ERRORR","error: "+e.getMessage());
            return null;

        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onDataLoading.onDataResolved(s);
    }



    public interface OnDataLoading {
        void onDataResolved(String result);
        void onFailedToLoad();
    }
}
