package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Materi implements Parcelable {
    private int id;
    private String titleMateri;
    private String materi = "";
    public Materi(int id , String titleMateri){
        this.id = id;
        this.titleMateri = titleMateri;
    }
    public Materi(int id , String titleMateri , String materi){
        this.id = id;
        this.titleMateri = titleMateri;
        this.materi = materi;
    }
    public Materi(Parcel parcel){
        String[] arrays = new String[3];
        parcel.readStringArray(arrays);
        this.id = Integer.parseInt(arrays[0]);
        this.titleMateri = arrays[1];
        this.materi = arrays[2];
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
}
