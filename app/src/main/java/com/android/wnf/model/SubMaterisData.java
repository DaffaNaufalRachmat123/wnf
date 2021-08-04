package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SubMaterisData implements Parcelable {
    private int id;
    private String title;
    private String materi;
    private int imageResource;
    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public String getMateri(){ return materi; }
    public int getImageResource(){ return imageResource; }
    public SubMaterisData(int id , String title , String materi , int imageResource){
        this.id = id;
        this.title = title;
        this.materi = materi;
        this.imageResource = imageResource;
    }
    public SubMaterisData(Parcel source){
        id = source.readInt();
        title = source.readString();
        materi = source.readString();
        imageResource = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.materi);
        dest.writeInt(this.imageResource);
    }
    public static final Creator<SubMaterisData> CREATOR = new Creator<SubMaterisData>() {
        @Override
        public SubMaterisData createFromParcel(Parcel source) {
            return new SubMaterisData(source);
        }

        @Override
        public SubMaterisData[] newArray(int size) {
            return new SubMaterisData[size];
        }
    };
}
