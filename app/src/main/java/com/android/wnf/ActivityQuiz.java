package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.wnf.model.Quiz;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ActivityQuiz extends AppCompatActivity {
    private AppCompatButton btnQuiz1 , btnQuiz2 , btnQuiz3 , btnQuiz4;
    private ImageView icHome;
    private List<Quiz> quizList = new ArrayList<>();
    private QuizData quizData = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        EventBus.getDefault().register(this);
        btnQuiz1 = findViewById(R.id.btnQuiz1);
        btnQuiz2 = findViewById(R.id.btnQuiz2);
        btnQuiz3 = findViewById(R.id.btnQuiz3);
        btnQuiz4 = findViewById(R.id.btnQuiz4);
        icHome = findViewById(R.id.icHome);

        quizData = new QuizData();

        btnQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                .putExtra("parent_quiz" , quizData.getParentQuizList().get(0))
                .putExtra("quiz_position" , 0));
            }
        });

        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("parent_quiz" , quizData.getParentQuizList().get(1))
                        .putExtra("quiz_position" , 1));
            }
        });

        btnQuiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("parent_quiz" , quizData.getParentQuizList().get(2))
                        .putExtra("quiz_position" , 2));
            }
        });

        btnQuiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("parent_quiz" , quizData.getParentQuizList().get(3))
                        .putExtra("quiz_position" , 3));
            }
        });

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(QuizTrigger data){

    }

    static class QuizTrigger {}

    private void stopMusic(){
        EventBus.getDefault().post(new ActivityHome.StopSound());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
