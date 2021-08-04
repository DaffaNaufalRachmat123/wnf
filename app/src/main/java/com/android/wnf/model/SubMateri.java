package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SubMateri implements Parcelable {
    private int id;
    private String titleMateri;
    private String materi = "";
    private int imageResource;
    private List<SubMateriData> subMateriDataList = new ArrayList<>();
    public SubMateri(int id , String titleMateri , int imageResource, List<SubMateriData> subMateriDataList){
        this.id = id;
        this.titleMateri = titleMateri;
        this.imageResource = imageResource;
        this.subMateriDataList = subMateriDataList;
    }
    public SubMateri(int id , String titleMateri , String materi){
        this.id = id;
        this.titleMateri = titleMateri;
        this.materi = materi;
    }
    public SubMateri(Parcel parcel){
        id = parcel.readInt();
        titleMateri = parcel.readString();
        materi = parcel.readString();
        imageResource = parcel.readInt();
        parcel.readList(this.subMateriDataList , SubMateriData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.titleMateri);
        dest.writeString(this.materi);
        dest.writeInt(this.imageResource);
        dest.writeList(this.subMateriDataList);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Materi createFromParcel(Parcel source) {
            return new Materi(source);
        }

        public Materi[] newArray(int size){
            return new Materi[size];
        }
    };

    public int getId() { return id; }
    public String getTitleMateri() { return titleMateri; }
    public String getMateri() { return materi; }
    public int getImageResource(){ return imageResource; }
    public List<SubMateriData> getSubMateriDataList(){ return subMateriDataList; }
}
