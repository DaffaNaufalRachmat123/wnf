package com.android.wnf;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityQuizDetail extends AppCompatActivity {
    private AppCompatTextView questionText , minuteText;
    private ImageView icStateSound , icSound;
    private SeekBar seekBar;
    private MediaPlayer player;
    private boolean isPlaying = false;
    private int currentVolume = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        initView();
        initializePlayer();
        setSeekBar();
        icStateSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.start();
            player.reset();
            player.release();
            player = null;
        }
        resetVolume();
    }

    private void initializePlayer(){
        player = MediaPlayer.create(this , R.raw.boruto_opening);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(player != null){
                    player.stop();
                    player.reset();
                    player.release();
                    player = null;
                    isPlaying = false;
                    resetVolume();
                    seekBar.setProgress(0);
                    icStateSound.setImageResource(R.drawable.ic_play);
                    initializePlayer();
                }
            }
        });
        Toast.makeText(this , String.valueOf(player.getDuration()) , Toast.LENGTH_LONG).show();
        new Thread(){
            @Override
            public void run() {

            }
        };
    }
    private void setSeekBar(){
        seekBar.setMax(player.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(player != null){
                    player.seekTo(progress);;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(player != null){
                    player.pause();
                    icStateSound.setImageResource(R.drawable.ic_play);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(player != null){
                    player.start();
                    icStateSound.setImageResource(R.drawable.ic_pause);
                }
            }
        });
        /*new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(player != null){
                    seekBar.setProgress(player.getCurrentPosition());
                }
            }
        } , 0 , 100);*/
    }
    private void play(){
        if(!isPlaying){
            if(player != null){
                maxVolume();
                player.start();
                isPlaying = true;
                icStateSound.setImageResource(R.drawable.ic_pause);
            }
        } else {
            if(player != null){
                resetVolume();
                player.pause();
                isPlaying = false;
                icStateSound.setImageResource(R.drawable.ic_play);
            }
        }
    }
    private void maxVolume(){
        AudioManager manager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        currentVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC , manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) , 0);
    }
    private void resetVolume(){
        AudioManager manager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC , currentVolume , 0);
    }
    private void initView(){
        questionText = findViewById(R.id.questionText);
        minuteText = findViewById(R.id.minuteText);
        icStateSound = findViewById(R.id.icStateSound);
        icSound = findViewById(R.id.icSound);
        seekBar = findViewById(R.id.seekBar);
    }
}
