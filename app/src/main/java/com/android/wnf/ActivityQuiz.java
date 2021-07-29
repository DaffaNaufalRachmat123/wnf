package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.wnf.model.Answer;
import com.android.wnf.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class ActivityQuiz extends AppCompatActivity {
    private AppCompatButton btnQuiz1 , btnQuiz2 , btnQuiz3 , btnQuiz4;
    private ImageView icHome;
    private List<Quiz> quizList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        btnQuiz1 = findViewById(R.id.btnQuiz1);
        btnQuiz2 = findViewById(R.id.btnQuiz2);
        btnQuiz3 = findViewById(R.id.btnQuiz3);
        btnQuiz4 = findViewById(R.id.btnQuiz4);
        icHome = findViewById(R.id.icHome);

        initializeQuizList();

        btnQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                .putExtra("quiz" , quizList.get(0)));
            }
        });

        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("quiz" , quizList.get(1)));
            }
        });

        btnQuiz3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("quiz" , quizList.get(2)));
            }
        });

        btnQuiz4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuizDetail.class)
                        .putExtra("quiz" , quizList.get(3)));
            }
        });

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void initializeQuizList(){
        quizList.add(new Quiz(0 , "\\textbf{Hasil dari }\\quad\\frac{1}{4} + \\frac{1}{5}\n" +
                "\\\\\\textbf{adalah....}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
            add(new Answer(0 , "\\frac{9}{20}" , false));
            add(new Answer(1 , "\\frac{2}{20}" , false));
            add(new Answer(2 , "\\frac{1}{9}" , false));
            add(new Answer(3 , "\\frac{1}{20}" , false));
        }}));

        quizList.add(new Quiz(1 , "\\textbf{Hasil dari}\\quad\\sqrt[3]{729}\\quad\\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
            add(new Answer(0 , "11" , false));
            add(new Answer(1 , "3" , false));
            add(new Answer(2 , "7" , false));
            add(new Answer(3 , "9" , false));
        }}));

        quizList.add(new Quiz(2 , "\\textbf{Manakah yang disebut}\\\\\\textbf{Bilangan genap...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
            add(new Answer(0 , "1 , 3, 5, 7, ..." , false));
            add(new Answer(1 , "2, 4, 6, 8, 10, ..." , false));
            add(new Answer(2 , "1, 2, 3, 4, 5, ..." , false));
            add(new Answer(3 , "2, 4, 6, 7, 8, ..." , false));
        }}));

        quizList.add(new Quiz(3 , "\\textbf{Bentuk Pecahan}\\\\\\textbf{Campuran dari}\\quad\\frac{12}{8}\\\\\\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
            add(new Answer(0 , "1\\frac{4}{8}" , false));
            add(new Answer(1 , "1\\frac{1}{4}" , false));
            add(new Answer(2 , "1\\frac{1}{8}" , false));
            add(new Answer(3 , "1\\frac{3}{8}" , false));
        }}));

    }
}
