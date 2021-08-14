package com.android.wnf;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.wnf.model.Materi;
import com.android.wnf.model.Materis;
import com.android.wnf.model.ParentMateri;
import com.android.wnf.model.ParentMateris;
import com.android.wnf.model.SubMateris;
import com.android.wnf.model.SubMaterisData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    private AppCompatButton btnBulat, btnPecahan, btnQuiz, btnTentang;
    private MateriData materiData;
    private LinearLayout bulatArea , pecahanArea , quizArea , tentangArea;
    private ImageView icSound;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private void initializeMusic(){
        mediaPlayer = MediaPlayer.create(this , R.raw.solo_guitar);
        mediaPlayer.setLooping(true);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                isPlaying = false;
                icSound.setImageResource(R.drawable.ic_sound_off);
            }
        });
    }
    private void playMusic(){
        mediaPlayer.start();
    }
    private void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer = null;
        icSound.setImageResource(R.drawable.ic_sound_off);
        isPlaying = false;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EventBus.getDefault().register(this);
        materiData = new MateriData();
        btnBulat = findViewById(R.id.btnBulat);
        btnPecahan = findViewById(R.id.btnPecahan);
        icSound = findViewById(R.id.icSound);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnTentang = findViewById(R.id.btnTentang);
        bulatArea = findViewById(R.id.bulatArea);
        pecahanArea = findViewById(R.id.pecahanArea);
        quizArea = findViewById(R.id.quizArea);
        tentangArea = findViewById(R.id.tentangArea);
        initializeMusic();
        playMusic();
        isPlaying = true;
        icSound.setImageResource(R.drawable.ic_sound_on);
        btnBulat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(0);
            }
        });
        bulatArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(0);
            }
        });

        btnPecahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(1);
            }
        });
        pecahanArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(1);
            }
        });

        btnBulat.setText(new MateriData().getParentMateriList().get(0).getTitle());
        btnPecahan.setText(new MateriData().getParentMateriList().get(1).getTitle());

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuiz.class));
            }
        });
        quizArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuiz.class));
            }
        });

        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityAbout.class));
            }
        });
        tentangArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityAbout.class));
            }
        });
        icSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    if(mediaPlayer != null)
                        mediaPlayer.pause();
                    icSound.setImageResource(R.drawable.ic_sound_off);
                    isPlaying = false;
                } else {
                    if(mediaPlayer == null)
                        initializeMusic();
                    playMusic();
                    icSound.setImageResource(R.drawable.ic_sound_on);
                    isPlaying = true;
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(StopSound data){
        stopMusic();
    }

    static class StopSound {}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isPlaying){
            mediaPlayer.stop();

        }
        EventBus.getDefault().unregister(this);
    }

    private void startMateri(int position){
        Intent intent = new Intent(this , ActivityMateri.class);
        intent.putExtra("parent_materi" , new MateriData().getParentMateriList().get(position));
        startActivity(intent);
    }
}
