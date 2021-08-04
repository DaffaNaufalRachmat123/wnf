package com.android.wnf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.R;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.model.Quiz;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Quiz> quizList;
    private QuestionListener listener;
    class ViewHolder extends RecyclerView.ViewHolder {
        public TeXView questionText;
        public ConstraintLayout parentContainer;
        public ViewHolder(View itemView){
            super(itemView);
            questionText = itemView.findViewById(R.id.questionText);
            parentContainer = itemView.findViewById(R.id.parentContainer);
        }
    }
    public QuestionAdapter(Context context , List<Quiz> quizList , QuestionListener listener){
        this.context = context;
        this.quizList = quizList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_question , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holders = (ViewHolder) holder;
        Quiz quiz = quizList.get(position);
        holders.questionText.setLaTeX(String.valueOf(position + 1) + ".\t" + quiz.getQuestion());
        holders.parentContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public interface QuestionListener {
        public void onClick(int position);
    }
}
