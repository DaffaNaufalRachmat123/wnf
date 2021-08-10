package com.android.wnf.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.R;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.model.Answer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Answer> answerList = new ArrayList<>();
    private AnswerListener listener = null;
    private boolean isClickable = true;
    public AnswerAdapter(Context context , ArrayList<Answer> answerList){
        this.context = context;
        this.answerList = answerList;
    }
    public void setClickable(boolean isClickable){
        this.isClickable = isClickable;
    }
    public boolean isClickable(){ return isClickable; }
    public void setAnswerListener(AnswerListener listener){
        this.listener = listener;
    }
    class AnswerHolder extends RecyclerView.ViewHolder {
        private ImageView icState;
        private TeXView latexText;
        private ConstraintLayout parentContainer;
        public AnswerHolder(View itemView){
            super(itemView);
            parentContainer = itemView.findViewById(R.id.parentContainer);
            icState = itemView.findViewById(R.id.icState);
            latexText = itemView.findViewById(R.id.latexText);
        }
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new AnswerHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_answer , parent , false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        AnswerHolder holders = (AnswerHolder) holder;
        Answer answer = answerList.get(position);
        if(answer.getResult() == -1){
            if(answer.isChecked() == 1){
                holders.icState.setImageResource(R.drawable.ic_dot_selected);
                holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_selected));
                holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_selected));
            } else {
                holders.icState.setImageResource(R.drawable.ic_dot_unselected);
                holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_unselected));
                holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_unselected));
            }
        } else if(answer.getResult() == 0){
            if(answer.isChecked() == 0){
                holders.icState.setImageResource(R.drawable.ic_dot_unselected);
                holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_unselected));
                holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_unselected));
            } else if(answer.isChecked() == 1){
                holders.icState.setImageResource(R.drawable.ic_dot_incorrect);
                holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_incorrect));
                holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_incorrect));
            }
        } else if(answer.getResult() == 1){
            holders.icState.setImageResource(R.drawable.ic_dot_correct);
            holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_correct));
            holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_correct));
        } else if(answer.getResult() == 2){
            holders.icState.setImageResource(R.drawable.ic_dot_unselected);
            holders.icState.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_dot_unselected));
            holders.parentContainer.setBackground(ContextCompat.getDrawable(context , R.drawable.bg_unselected));
        }
        holders.icState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("isClickable" , String.valueOf(answer.isClickable()));
                if(answer.isClickable() == 1){
                    if(listener != null){
                        listener.onChoose(position);
                    }
                }
            }
        });
        holders.parentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("isClickable" , String.valueOf(answer.isClickable()));
                if(answer.isClickable() == 1){
                    if(listener != null){
                        listener.onChoose(position);
                    }
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
        void onChoose(int position);
    }
}
