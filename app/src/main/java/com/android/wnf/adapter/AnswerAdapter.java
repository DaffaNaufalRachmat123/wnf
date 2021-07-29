package com.android.wnf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.R;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.model.Answer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Answer> answerList = new ArrayList<>();
    private AnswerListener listener = null;
    public AnswerAdapter(List<Answer> answerList){
        this.answerList = answerList;
    }
    public void setAnswerListener(AnswerListener listener){
        this.listener = listener;
    }
    class AnswerHolder extends RecyclerView.ViewHolder {
        private ImageView icState;
        private TeXView latexText;
        public AnswerHolder(View itemView){
            super(itemView);
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
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        AnswerHolder holders = (AnswerHolder) holder;
        Answer answer = answerList.get(position);
        if(answer.isChecked()){
            holders.icState.setImageResource(R.drawable.ic_dot_gray);
        } else {
            holders.icState.setImageResource(0);
        }
        holders.icState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
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
        void onChoose(int position);
    }
}
