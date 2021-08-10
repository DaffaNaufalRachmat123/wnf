package com.android.wnf.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.R;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.model.Answer;

import java.util.List;

public class AnswerAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Answer> answerList;
    private Context context;
    private boolean isClickable;
    private AnswerListener listener;
    public void setAnswerListener(AnswerListener listener){
        this.listener = listener;
    }
    public void setClickable(boolean isClickable){
        this.isClickable = isClickable;
    }
    public boolean isClickable(){ return isClickable; }
    public AnswerAdapters(Context context , List<Answer> answerList){
        this.context = context;
        this.answerList = answerList;
    }
    class AnswerHolder extends RecyclerView.ViewHolder {
        private ImageView icState;
        private TeXView latexText;
        private ConstraintLayout parentContainer;
        public AnswerHolder(View itemView){
            super(itemView);
            icState = itemView.findViewById(R.id.icState);
            latexText = itemView.findViewById(R.id.latexText);
            parentContainer = itemView.findViewById(R.id.parentContainer);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnswerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Answer answer = answerList.get(position);
        AnswerHolder holders = (AnswerHolder) holder;
        holders.icState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKABLE" , String.valueOf(isClickable));
                if(isClickable){
                    listener.onChoose(position);
                }
            }
        });
        holders.parentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICKABLE" , String.valueOf(isClickable));
                if(isClickable){
                    listener.onChoose(position);
                }
            }
        });
        holders.latexText.setLaTeX(answer.getAnswer());
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }
    public interface AnswerListener {
        public void onChoose(int position);
    }
}
