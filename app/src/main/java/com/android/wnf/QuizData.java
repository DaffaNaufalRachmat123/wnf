package com.android.wnf;

import com.android.wnf.model.Answer;
import com.android.wnf.model.ParentQuiz;
import com.android.wnf.model.Quiz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizData {
    private List<ParentQuiz> parentQuizList = new ArrayList<>();
    public QuizData(){
        initializeQuizList();
    }
    public List<ParentQuiz> getParentQuizList() { return parentQuizList; }
    private void initializeQuizList(){
        parentQuizList.add(new ParentQuiz(0 , "Quiz 1" , new ArrayList<Quiz>() {{
            add(new Quiz(0 , "\\mathrm{Hasil\\;dari}\\; \\frac{1}{4}+\\frac{1}{5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(0 , "\\frac{9}{20}" , 0 , 1 , -1 , 1));
                add(new Answer(1 , "\\frac{1}{20}" , 0 , 0, -1 , 1));
                add(new Answer(2 , "\\frac{3}{20}" , 0 , 0, -1 , 1));
                add(new Answer(3 , "\\frac{1}{9}" , 0 , 0, -1 , 1));
            }} , 0 , 0,0 , 0 , -1));
            add(new Quiz(1 , "\\mathrm{Hasil\\; dari}\\; 17 - (3 x (-8))\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(4 , "49" , 0 , 0, -1 , 1));
                add(new Answer(5 , "41" , 0 , 1, -1 , 1));
                add(new Answer(6 , "-7" , 0 , 0, -1 , 1));
                add(new Answer(7 , "-41" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(2 , "\\mathrm{Hasil\\; dari}\\; -14 + 19\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(8 , "\\mathrm{Bilangan\\; Nol}" , 0 , 0, -1 , 1));
                add(new Answer(9 , "\\mathrm{Bilangan\\; Bulat\\;Positif}" , 0 , 1, -1 , 1));
                add(new Answer(10 , "\\mathrm{Bilangan\\; Bulat\\; Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(11, "\\mathrm{Bilangan\\; Pecahan}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(3 , "\\mathrm{Hasil\\;dari}\\; 4\\frac{1}{2}+3\\frac{2}{5}\\;\\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(12 , "1\\frac{9}{10}" , 0 , 0, -1 , 1));
                add(new Answer(13 , "2\\frac{1}{9}" , 0 , 0, -1 , 1));
                add(new Answer(14 , "7\\frac{9}{10}" , 0 , 1, -1 , 1));
                add(new Answer(15 , "7\\frac{1}{9}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(4 , "\\mathrm{Hasil\\; dari}\\; 0,125 + 0,25\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(16 , "\\mathrm{0,15}" , 0 , 0, -1 , 1));
                add(new Answer(17 , "\\mathrm{0,75}" , 0 , 0, -1 , 1));
                add(new Answer(18 , "\\mathrm{0,315}" , 0 , 0, -1 , 1));
                add(new Answer(19 , "\\mathrm{0,375}" , 0 , 1, -1 , 1));
            }}, 0  , 0 ,0 , 0, -1));
        }}));
        parentQuizList.add(new ParentQuiz(1 , "Quiz 2" , new ArrayList<Quiz>(){{
            add(new Quiz(5 , "\\mathrm{Hasil\\; dari}\\; \\sqrt[3]{729}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(20 , "\\mathrm{3}" , 0 , 0, -1 , 1));
                add(new Answer(21 , "\\mathrm{7}" , 0 , 0, -1 , 1));
                add(new Answer(22 , "\\mathrm{11}" , 0 , 0, -1 , 1));
                add(new Answer(23 , "\\mathrm{9}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(6 , "\\mathrm{Hasil\\; dari} -18 - (-18) \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(24 , "\\mathrm{Bilangan\\; Nol}" , 0 , 1, -1 , 1));
                add(new Answer(25 , "\\mathrm{Bilangan\\; Bulat\\; Positif}" , 0 , 0, -1 , 1));
                add(new Answer(26 , "\\mathrm{Bilangan\\; Bulat\\; Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(27 , "\\mathrm{Bilangan\\; Pecahan}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(7 , "\\mathrm{Hasil\\; dari}\\; \\frac{3}{4}x\\frac{1}{5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(28 , "\\frac{9}{20}" , 0 , 0, -1 , 1));
                add(new Answer(29 , "\\frac{3}{20}" , 0 , 1, -1 , 1));
                add(new Answer(30 , "\\frac{7}{9}" , 0 , 0, -1 , 1));
                add(new Answer(31 , "\\frac{4}{9}" , 0 , 0, -1 , 1));
            }}, 0  , 0 ,0 , 0, -1));
            add(new Quiz(8 , "\\mathrm{Bentuk\\; Desimal\\; dari}\\; \\frac{7}{5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(32 , "\\mathrm{0,12}" , 0 , 0, -1 , 1));
                add(new Answer(33 , "\\mathrm{1,2}" , 0 , 0, -1 , 1));
                add(new Answer(34 , "\\mathrm{1,4}" , 0 , 1, -1 , 1));
                add(new Answer(35 , "\\mathrm{0,14}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(9 , "\\mathrm{Hasil\\; dari}\\; 1\\frac{2}{5}÷\\frac{14}{5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(36 , "\\frac{1}{14}" , 0 , 0, -1 , 1));
                add(new Answer(37 , "\\frac{1}{7}" , 0 , 0, -1 , 1));
                add(new Answer(38 , "\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(39 , "\\frac{1}{2}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
        }}));//

        parentQuizList.add(new ParentQuiz(2 , "Quiz 3" , new ArrayList<Quiz>(){{
            add(new Quiz(10 , "\\mathrm{Manakah\\; yang\\; disebut\\; Bilangan\\; Genap\\;...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(40 , "\\mathrm{1,3,5,7,...}" , 0 , 0, -1 , 1));
                add(new Answer(41 , "\\mathrm{1,2,3,4,5,...}" , 0 , 0, -1 , 1));
                add(new Answer(42 , "\\mathrm{2,4,6,7,8,...}" , 0 , 0, -1 , 1));
                add(new Answer(43 , "\\mathrm{2,4,6,8,10,...}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(11 , "\\mathrm{Pecahan\\; Biasa\\; Dari\\; 27\\%\\; adalah..." , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(44 , "\\frac{27}{100}" , 0 , 1, -1 , 1));
                add(new Answer(45 , "\\frac{27}{10}" , 0 , 0, -1 , 1));
                add(new Answer(46 , "\\frac{27}{1000}" , 0 , 0, -1 , 1));
                add(new Answer(47 , "\\frac{27}{1}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(12 , "\\mathrm{Hasil\\; dari\\; 4,5÷1,5\\; adalah\\;...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(48 , "0,3" , 0 , 0, -1 , 1));
                add(new Answer(49 , "3" , 0 , 1, -1 , 1));
                add(new Answer(50 , "30" , 0 , 0, -1 , 1));
                add(new Answer(51 , "300" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(13 , "\\mathrm{Jika\\; Kamu\\; Mempunyai\\; Bilangan\\; }\\\\\\mathrm{[2,3,5,7,11].}\\mathrm{\\; Bilangan\\; yang}\\\\\\mathrm{kamu\\; punya\\; adalah\\; ...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(52 , "\\mathrm{Bilangan\\; Prima}" , 0 , 1, -1 , 1));
                add(new Answer(53 , "\\mathrm{Bilangan\\; Bulat\\; Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(54 , "\\mathrm{Bilangan\\; Ganjil}" , 0 , 0, -1 , 1));
                add(new Answer(55 , "\\mathrm{Bilangan\\; Genap}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(14 , "\\mathrm{Hasil\\; dari}\\; 1\\frac{2}{5}÷\\frac{1}{5}\\; \\mathrm{adalah\\;...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(56 , "\\frac{3}{5}" , 0 , 0, -1 , 1));
                add(new Answer(57 , "\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(58 , "1\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(59 , "1\\frac{3}{5}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
        }}));

        parentQuizList.add(new ParentQuiz(3 , "Quiz 4" , new ArrayList<Quiz>(){{
            add(new Quiz(15 , "\\mathrm{Bentuk\\; Pecahan}\\ \\mathrm{Campuran\\; dari}\\; \\frac{12}{8}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(60 , "1\\frac{3}{8}" , 0 , 0, -1 , 1));
                add(new Answer(61 , "1\\frac{1}{8}" , 0 , 0, -1 , 1));
                add(new Answer(62 , "1\\frac{1}{4}" , 0 , 0, -1 , 1));
                add(new Answer(63 , "1\\frac{4}{8}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(16 , "\\mathrm{Hasil\\; dari}\\; 5^{2}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(64 , "\\mathrm{22}" , 0 , 0, -1 , 1));
                add(new Answer(65 , "\\mathrm{23}" , 0 , 0, -1 , 1));
                add(new Answer(66 , "\\mathrm{24}" , 0 , 0, -1 , 1));
                add(new Answer(67 , "\\mathrm{25}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(17 , "\\mathrm{Hasil\\; dari}\\; 3\\frac{3}{4}-2\\frac{1}{5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(68 , "\\frac{11}{20}" , 0 , 0, -1 , 1));
                add(new Answer(69 , "1\\frac{11}{20}" , 0 , 1, -1 , 1));
                add(new Answer(70 , "1\\frac{9}{20}" , 0 , 0, -1 , 1));
                add(new Answer(71 , "\\frac{9}{20}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(18 , "\\mathrm{Apa\\; yang\\; dimaksud\\; dengan}\\; \\mathrm{5^{3}}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(72 , "\\mathrm{5\\; +\\; 5\\; + 5}" , 0 , 0, -1 , 1));
                add(new Answer(73 , "\\mathrm{5\\; x\\; 3}" , 0 , 0, -1 , 1));
                add(new Answer(74 , "\\mathrm{5\\; x\\; 5\\; x\\; 5}" , 0 , 1, -1 , 1));
                add(new Answer(75 , "\\mathrm{3\\; x\\; 3\\; x\\; 3\\; x\\; 3\\; x\\; 3}" , 0 , 0, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
            add(new Quiz(19 , "\\mathrm{Budi\\; Punya\\; angka}\\; \\frac{4}{5}.\\ \\mathrm{Angka\\;5}\\; \\mathrm{adalah...}" , R.raw.happy_girly , new ArrayList<Answer>() {{
                add(new Answer(76 , "\\mathrm{Perkalian}" , 0 , 0, -1 , 1));
                add(new Answer(77 , "\\mathrm{Pembagian}" , 0 , 0, -1 , 1));
                add(new Answer(78 , "\\mathrm{Pembilang}" , 0 , 0, -1 , 1));
                add(new Answer(79 , "\\mathrm{Penyebut}" , 0 , 1, -1 , 1));
            }}, 0 , 0 ,0 , 0, -1));
        }}));

    }
}
