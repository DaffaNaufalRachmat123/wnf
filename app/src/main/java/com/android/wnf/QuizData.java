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
            add(new Quiz(0 , "\\textbf{Hasil dari}\\frac{1}{4}+\\frac{1}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(0 , "\\frac{9}{20}" , 0 , 1 , -1 , 1));
                add(new Answer(1 , "\\frac{1}{20}" , 0 , 0, -1 , 1));
                add(new Answer(2 , "\\frac{3}{20}" , 0 , 0, -1 , 1));
                add(new Answer(3 , "\\frac{1}{9}" , 0 , 0, -1 , 1));
            }} , 0 ,0 , 0));
            add(new Quiz(1 , "\\textbf{Hasil dari} 17 - (3 x (-8)) \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(4 , "49" , 0 , 0, -1 , 1));
                add(new Answer(5 , "41" , 0 , 1, -1 , 1));
                add(new Answer(6 , "-7" , 0 , 0, -1 , 1));
                add(new Answer(7 , "-41" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(2 , "\\textbf{Hasil dari}-14 + 19\\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(8 , "\\textbf{Bilangan Nol}" , 0 , 0, -1 , 1));
                add(new Answer(9 , "\\textbf{Bilangan Bulat Positif}" , 0 , 1, -1 , 1));
                add(new Answer(10 , "\\textbf{Bilangan Bulat Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(11, "\\textbf{Bilangan Pecahan}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(3 , "\\textbf{Hasil dari} 4\\frac{1}{2}+3\\frac{2}{5}\\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(12 , "1\\frac{9}{10}" , 0 , 0, -1 , 1));
                add(new Answer(13 , "2\\frac{1}{9}" , 0 , 0, -1 , 1));
                add(new Answer(14 , "7\\frac{9}{10}" , 0 , 1, -1 , 1));
                add(new Answer(15 , "7\\frac{1}{9}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} 0,125 + 0,25 \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(16 , "\\textbf{0,15}" , 0 , 0, -1 , 1));
                add(new Answer(17 , "\\textbf{0,75}" , 0 , 0, -1 , 1));
                add(new Answer(18 , "\\textbf{0,315}" , 0 , 0, -1 , 1));
                add(new Answer(19 , "\\textbf{0,375}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
        }}));
        parentQuizList.add(new ParentQuiz(1 , "Quiz 2" , new ArrayList<Quiz>(){{
            add(new Quiz(4 , "\\textbf{Hasil dari} \\sqrt[3]{729} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(20 , "\\textbf{3}" , 0 , 0, -1 , 1));
                add(new Answer(21 , "\\textbf{7}" , 0 , 0, -1 , 1));
                add(new Answer(22 , "\\textbf{11}" , 0 , 0, -1 , 1));
                add(new Answer(23 , "\\textbf{9}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} -18 - (-18) \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(24 , "\\textbf{Bilangan Nol}" , 0 , 1, -1 , 1));
                add(new Answer(25 , "\\textbf{Bilangan Bulat Positif}" , 0 , 0, -1 , 1));
                add(new Answer(26 , "\\textbf{Bilangan Bulat Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(27 , "\\textbf{Bilangan Pecahan}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} \\frac{3}{4}x\\frac{1}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(28 , "\\frac{9}{20}" , 0 , 0, -1 , 1));
                add(new Answer(29 , "\\frac{3}{20}" , 0 , 1, -1 , 1));
                add(new Answer(30 , "\\frac{7}{9}" , 0 , 0, -1 , 1));
                add(new Answer(31 , "\\frac{4}{9}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} Bentuk Desimal dari \\frac{7}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(32 , "\\textbf{0,12}" , 0 , 0, -1 , 1));
                add(new Answer(33 , "\\textbf{1,2}" , 0 , 0, -1 , 1));
                add(new Answer(34 , "\\textbf{1,4}" , 0 , 1, -1 , 1));
                add(new Answer(35 , "\\textbf{0,14}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} 1\\frac{2}{5}÷\\frac{14}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(36 , "\\frac{1}{14}" , 0 , 0, -1 , 1));
                add(new Answer(37 , "\\frac{1}{7}" , 0 , 0, -1 , 1));
                add(new Answer(38 , "\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(39 , "\\frac{1}{2}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
        }}));

        parentQuizList.add(new ParentQuiz(2 , "Quiz 3" , new ArrayList<Quiz>(){{
            add(new Quiz(4 , "\\textbf{Manakah yang disebut Bilangan Genap...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(40 , "\\textbf{1,3,5,7,...}" , 0 , 0, -1 , 1));
                add(new Answer(41 , "\\textbf{1,2,3,4,5,...}" , 0 , 0, -1 , 1));
                add(new Answer(42 , "\\textbf{2,4,6,7,8,...}" , 0 , 0, -1 , 1));
                add(new Answer(43 , "\\textbf{2,4,6,8,10,...}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Pecahan Biasa dari 27% adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(44 , "\\frac{27}{100}" , 0 , 0, -1 , 1));
                add(new Answer(45 , "\\frac{27}{10}" , 0 , 0, -1 , 1));
                add(new Answer(46 , "\\frac{27}{1000}" , 0 , 0, -1 , 1));
                add(new Answer(47 , "\\frac{27}{1}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari 4,5÷1,5 adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(48 , "0,3" , 0 , 0, -1 , 1));
                add(new Answer(49 , "3" , 0 , 1, -1 , 1));
                add(new Answer(50 , "30" , 0 , 0, -1 , 1));
                add(new Answer(51 , "300" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Jika Kamu Mempunyai Bilangan [2,3,5,7,11].}\\\textbf{Bilangan yang kamu punya adalah ...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(52 , "\\textbf{Bilangan Prima}" , 0 , 1, -1 , 1));
                add(new Answer(53 , "\\textbf{Bilangan Bulat Negatif}" , 0 , 0, -1 , 1));
                add(new Answer(54 , "\\textbf{Bilangan Ganjil}" , 0 , 0, -1 , 1));
                add(new Answer(55 , "\\textbf{Bilangan Genap}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} 1\\frac{2}{5}÷\\frac{1}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(56 , "\\frac{3}{5}" , 0 , 0, -1 , 1));
                add(new Answer(57 , "\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(58 , "1\\frac{1}{5}" , 0 , 0, -1 , 1));
                add(new Answer(59 , "1\\frac{3}{5}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
        }}));

        parentQuizList.add(new ParentQuiz(3 , "Quiz 4" , new ArrayList<Quiz>(){{
            add(new Quiz(4 , "\\textbf{Bentuk Pecahan}\\Campuran dari \\frac{12}{8} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(60 , "1\\frac{3}{8}" , 0 , 0, -1 , 1));
                add(new Answer(61 , "1\\frac{1}{8}" , 0 , 0, -1 , 1));
                add(new Answer(62 , "1\\frac{1}{4}" , 0 , 0, -1 , 1));
                add(new Answer(63 , "1\\frac{4}{8}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari}5^{2} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(64 , "\\textbf{22}" , 0 , 0, -1 , 1));
                add(new Answer(65 , "\\textbf{23}" , 0 , 0, -1 , 1));
                add(new Answer(66 , "\\textbf{24}" , 0 , 0, -1 , 1));
                add(new Answer(67 , "\\textbf{25}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Hasil dari} 3\\frac{3}{4}-2\\frac{1}{5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(68 , "\\frac{11}{20}" , 0 , 0, -1 , 1));
                add(new Answer(69 , "1\\frac{11}{20}" , 0 , 1, -1 , 1));
                add(new Answer(70 , "1\\frac{9}{20}" , 0 , 0, -1 , 1));
                add(new Answer(71 , "\\frac{9}{20}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Apa yang dimaksud dengan}\\5^{3}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(72 , "\\textbf{5 + 5 + 5}" , 0 , 0, -1 , 1));
                add(new Answer(73 , "\\textbf{5 x 3}" , 0 , 0, -1 , 1));
                add(new Answer(74 , "\\textbf{5 x 5 x 5}" , 0 , 1, -1 , 1));
                add(new Answer(75 , "\\textbf{3 x 3 x 3 x 3 x 3}" , 0 , 0, -1 , 1));
            }}, 0 ,0 , 0));
            add(new Quiz(4 , "\\textbf{Budi Punya angka} \\frac{4}{5}.\\ \textbf{Angka 5} \\textbf{adalah...}" , R.raw.boruto_opening , new ArrayList<Answer>() {{
                add(new Answer(76 , "\\textbf{Perkalian}" , 0 , 0, -1 , 1));
                add(new Answer(77 , "\\textbf{Pembagian}" , 0 , 0, -1 , 1));
                add(new Answer(78 , "\\textbf{Pembilang}" , 0 , 0, -1 , 1));
                add(new Answer(79 , "\\textbf{Penyebut}" , 0 , 1, -1 , 1));
            }}, 0 ,0 , 0));
        }}));

    }
}
