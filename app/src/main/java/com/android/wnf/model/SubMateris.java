package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SubMateris implements Parcelable {
    private int id;
    private String title;
    private int imageResource;
    private List<SubMaterisData> subMaterisDataList;
    public SubMateris(int id , String title , int imageResource , List<SubMaterisData> subMaterisDataList){
        this.id = id;
        this.title = title;
        this.imageResource = imageResource;
        this.subMaterisDataList = subMaterisDataList;
    }
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public int getImageResource(){return imageResource; }
    public List<SubMaterisData> getSubMaterisDataList(){ return subMaterisDataList; }
    public SubMateris(Parcel source){
        id = source.readInt();
        title = source.readString();
        imageResource = source.readInt();
        subMaterisDataList = source.readArrayList(SubMaterisData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.imageResource);
        dest.writeList(this.subMaterisDataList);
    }
    public static final Parcelable.Creator<SubMateris> CREATOR = new Creator<SubMateris>() {
        @Override
        public SubMateris createFromParcel(Parcel source) {
            return new SubMateris(source);
        }

        @Override
        public SubMateris[] newArray(int size) {
            return new SubMateris[size];
        }
    };
}
