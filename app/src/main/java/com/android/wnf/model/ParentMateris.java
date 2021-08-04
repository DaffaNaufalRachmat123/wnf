package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ParentMateris implements Parcelable {
    private int id;
    private String title;
    private List<Materis> materisList;
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public List<Materis> getMaterisList(){ return materisList; }
    public ParentMateris(int id , String title , List<Materis> materisList){
        this.id = id;
        this.title = title;
        this.materisList = materisList;
    }
    public ParentMateris(Parcel source){
        id = source.readInt();
        title = source.readString();
        materisList = source.readArrayList(Materis.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeList(this.materisList);
    }
    public static final Parcelable.Creator<ParentMateris> CREATOR = new Creator<ParentMateris>() {
        @Override
        public ParentMateris createFromParcel(Parcel source) {
            return new ParentMateris(source);
        }

        @Override
        public ParentMateris[] newArray(int size) {
            return new ParentMateris[size];
        }
    };
}
