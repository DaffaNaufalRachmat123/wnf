package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class QuizResult implements Parcelable {
    private int id;
    private int isComplete;
    private int isTrue;
    private List<Answer> answerList;
    public QuizResult(int id , int isComplete , int isTrue){
        this.id = id;
        this.isComplete = isComplete;
        this.isTrue = isTrue;
    }

    @Override
    public String toString() {
        return "QuizResult{" +
                "id=" + id +
                ", isComplete=" + isComplete +
                ", isTrue=" + isTrue +
                ", answerList=" + answerList +
                '}';
    }

    public int isTrue(){ return isTrue; }
    public QuizResult(Parcel parcel){
        id = parcel.readInt();
        isComplete = parcel.readInt();
        isTrue = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.isComplete);
        dest.writeInt(this.isTrue);
    }
    public static final Parcelable.Creator<QuizResult> CREATOR = new Creator<QuizResult>() {
        @Override
        public QuizResult createFromParcel(Parcel source) {
            return new QuizResult(source);
        }

        @Override
        public QuizResult[] newArray(int size) {
            return new QuizResult[size];
        }
    };
}
