package com.android.wnf;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityMusic extends AppCompatActivity {
    private ImageView icStateSound;
    private SeekBar seekBar;
    private MediaPlayer player;
    private boolean isPlaying = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        icStateSound = findViewById(R.id.icStateSound);
        seekBar = findViewById(R.id.seekBar);
        initializePlayer();
        initSeekBar();
        icStateSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player != null){
                    player.start();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(player.getCurrentPosition());
                            handler.postDelayed(this , 1000);
                        }
                    } , 0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null){
            player.reset();
            player.stop();
            player.release();
            player = null;
        }
    }

    private void initializePlayer(){
        player = MediaPlayer.create(this , R.raw.boruto_opening);
    }
    private void initSeekBar(){
        if(player != null)
            seekBar.setMax(player.getDuration());
    }
}
