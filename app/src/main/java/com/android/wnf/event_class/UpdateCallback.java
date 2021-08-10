package com.android.wnf.event_class;

import com.android.wnf.model.Answer;

import java.util.ArrayList;

public class UpdateCallback {
    public int model_position;
    public ArrayList<Answer> answerList;
    public int isAnswer;
    public int isResult;
    public UpdateCallback(int model_position , ArrayList<Answer> answerList , int isAnswer , int isResult){
        this.model_position = model_position;
        this.answerList = answerList;
        this.isAnswer = isAnswer;
        this.isResult = isResult;
    }
}
