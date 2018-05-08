package com.ahmdkhled.popularmovies.network;

import com.ahmdkhled.popularmovies.model.Trailer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/21/2018.
 */

public class TrailersParser {

    public static ArrayList<Trailer> parse(String jsonString){
        ArrayList<Trailer> trailers=new ArrayList<>();
        try {
            JSONObject mainObj=new JSONObject(jsonString);
            JSONArray results=mainObj.optJSONArray("results");
            for (int i = 0; i <results.length() ; i++) {
                JSONObject currentObj=results.getJSONObject(i);
                String key=currentObj.optString("key");
                String url=Urls.getYoutubeUrl(key);
                String name=currentObj.optString("name");
                String size=currentObj.optString("size");
                trailers.add(new Trailer(url,name,size));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return trailers;
    }
}
