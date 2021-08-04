package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Materi implements Parcelable {
    private int id;
    private String titleMateri;
    private String materi = "";
    private List<SubMateri> subMateriList;
    private int isSubMateri = 0;
    public Materi(int id , String titleMateri , int isSubMateri , List<SubMateri> subMateriList){
        this.id = id;
        this.titleMateri = titleMateri;
        this.isSubMateri = isSubMateri;
        this.subMateriList = subMateriList;
    }
    public Materi(Parcel parcel){
        id = parcel.readInt();
        titleMateri = parcel.readString();
        isSubMateri = parcel.readInt();
        subMateriList = parcel.readArrayList(SubMateri.class.getClassLoader());
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
        dest.writeInt(this.isSubMateri);
        dest.writeList(this.subMateriList);
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
    public int getIsSubMateri(){ return isSubMateri; }
    public List<SubMateri> getSubMateriList(){ return subMateriList; }
}
