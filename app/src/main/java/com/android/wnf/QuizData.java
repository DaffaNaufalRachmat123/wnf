package com.android.wnf;

import com.android.wnf.model.Answer;
import com.android.wnf.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizData {
    private List<Quiz> quizList = new ArrayList<>();
    public QuizData(){
        initializeQuizList();
    }
    public List<Quiz> getQuizList() { return quizList; }
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
