package com.android.wnf;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.adapter.AnswerAdapter;
import com.android.wnf.custom_widget.TeXView;
import com.android.wnf.model.Quiz;

public class ActivityQuizDetail extends AppCompatActivity {
    private ImageView icStateSound;
    private TeXView questionText;
    private SeekBar seekBar;
    private LinearLayout editLayout;
    private MediaPlayer player;
    private boolean isPlaying = false;
    private Handler handler = new Handler();
    private Quiz quiz = null;
    private RecyclerView recyclerViewAnswer;
    private AnswerAdapter mAdapter;
    private int lastCheckedPosition = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        quiz = getIntent().getParcelableExtra("quiz");
        icStateSound = findViewById(R.id.icStateSound);
        seekBar = findViewById(R.id.seekBar);
        questionText = findViewById(R.id.questionText);
        editLayout = findViewById(R.id.editLayout);

        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(quiz != null){
            initializeQuestion();
            initializePlayer();
            initSeekBar();
            initializeAnswer();
            icStateSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playSound();
                }
            });
        } else {
            seekBar.setEnabled(false);
            icStateSound.setEnabled(false);
        }
    }

    private void initializeQuestion(){
        questionText.setLaTeX(quiz.getQuestion());
    }

    private void initializeAnswer(){
        recyclerViewAnswer = findViewById(R.id.recyclerViewAnswer);
        recyclerViewAnswer.setHasFixedSize(true);
        recyclerViewAnswer.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAnswer.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        mAdapter = new AnswerAdapter(quiz.getAnswerList());
        mAdapter.setAnswerListener(new AnswerAdapter.AnswerListener() {
            @Override
            public void onChoose(int position) {
                if(lastCheckedPosition != -1){
                    quiz.getAnswerList().get(lastCheckedPosition).setChecked(false);
                    mAdapter.notifyItemChanged(lastCheckedPosition);
                }
                lastCheckedPosition = position;
                quiz.getAnswerList().get(lastCheckedPosition).setChecked(true);
                mAdapter.notifyItemChanged(lastCheckedPosition);
            }
        });
        recyclerViewAnswer.setAdapter(mAdapter);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.stop();
            player = null;
        }
        handler.removeCallbacksAndMessages(null);
    }

    private void initializePlayer(){
        if(quiz != null){
            player = MediaPlayer.create(this , quiz.getSoundResource());
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
    private void initSeekBar(){
        if(player != null)
            seekBar.setMax(player.getDuration());
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
