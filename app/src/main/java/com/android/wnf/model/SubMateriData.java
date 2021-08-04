package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SubMateriData implements Parcelable {
    private int id;
    private String title;
    private String materi;
    private int imageResource;
    public SubMateriData(int id , String title , String materi , int imageResource){
        this.id = id;
        this.title = title;
        this.materi = materi;
        this.imageResource = imageResource;
    }

    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public String getMateri(){ return materi; }
    public int getImageResource(){ return imageResource; }
    public SubMateriData(Parcel parcel){
        id = parcel.readInt();
        title = parcel.readString();
        materi = parcel.readString();
        imageResource = parcel.readInt();
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
    public static final Parcelable.Creator<SubMateriData> CREATOR = new Creator<SubMateriData>() {
        @Override
        public SubMateriData createFromParcel(Parcel source) {
            return new SubMateriData(source);
        }

        @Override
        public SubMateriData[] newArray(int size) {
            return new SubMateriData[size];
        }
    };
}
