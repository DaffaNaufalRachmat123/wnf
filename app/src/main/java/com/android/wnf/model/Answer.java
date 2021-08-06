package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private int id;
    private String answer;
    private int isChecked;
    private int isCorrect;
    private int result;
    private int isClickable;
    public Answer(int id , String answer , int isChecked , int isCorrect , int result , int isClickable){
        this.id = id;
        this.answer = answer;
        this.isChecked = isChecked;
        this.isCorrect = isCorrect;
        this.result = result;
        this.isClickable = isClickable;
    }
    public int getId(){ return id; }
    public String getAnswer(){ return answer; }
    public int isChecked(){ return isChecked; }
    public int isCorrect(){ return isCorrect; }
    public int isClickable(){ return isClickable; }
    public int getResult(){ return result; }
    public void setClickable(int isClickable){
        this.isClickable = isClickable;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setIsCorrect(int isCorrect){
        this.isChecked = isCorrect;
    }
    public void setChecked(int isChecked){
        this.isChecked = isChecked;
    }
    public void setResult(int result){this.result = result; }
    public Answer(Parcel in){
        id = in.readInt();
        answer = in.readString();
        isChecked = in.readInt();
        isCorrect = in.readInt();
        result = in.readInt();
        isClickable = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.answer);
        dest.writeInt(this.isChecked);
        dest.writeInt(this.isCorrect);
        dest.writeInt(this.result);
        dest.writeInt(this.isClickable);
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
