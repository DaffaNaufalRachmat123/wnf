package com.android.wnf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.adapter.AnswerAdapter;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.event_class.UpdateCallback;
import com.android.wnf.event_class.UpdatePosition;
import com.android.wnf.interfaces.QuizDetailListener;
import com.android.wnf.model.Answer;
import com.android.wnf.model.Quiz;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class FragmentQuizDetail extends Fragment {
    private Quiz quiz;
    private boolean isReview = false;
    private boolean isResult = false;
    private int parent_position;
    private int model_position;
    private ConstraintLayout headerStatus , constraintQuestion;
    private ImageView icStateSound , icHeaderStatus , icStatusResult;
    private CardView resultView , cardViewStatus;
    private SeekBar seekBar;
    private TeXView questionText;
    private AppCompatTextView minuteText , headerStatusText , statusText , statusResultText , scoreText;
    private MediaPlayer player;
    private AppCompatButton btnReview;
    private LinearLayout btnRetry;
    private boolean isPlaying = false;
    private Handler handler = new Handler();
    private AnswerAdapter mAdapter;
    private RecyclerView recyclerViewAnswer;
    private ArrayList<Answer> answerList = new ArrayList();
    private int lastChoosePosition = -1;

    private BroadcastReceiver answerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            for(int i = 0; i < answerList.size(); i++){
                answerList.get(i).setClickable(0);
            }
        }
    };

    public static Fragment newInstance(Quiz quiz , ArrayList<Answer> answerList , boolean isReview , boolean isResult , int model_position, int parent_position){
        FragmentQuizDetail fragment = new FragmentQuizDetail();
        Bundle bundle = new Bundle();
        bundle.putParcelable("quiz" , quiz);
        bundle.putBoolean("is_review" , isReview);
        bundle.putParcelableArrayList("answer_list" , answerList);
        bundle.putBoolean("is_result" , isResult);
        bundle.putInt("model_position" , model_position);
        bundle.putInt("parent_position" , parent_position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        quiz = bundle.getParcelable("quiz");
        answerList = bundle.getParcelableArrayList("answer_list");
        isReview = bundle.getBoolean("is_review");
        isResult = bundle.getBoolean("is_result");
        model_position = bundle.getInt("model_position");
        parent_position = bundle.getInt("parent_position");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        initView(view);
        if(quiz != null){
            if(!isReview){
                if(!isResult){
                    initQuestion();
                    initAnswerList();
                } else {
                    constraintQuestion.setVisibility(View.GONE);
                    resultView.setVisibility(View.VISIBLE);

                    btnReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });
                    btnRetry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            } else {

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TriggerChange data){
        if(lastChoosePosition != -1){
            boolean isTrue = quiz.getAnswerList().get(lastChoosePosition).isCorrect() == 1;
            if(isTrue){
                quiz.getAnswerList().get(lastChoosePosition).setResult(1);
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(requireContext() , R.drawable.bg_status_correct));
                icHeaderStatus.setImageResource(R.drawable.ic_done_white);
                statusText.setText(requireContext().getResources().getString(R.string.correct));
                headerStatusText.setText(requireContext().getResources().getString(R.string.correct_badge));
            } else {
                quiz.getAnswerList().get(lastChoosePosition).setResult(0);
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(requireContext() , R.drawable.bg_status_incorrect));
                icHeaderStatus.setImageResource(R.drawable.ic_close_white);
                statusText.setText(requireContext().getResources().getString(R.string.incorrect));
                headerStatusText.setText(requireContext().getResources().getString(R.string.incorrect_badge));
                for(int i = 0; i < quiz.getAnswerList().size(); i++){
                    if(quiz.getAnswerList().get(i).isCorrect() == 1){
                        quiz.getAnswerList().get(i).setResult(1);
                        break;
                    }
                }
            }
            for(int i = 0; i < quiz.getAnswerList().size(); i++){
                quiz.getAnswerList().get(i).setClickable(0);
            }
            EventBus.getDefault().post(new UpdateCallback(model_position , quiz.getAnswerList() , 1 , isTrue ? 1 : 0));
            mAdapter.notifyDataSetChanged();
        }
    }

    static class TriggerChange {}

    private void initQuestion(){
        questionText.setLaTeX(quiz.getQuestion());
    }
    private void initPlayer(){

    }
    private void initSeekBarPlayer(){

    }
    private void initAnswerList(){
        recyclerViewAnswer.setHasFixedSize(true);
        recyclerViewAnswer.setItemAnimator(null);
        recyclerViewAnswer.setLayoutManager(new LinearLayoutManager(requireContext() , RecyclerView.VERTICAL , false));
        mAdapter = new AnswerAdapter(requireContext() , quiz.getAnswerList());
        mAdapter.setAnswerListener(new AnswerAdapter.AnswerListener() {
            @Override
            public void onChoose(int position) {
                if(lastChoosePosition != -1){
                    quiz.getAnswerList().get(lastChoosePosition).setChecked(0);
                    mAdapter.notifyItemChanged(lastChoosePosition);
                }
                lastChoosePosition = position;
                quiz.getAnswerList().get(lastChoosePosition).setChecked(1);
                mAdapter.notifyItemChanged(lastChoosePosition);
                EventBus.getDefault().post(new UpdatePosition(model_position , lastChoosePosition));
            }
        });
        recyclerViewAnswer.setAdapter(mAdapter);
    }
    private void initView(View view){
        icStateSound = view.findViewById(R.id.icStateSound);
        seekBar = view.findViewById(R.id.seekBar);
        questionText = view.findViewById(R.id.questionText);
        recyclerViewAnswer = view.findViewById(R.id.recyclerViewAnswer);
        btnReview = view.findViewById(R.id.btnReview);
        btnRetry = view.findViewById(R.id.btnRetry);
        headerStatus = view.findViewById(R.id.headerStatus);
        headerStatusText = view.findViewById(R.id.headerStatusText);
        icHeaderStatus = view.findViewById(R.id.icHeaderStatus);
        constraintQuestion = view.findViewById(R.id.constraintQuestion);
        minuteText = view.findViewById(R.id.minuteText);
        statusText = view.findViewById(R.id.statusText);
        statusResultText = view.findViewById(R.id.statusResultText);
        icStatusResult = view.findViewById(R.id.icStatusResult);
        resultView = view.findViewById(R.id.resultView);
        cardViewStatus = view.findViewById(R.id.cardViewStatus);
    }
}
