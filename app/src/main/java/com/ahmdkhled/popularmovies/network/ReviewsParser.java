package com.ahmdkhled.popularmovies.network;

import android.util.Log;

import com.ahmdkhled.popularmovies.model.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/18/2018.
 */

public class ReviewsParser {

    public static ArrayList<Review> parse(String jsonstring){
            ArrayList<Review> reviews=new ArrayList<>();
        try {
            JSONObject mainObj=new JSONObject(jsonstring);
            JSONArray results=mainObj.optJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject currentObj=results.getJSONObject(i);
                String author=currentObj.optString("author");
                String content=currentObj.optString("content");

                reviews.add(new Review(author,content));
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("ERRORR",e.getMessage());
        }
        return reviews;
    }
}
