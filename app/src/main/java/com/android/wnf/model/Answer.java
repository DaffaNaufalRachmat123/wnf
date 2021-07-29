package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private int id;
    private String answer;
    private boolean isChecked;
    public Answer(int id , String answer , boolean isChecked){
        this.id = id;
        this.answer = answer;
        this.isChecked = isChecked;
    }
    public int getId(){ return id; }
    public String getAnswer(){ return answer; }
    public boolean isChecked(){ return isChecked; }
    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
    public Answer(Parcel in){
        id = in.readInt();
        answer = in.readString();
        isChecked = in.readBoolean();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.answer);
        dest.writeBoolean(this.isChecked);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel source) {
            return new Answer(source);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
