package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import org.greenrobot.eventbus.EventBus;

public class ActivityQuizPage extends AppCompatActivity {
    private AppCompatButton btnMenu , btnOtherQuiz;
    private boolean isAnotherQuiz = false;
    private int numberQuiz = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        btnOtherQuiz = findViewById(R.id.btnOtherQuiz);
        btnMenu = findViewById(R.id.btnMenu);
        isAnotherQuiz = getIntent().getBooleanExtra("is_another_quiz" , false);
        if(!isAnotherQuiz){
            btnOtherQuiz.setVisibility(View.GONE);
        } else {
            numberQuiz = getIntent().getIntExtra("number_quiz" , 0);
            btnOtherQuiz.setText("Quis " + String.valueOf(numberQuiz + 1));
            btnOtherQuiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new ActivityQuizDetail.FinishQuiz());
                    startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                            .putExtra("is_review" , false)
                    .putExtra("parent_quiz" , new QuizData().getParentQuizList().get(numberQuiz))
                    .putExtra("quiz_position" , numberQuiz));
                }
            });
        }
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ActivityQuizDetail.FinishQuiz());
                finish();
                startActivity(new Intent(getApplicationContext() , ActivityQuiz.class));
            }
        });
    }
}
