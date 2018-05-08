package com.ahmdkhled.popularmovies.network;

import com.ahmdkhled.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 1/26/2018.
 */

public class MoviesParser {

    public static ArrayList<Movie> parse(String jsonString){
        ArrayList<Movie> moviesList=new ArrayList<>();

        try {
            JSONObject mainObj=new JSONObject(jsonString);
            JSONArray resultsArr=mainObj.getJSONArray("results");
            for (int i = 0; i < resultsArr.length(); i++) {
                JSONObject currentObj=resultsArr.getJSONObject(i);
                int id =currentObj.getInt("id");
                String originalTitle=currentObj.optString("original_title");
                String overview=currentObj.optString("overview");
                String releaseDate=currentObj.optString("release_date");
                String poster=currentObj.optString("poster_path");
                double vote=currentObj.optInt("vote_average");

                String posterPath=Urls.getImagePath(poster);

                Movie movie=new Movie(id,originalTitle,overview,releaseDate,posterPath,vote);
                moviesList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return moviesList;
    }
}
