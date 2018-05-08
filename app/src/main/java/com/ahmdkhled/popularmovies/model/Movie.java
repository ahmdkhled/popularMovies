package com.ahmdkhled.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Khaled on 1/26/2018.
 */

public class Movie implements Parcelable {
    private int id;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private double vote;

    public Movie(int id,String originalTitle, String overview, String releaseDate, String posterPath, double vote) {
        this.id=id;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
        this.vote = vote;
    }

    private Movie(Parcel parcel) {
        id=parcel.readInt();
        originalTitle=parcel.readString();
        overview=parcel.readString();
        releaseDate=parcel.readString();
        posterPath=parcel.readString();
        vote=parcel.readDouble();
    }
    public Movie(){}

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVote() {
        return vote;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(posterPath);
        parcel.writeDouble(vote);
    }

    public static final Parcelable.Creator<Movie> CREATOR=new Parcelable.Creator<Movie>(){


        @Override
        public Movie createFromParcel(Parcel parcel) {

            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {return new Movie[i];
        }
    };


}
