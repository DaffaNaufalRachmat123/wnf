package com.android.wnf;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityQuizDetail extends AppCompatActivity implements Player.EventListener {
    private AppCompatTextView questionText , minuteText;
    private ImageView icStateSound , icSound;
    private SeekBar seekBar;
    private boolean isPlaying = false;
    private int currentVolume = 0;
    private Handler mHandler = new Handler();
    private SimpleExoPlayer exoPlayer;
    private boolean dragging = false;
    private void updateProgressBar() {
        long duration = exoPlayer == null ? 0 : exoPlayer.getDuration();
        long position = exoPlayer == null ? 0 : exoPlayer.getCurrentPosition();
        if (!dragging) {
            //seekBar.setProgress(progressBarValue(position));
        }
        long bufferedPosition = exoPlayer == null ? 0 : exoPlayer.getBufferedPosition();
        //seekBar.setSecondaryProgress(progressBarValue(bufferedPosition));
        // Remove scheduled updates.
        mHandler.removeCallbacks(updateProgressAction);
        // Schedule an update if necessary.
        int playbackState = exoPlayer == null ? Player.STATE_IDLE : exoPlayer.getPlaybackState();
        if (playbackState != Player.STATE_IDLE && playbackState != Player.STATE_ENDED) {
            long delayMs;
            if (exoPlayer.getPlayWhenReady() && playbackState == Player.STATE_READY) {
                delayMs = 1000 - (position % 1000);
                if (delayMs < 200) {
                    delayMs += 1000;
                }
            } else {
                delayMs = 1000;
            }
            mHandler.postDelayed(updateProgressAction, delayMs);
        }
    }

    private final Runnable updateProgressAction = new Runnable() {
        @Override
        public void run() {
            updateProgressBar();
        }
    };
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
        if(exoPlayer != null){
            exoPlayer.stop(true);
            exoPlayer.release();
            exoPlayer = null;
        }
        resetVolume();
    }

    private void initializePlayer(){
        exoPlayer = new SimpleExoPlayer.Builder(this).build();
        exoPlayer.addListener(this);
        if(getMediaSourceFromRaw() != null){
            exoPlayer.setMediaSource(getMediaSourceFromRaw());
            exoPlayer.prepare();
            Toast.makeText(ActivityQuizDetail.this , String.valueOf(exoPlayer.getDuration()) , Toast.LENGTH_LONG).show();
        }
    }
    private MediaSource getMediaSourceFromRaw(){
        RawResourceDataSource rawSource = new RawResourceDataSource(this);
        try {
            rawSource.open(new DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.boruto_opening)));
            DefaultDataSourceFactory dataSource = new DefaultDataSourceFactory(this , Util.getUserAgent(this , "ExoAudio"));
            MediaItem mediaItem = MediaItem.fromUri(rawSource.getUri());
            return new ProgressiveMediaSource.Factory(dataSource).createMediaSource(mediaItem);
        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void setSeekBar(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(exoPlayer != null){
                    exoPlayer.seekTo(progress);;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(exoPlayer != null){
                    exoPlayer.pause();
                    icStateSound.setImageResource(R.drawable.ic_play);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(exoPlayer != null){
                    exoPlayer.setPlayWhenReady(true);
                    exoPlayer.seekTo(exoPlayer.getCurrentPosition());
                    icStateSound.setImageResource(R.drawable.ic_pause);
                }
            }
        });
    }
    private void play(){
        if(!isPlaying){
            if(exoPlayer != null){
                maxVolume();
                exoPlayer.setPlayWhenReady(true);
                isPlaying = true;
                icStateSound.setImageResource(R.drawable.ic_pause);
            }
        } else {
            if(exoPlayer != null){
                resetVolume();
                exoPlayer.pause();
                isPlaying = false;
                icStateSound.setImageResource(R.drawable.ic_play);
            }
        }
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        updateProgressBar();
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
