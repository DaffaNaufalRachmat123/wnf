package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Materis implements Parcelable {
    private int id;
    private String title;
    private List<SubMateris> subMaterisList;
    private int isSubMateri;
    public Materis(int id , String title , int isSubMateri, List<SubMateris> subMaterisList){
        this.id = id;
        this.title = title;
        this.isSubMateri = isSubMateri;
        this.subMaterisList = subMaterisList;
    }
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public int getIsSubMateri(){ return isSubMateri; }
    public List<SubMateris> getSubMaterisList(){ return subMaterisList; }
    public Materis(Parcel source){
        id = source.readInt();
        title = source.readString();
        isSubMateri = source.readInt();
        subMaterisList = source.readArrayList(SubMateris.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.isSubMateri);
        dest.writeList(this.subMaterisList);
    }
    public static final Creator<Materis> CREATOR = new Creator<Materis>() {
        @Override
        public Materis createFromParcel(Parcel source) {
            return new Materis(source);
        }

        @Override
        public Materis[] newArray(int size) {
            return new Materis[size];
        }
    };
}
