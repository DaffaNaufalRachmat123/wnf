package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Quiz implements Parcelable {
    private int id;
    private String question;
    private int soundResource;
    private ArrayList<Answer> answerList;
    private int score_points = 0;
    private int isSuccess = 0;
    private int isAnswer = 0;
    private int isResult = -1;
    private int lastChoosePosition;
    public Quiz(int id , String question , int soundResource , ArrayList<Answer> answerList , int isResult , int score_points , int isSuccess , int isAnswer , int lastChoosePosition){
        this.id = id;
        this.question = question;
        this.soundResource = soundResource;
        this.answerList = answerList;
        this.isResult = isResult;
        this.score_points = score_points;
        this.isSuccess = isSuccess;
        this.isAnswer = isAnswer;
        this.lastChoosePosition = lastChoosePosition;
    }
    public int getId(){return id; }
    public String getQuestion(){ return question; }
    public int getSoundResource(){ return soundResource; }
    public int getScorePoints(){ return score_points; }
    public int isSuccess(){ return isSuccess; }
    public int getIsResult(){ return isResult; }
    public int getIsAnswer(){ return isAnswer; }
    public int getLastChoosePosition(){ return lastChoosePosition; }
    public ArrayList<Answer> getAnswerList(){ return answerList; }
    public void setSoundResource(int soundResource){
        this.soundResource = soundResource;
    }
    public void setAnswerList(ArrayList<Answer> answerList){
        this.answerList = answerList;
    }
    public void setLastChoosePosition(int lastChoosePosition){
        this.lastChoosePosition = lastChoosePosition;
    }

    public void setAnswer(int isAnswer){
        this.isAnswer = isAnswer;
    }

    public void setIsResult(int isResult){
        this.isResult = isResult;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", soundResource=" + soundResource +
                ", answerList=" + answerList +
                '}';
    }

    public Quiz(Parcel parcel){
        id = parcel.readInt();
        question = parcel.readString();
        soundResource = parcel.readInt();
        answerList = parcel.readArrayList(Answer.class.getClassLoader());
        isResult = parcel.readInt();
        score_points = parcel.readInt();
        isSuccess = parcel.readInt();
        isAnswer = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.question);
        dest.writeInt(this.soundResource);
        dest.writeList(this.answerList);
        dest.writeInt(this.isResult);
        dest.writeInt(this.score_points);
        dest.writeInt(this.isSuccess);
        dest.writeInt(this.isAnswer);
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
