package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ActivityHome extends AppCompatActivity {
    private AppCompatButton btnBulat, btnPecahan, btnQuiz, btnTentang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnBulat = findViewById(R.id.btnBulat);
        btnPecahan = findViewById(R.id.btnPecahan);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnTentang = findViewById(R.id.btnTentang);

        btnBulat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(getString(R.string.materi_bilangan_bulat));
            }
        });

        btnPecahan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMateri(getString(R.string.materi_bilangan_pecahan));
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ActivityQuiz.class));
            }
        });

        btnTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void startMateri(String title){
        Intent intent = new Intent(this , ActivityMateri.class);
        intent.putExtra("title_materi" , title);
        startActivity(intent);
    }
}
