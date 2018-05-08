package com.ahmdkhled.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Khaled on 2/18/2018.
 */

public class Review implements Parcelable{
    private String author;
    private String content;

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }

    private Review(Parcel parcel) {
        author=parcel.readString();
        content=parcel.readString();
    }
    public Review(){};

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(content);
    }



    Creator<Review> CREATOT=new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        @Override
        public Review[] newArray(int i) {
            return new Review[i];
        }
    };
}
