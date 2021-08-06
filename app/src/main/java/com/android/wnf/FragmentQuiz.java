package com.android.wnf;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
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
import com.android.wnf.model.Answer;
import com.android.wnf.model.Quiz;
import com.android.wnf.model.QuizResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuiz extends Fragment {
    private ConstraintLayout headerStatus , constraintQuestion;
    private ImageView icStateSound , icHeaderStatus , icStatusResult;
    private SeekBar seekBar;
    private TeXView questionText;
    private AppCompatTextView minuteText , headerStatusText , statusText , statusResultText , scoreText;
    private AppCompatButton btnReview;
    private LinearLayout btnRetry;
    private CardView resultView , cardViewStatus;
    private RecyclerView recyclerViewAnswer;
    private Quiz quizData;
    private MediaPlayer player;
    private boolean isPlaying = false;
    private Handler handler = new Handler();
    private List<Answer> answerList;
    private AnswerAdapter mAdapter;
    private boolean isRegistered = false;
    private boolean isReview = false;
    private boolean isResult = false;
    private int parent_position = -1;
    private int model_position = -1;
    private int lastChoosePosition = -1;
    
    private Context contexts;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contexts = context;
    }

    public static FragmentQuiz newInstance(Quiz quiz_data , List<Answer> answerList , int parent_position , int model_position , boolean isReview , boolean isResult){
        FragmentQuiz quiz = new FragmentQuiz();
        Bundle bundle = new Bundle();
        bundle.putParcelable("quiz" , quiz_data);
        bundle.putParcelableArrayList("answer_list" , (ArrayList<Answer>) answerList);
        bundle.putBoolean("is_review" , isReview);
        bundle.putBoolean("is_result" , isResult);
        bundle.putInt("parent_position" , parent_position);
        bundle.putInt("model_position" , model_position);
        quiz.setArguments(bundle);
        return quiz;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizData = getArguments().getParcelable("quiz");
        answerList = getArguments().getParcelableArrayList("answer_list");
        isReview = getArguments().getBoolean("is_review" , false);
        isResult = getArguments().getBoolean("is_result" , false);
        parent_position = getArguments().getInt("parent_position" , 0);
        model_position = getArguments().getInt("model_position" , 0);
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
        if(!isRegistered){
            EventBus.getDefault().register(this);
            isRegistered = true;
        }
        initView(view);
        for(int i = 0; i < answerList.size(); i++){
            Log.d("answer_ansawer" , answerList.get(i).getAnswer());
        }
        if(quizData != null){
            if(!isReview){
                Log.d("question" , quizData.getQuestion());
                Log.d("is_result" , String.valueOf(quizData.getIsResult()));
                Log.d("is_answer" , String.valueOf(quizData.getIsAnswer()));
                if(quizData.getIsResult() == 0){
                    quizData.setAnswerList(new QuizData().getParentQuizList().get(parent_position).getQuizList().get(model_position).getAnswerList());
                }
                initializeQuestion();
                initializePlayer();
                initSeekBar();
                initAnswerList();
                icStateSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playSound();
                    }
                });
            } else {
                if(isResult){
                    Log.d("RESULT_PAGE" , "masuk sini daf");
                    constraintQuestion.setVisibility(View.GONE);
                    resultView.setVisibility(View.VISIBLE);
                    if(quizData.isSuccess() == 1){
                        icStatusResult.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_circle_green));
                        icStatusResult.setImageResource(R.drawable.ic_done_white);
                        statusResultText.setText(contexts.getResources().getString(R.string.result_success));
                    } else {
                        icStatusResult.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_circle_red));
                        icStatusResult.setImageResource(R.drawable.ic_close_white);
                        statusResultText.setText(contexts.getResources().getString(R.string.result_failed));
                    }
                    scoreText.setText(contexts.getResources().getString(R.string.score_text , String.valueOf(quizData.getScorePoints()) + "%" , "(" + String.valueOf(quizData.getScorePoints()) + " Points)"));

                    btnReview.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EventBus.getDefault().post(new ActivityQuizDetail.WrapperReview());
                        }
                    });
                    btnRetry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EventBus.getDefault().post(new ActivityQuizDetail.WrapperReset());
                        }
                    });
                } else {
                    initializeQuestion();
                    initializePlayer();
                    initSeekBar();
                    initAnswerList();
                    icStateSound.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            playSound();
                        }
                    });
                }
            }
        } else {
            seekBar.setEnabled(false);
            icStateSound.setEnabled(false);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WrapperResult result){
        constraintQuestion.setVisibility(View.GONE);
        resultView.setVisibility(View.VISIBLE);
        if(result.isSuccess){
            icStatusResult.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_circle_green));
            icStatusResult.setImageResource(R.drawable.ic_done_white);
            statusResultText.setText(contexts.getResources().getString(R.string.result_success));
        } else {
            icStatusResult.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_circle_red));
            icStatusResult.setImageResource(R.drawable.ic_close_white);
            statusResultText.setText(contexts.getResources().getString(R.string.result_failed));
        }
        scoreText.setText(contexts.getResources().getString(R.string.score_text , String.valueOf(result.score_points) + "%" , "(" + String.valueOf(result.score_points) + " Points)"));

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ActivityQuizDetail.WrapperReview());
            }
        });
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ActivityQuizDetail.WrapperReset());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeData data){
        quizData = data.quiz;
        lastChoosePosition = -1;
        initializePlayer();
        initializeQuestion();
        initAnswerList();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(TriggerChange data){
        Log.d("LAST_CHOOSE_POSITION" , String.valueOf(lastChoosePosition));
        if(lastChoosePosition != -1){
            boolean isTrue = quizData.getAnswerList().get(lastChoosePosition).isCorrect() == 1;
            if(isTrue){
                quizData.getAnswerList().get(lastChoosePosition).setResult(1);
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_status_correct));
                icHeaderStatus.setImageResource(R.drawable.ic_done_white);
                statusText.setText(contexts.getResources().getString(R.string.correct));
                headerStatusText.setText(contexts.getResources().getString(R.string.correct_badge));
            } else {
                quizData.getAnswerList().get(lastChoosePosition).setResult(0);
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_status_incorrect));
                icHeaderStatus.setImageResource(R.drawable.ic_close_white);
                statusText.setText(contexts.getResources().getString(R.string.incorrect));
                headerStatusText.setText(contexts.getResources().getString(R.string.incorrect_badge));
                for(int i = 0; i < quizData.getAnswerList().size(); i++){
                    if(quizData.getAnswerList().get(i).isCorrect() == 1){
                        quizData.getAnswerList().get(i).setResult(1);
                        break;
                    }
                }
            }
            for(int i = 0; i < quizData.getAnswerList().size(); i++){
                quizData.getAnswerList().get(i).setClickable(0);
            }
            EventBus.getDefault().post(new ActivityQuizDetail.WrapperCallback(model_position , quizData.getAnswerList()));
            mAdapter.notifyDataSetChanged();
        }
    }

    static class ChangeData {
        int model_position;
        Quiz quiz;
        ChangeData(int model_position , Quiz quiz){
            this.model_position = model_position;
            this.quiz = quiz;
        }
    }

    static class TriggerChange {
        int model_position;
        int lastChoosePosition;
        TriggerChange(int model_position , int lastChoosePosition){
            this.model_position = model_position;
            this.lastChoosePosition = lastChoosePosition;
        }
    }

    static class WrapperResult {
        boolean isSuccess;
        int score_points;
        WrapperResult(boolean isSuccess , int score_points){
            this.isSuccess = isSuccess;
            this.score_points = score_points;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(player != null){
            player.reset();
            player.release();
            player = null;
        }
    }
    private void initView(View view){
        headerStatus = view.findViewById(R.id.headerStatus);
        constraintQuestion = view.findViewById(R.id.constraintQuestion);
        icStateSound = view.findViewById(R.id.icStateSound);
        icHeaderStatus = view.findViewById(R.id.icHeaderStatus);
        icStatusResult = view.findViewById(R.id.icStatusResult);
        seekBar = view.findViewById(R.id.seekBar);
        questionText = view.findViewById(R.id.questionText);
        minuteText = view.findViewById(R.id.minuteText);
        headerStatusText = view.findViewById(R.id.headerStatusText);
        statusText = view.findViewById(R.id.statusText);
        statusResultText = view.findViewById(R.id.statusResultText);
        scoreText = view.findViewById(R.id.scoreText);
        btnReview = view.findViewById(R.id.btnReview);
        btnRetry = view.findViewById(R.id.btnRetry);
        resultView = view.findViewById(R.id.resultView);
        cardViewStatus = view.findViewById(R.id.cardViewStatus);
        recyclerViewAnswer = view.findViewById(R.id.recyclerViewAnswer);
    }
    private void initializeQuestion(){
        questionText.setLaTeX(quizData.getQuestion());
    }
    private void initializeStatus(){
        boolean isCheckedOne = false;
        int positionChecked = -1;
        for(int i = 0; i < quizData.getAnswerList().size(); i++){
            if(quizData.getAnswerList().get(i).isChecked() == 1){
                isCheckedOne = true;
                positionChecked = i;
                break;
            }
        }
        if(isCheckedOne){
            if(quizData.getAnswerList().get(positionChecked).isCorrect() == 1){
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_status_correct));
                icHeaderStatus.setImageResource(R.drawable.ic_done_white);
                statusText.setText(contexts.getResources().getString(R.string.correct));
                headerStatusText.setText(contexts.getResources().getString(R.string.correct_badge));
            } else {
                cardViewStatus.setVisibility(View.VISIBLE);
                headerStatus.setBackground(ContextCompat.getDrawable(contexts , R.drawable.bg_status_incorrect));
                icHeaderStatus.setImageResource(R.drawable.ic_close_white);
                statusText.setText(contexts.getResources().getString(R.string.incorrect));
                headerStatusText.setText(contexts.getResources().getString(R.string.incorrect_badge));
            }
        }
    }
    private void initializePlayer(){
        if(quizData != null){
            player = MediaPlayer.create(contexts , quizData.getSoundResource());
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(isPlaying){
                        if(player != null){
                            player.seekTo(0);
                            isPlaying = false;
                            player.stop();
                            player.release();
                            player = null;
                            initializePlayer();
                            seekBar.setProgress(0);
                            icStateSound.setImageResource(R.drawable.ic_play);
                            handler.removeCallbacksAndMessages(null);
                        }
                    }
                }
            });
        }
    }
    private void playSound(){
        if(isPlaying){
            handler.removeCallbacksAndMessages(null);
            isPlaying = false;
            player.pause();
            icStateSound.setImageResource(R.drawable.ic_play);
        } else {
            updateSeekBar();
            isPlaying = true;
            player.start();
            icStateSound.setImageResource(R.drawable.ic_pause);
        }
    }
    private void initSeekBar(){
        if(player != null)
            seekBar.setMax(player.getDuration());
        Log.d("playDuration" , String.valueOf(player.getDuration()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(isPlaying){
                    isPlaying = false;
                    if(player != null){
                        player.pause();
                        player.seekTo(seekBar.getProgress());
                    }
                    icStateSound.setImageResource(R.drawable.ic_play);
                } else {
                    player.seekTo(seekBar.getProgress());
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(!isPlaying){
                    if(player != null){
                        player.seekTo(seekBar.getProgress());
                        playSound();
                    }
                    icStateSound.setImageResource(R.drawable.ic_pause);
                }
            }
        });
    }
    private void initAnswerList(){
        Log.d("questionss" , quizData.getQuestion());
        for(int i = 0; i < quizData.getAnswerList().size(); i++){
            Answer data = quizData.getAnswerList().get(i);
            Log.d("answerss" , data.getAnswer());
        }
        recyclerViewAnswer.setHasFixedSize(true);
        recyclerViewAnswer.setItemAnimator(null);
        recyclerViewAnswer.setLayoutManager(new LinearLayoutManager(contexts , RecyclerView.VERTICAL , false));
        mAdapter = new AnswerAdapter(contexts , quizData.getAnswerList());
        mAdapter.setAnswerListener(new AnswerAdapter.AnswerListener() {
            @Override
            public void onChoose(int position) {
                if(lastChoosePosition != -1){
                    Log.d("onClick" , "masuk sini daf");
                    quizData.getAnswerList().get(lastChoosePosition).setChecked(0);
                    mAdapter.notifyItemChanged(lastChoosePosition);
                    lastChoosePosition = position;
                    quizData.getAnswerList().get(lastChoosePosition).setChecked(1);
                    mAdapter.notifyItemChanged(lastChoosePosition);
                } else {
                    Log.d("onClick" , "masuk sini rul");
                    lastChoosePosition = position;
                    quizData.getAnswerList().get(lastChoosePosition).setChecked(1);
                    mAdapter.notifyItemChanged(lastChoosePosition);
                }
                Log.d("getResult" , String.valueOf(quizData.getAnswerList().get(lastChoosePosition).getResult()));
                Log.d("getChecked" , String.valueOf(quizData.getAnswerList().get(lastChoosePosition).isChecked()));
                EventBus.getDefault().post(new ActivityQuizDetail.WrapperPosition(model_position , lastChoosePosition , quizData.getAnswerList()));
            }
        });
        recyclerViewAnswer.setAdapter(mAdapter);
        for(int i = 0; i < quizData.getAnswerList().size(); i++){
            if(quizData.getAnswerList().get(i).isChecked() == 1){
                EventBus.getDefault().post(new ActivityQuizDetail.WrapperPosition(model_position , i , quizData.getAnswerList()));
                break;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResumeFrag" , "onResume()");
        initializeStatus();
    }

    private void updateSeekBar(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(player != null){
                    seekBar.setProgress(player.getCurrentPosition());
                    handler.postDelayed(this , 1000);
                }
            }
        } , 0);
    }
}
