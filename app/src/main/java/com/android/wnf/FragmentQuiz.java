package com.android.wnf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class FragmentQuiz extends Fragment {
    private ConstraintLayout headerStatus;
    private ImageView icStateSound , icHeaderStatus , icStatusResult;
    private SeekBar seekBar;
    private AppCompatTextView questionText , minuteText , headerStatusText , statusText , statusResultText , scoreText;
    private CardView btnPrev , btnNext , btnSubmit;
    private AppCompatButton btnReview;
    private LinearLayout btnRetry;
    private CardView resultView;
    private RecyclerView recyclerViewAnswer;
    public static FragmentQuiz newInstance(){
        FragmentQuiz quiz = new FragmentQuiz();

        return quiz;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz , container , false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
