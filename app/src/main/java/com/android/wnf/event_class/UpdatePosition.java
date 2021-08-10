package com.android.wnf.event_class;

public class UpdatePosition {
    public int model_position;
    public int lastChoosePosition;
    public UpdatePosition(int model_position , int lastChoosePosition){
        this.model_position = model_position;
        this.lastChoosePosition = lastChoosePosition;
    }
}
