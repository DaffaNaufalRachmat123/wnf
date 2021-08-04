package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.wnf.model.Materi;
import com.android.wnf.model.Materis;
import com.android.wnf.model.ParentMateri;
import com.android.wnf.model.ParentMateris;
import com.android.wnf.model.SubMateris;
import com.android.wnf.model.SubMaterisData;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    private AppCompatButton btnBulat, btnPecahan, btnQuiz, btnTentang;
    private MateriData materiData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        materiData = new MateriData();
        btnBulat = findViewById(R.id.btnBulat);
        btnPecahan = findViewById(R.id.btnPecahan);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnTentang = findViewById(R.id.btnTentang);

        btnBulat.setOnClickListener(new View.OnClickListener() {
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

        btnBulat.setText(new MateriData().getParentMateriList().get(0).getTitle());
        btnPecahan.setText(new MateriData().getParentMateriList().get(1).getTitle());

        btnQuiz.setOnClickListener(new View.OnClickListener() {
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
    }
    private void startMateri(int position){
        Intent intent = new Intent(this , ActivityMateri.class);
        intent.putExtra("parent_materi" , new MateriData().getParentMateriList().get(position));
        startActivity(intent);
    }
}
