package com.ahmdkhled.popularmovies.network;

import com.ahmdkhled.popularmovies.BuildConfig;

/**
 * Created by Ahmed Khaled on 2/16/2018.
 */

public class Urls {

    private final static String apiKey = BuildConfig.API_KEY;

    public static  String getPopularUrl(){
        return "http://api.themoviedb.org/3/movie/popular?api_key=" +apiKey;
    }

    public static String getTopRatedUrl(){
        return "https://api.themoviedb.org/3/movie/top_rated?api_key="+apiKey;
    }

    public static String getTrailersUrl(int movieId){
        return "https://api.themoviedb.org/3/movie/"+movieId+"/videos?api_key=" +apiKey;
    }

    public static String getReviewsUrl(int movieId) {
        return "https://api.themoviedb.org/3/movie/"+movieId+"/reviews?api_key="+apiKey;
    }

     static String getImagePath(String poster){
        return "http://image.tmdb.org/t/p/w185"+poster;
    }

    public static String getYoutubeUrl(String key){
         return "https://www.youtube.com/watch?v="+key;
    }

    }
