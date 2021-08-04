package com.android.wnf.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ParentQuiz implements Parcelable {
    private int id;
    private String parent_quiz;
    private List<Quiz> quizList;
    public ParentQuiz(int id , String parent_quiz , List<Quiz> quizList){
        this.id = id;
        this.parent_quiz = parent_quiz;
        this.quizList = quizList;
    }
    public int getId(){ return id; }
    public String getParentQuiz(){ return parent_quiz; }
    public List<Quiz> getQuizList(){ return quizList; }
    public ParentQuiz(Parcel in){
        id = in.readInt();
        parent_quiz = in.readString();
        quizList = in.readArrayList(Quiz.class.getClassLoader());
    }

    @Override
    public String toString() {
        return "ParentQuiz{" +
                "id=" + id +
                ", parent_quiz='" + parent_quiz + '\'' +
                ", quizList=" + quizList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.parent_quiz);
        dest.writeList(this.quizList);
    }
    public static final Parcelable.Creator<ParentQuiz> CREATOR = new Creator<ParentQuiz>() {
        @Override
        public ParentQuiz createFromParcel(Parcel source) {
            return new ParentQuiz(source);
        }

        @Override
        public ParentQuiz[] newArray(int size) {
            return new ParentQuiz[size];
        }
    };
}
