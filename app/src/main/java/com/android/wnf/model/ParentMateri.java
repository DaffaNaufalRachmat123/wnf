package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ParentMateri implements Parcelable {
    private int id;
    private String title;
    private List<Materi> materiList = new ArrayList<>();
    public ParentMateri (int id , String title , List<Materi> materiList) {
        this.id = id;
        this.title = title;
        this.materiList = materiList;
    }
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public List<Materi> getMateriList(){ return materiList; }
    public ParentMateri(Parcel parcel){
        id = parcel.readInt();
        title = parcel.readString();
        parcel.readList(this.materiList , Materi.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeList(this.materiList);
    }
    public static final Parcelable.Creator<ParentMateri> CREATOR = new Creator<ParentMateri>() {
        @Override
        public ParentMateri createFromParcel(Parcel source) {
            return new ParentMateri(source);
        }

        @Override
        public ParentMateri[] newArray(int size) {
            return new ParentMateri[size];
        }
    };
}
