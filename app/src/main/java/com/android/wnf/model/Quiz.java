package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Quiz implements Parcelable {
    private int id;
    private String question;
    private int soundResource;
    private List<Answer> answerList;
    public Quiz(int id , String question , int soundResource , List<Answer> answerList){
        this.id = id;
        this.question = question;
        this.soundResource = soundResource;
        this.answerList = answerList;
    }
    public int getId(){return id; }
    public String getQuestion(){ return question; }
    public int getSoundResource(){ return soundResource; }
    public List<Answer> getAnswerList(){ return answerList; }
    public Quiz(Parcel parcel){
        id = parcel.readInt();
        question = parcel.readString();
        soundResource = parcel.readInt();
        answerList = parcel.readArrayList(Answer.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.question);
        dest.writeInt(this.soundResource);
        dest.writeList(this.answerList);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel source) {
            return new Quiz(source);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}
