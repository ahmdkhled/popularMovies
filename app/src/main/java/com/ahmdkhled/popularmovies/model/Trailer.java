package com.ahmdkhled.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Khaled on 2/21/2018.
 */

public class Trailer implements Parcelable {

    private String url;
    private String name;
    private String size;

    public Trailer(String url, String name, String size) {
        this.url = url;
        this.name = name;
        this.size = size;
    }

    private Trailer(Parcel parcel) {
        url=parcel.readString();
        name=parcel.readString();
        size=parcel.readString();
    }
    public Trailer(){};

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(name);
        parcel.writeString(size);
    }

    Parcelable.Creator<Trailer> CREATOR =new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel parcel) {
            return new Trailer(parcel);
        }

        @Override
        public Trailer[] newArray(int i) {
            return new Trailer[i];
        }
    };


}
