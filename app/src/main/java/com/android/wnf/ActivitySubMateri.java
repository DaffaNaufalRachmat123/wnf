package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.wnf.adapter.MateriAdapter;
import com.android.wnf.adapter.SubMateriAdapter;
import com.android.wnf.model.Materi;
import com.android.wnf.model.SubMateri;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubMateri extends AppCompatActivity {
    private AppCompatTextView titleText;
    private RecyclerView recyclerViewSubMateri;
    private ImageView icHome , icBack;
    private String subTitleMateri;
    private SubMateriAdapter subMateriAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_materi);
        subTitleMateri = getIntent().getStringExtra("sub_title_materi");
        titleText = findViewById(R.id.titleText);
        recyclerViewSubMateri = findViewById(R.id.recyclerViewSubMateri);
        icHome = findViewById(R.id.icHome);
        icBack = findViewById(R.id.icBack);

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setupMateriAdapter(new String[]{"DAFFA" , "NAUFAL" , "RACHMAT"});
    }
    private void setupMateriAdapter(String[] stringArray){
        List<SubMateri> materiList = new ArrayList<>();
        for(int i = 0; i < stringArray.length; i++)
            materiList.add(new SubMateri(i , stringArray[i]));
        subMateriAdapter = new SubMateriAdapter(this , materiList);
        subMateriAdapter.setMateriClickListener(new SubMateriAdapter.MateriClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(getApplicationContext() , ActivityParentMateri.class));
            }
        });
        recyclerViewSubMateri.setHasFixedSize(true);
        recyclerViewSubMateri.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSubMateri.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        recyclerViewSubMateri.setAdapter(subMateriAdapter);
    }
}
