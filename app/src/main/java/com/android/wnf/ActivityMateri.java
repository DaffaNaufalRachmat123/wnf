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
import com.android.wnf.model.Materi;

import java.util.ArrayList;
import java.util.List;

public class ActivityMateri extends AppCompatActivity {
    private AppCompatTextView titleText;
    private RecyclerView recyclerViewMateri;
    private ImageView icHome;
    private String titleMateri;
    private MateriAdapter materiAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        titleMateri = getIntent().getStringExtra("title_materi");

        titleText = findViewById(R.id.titleText);
        recyclerViewMateri = findViewById(R.id.recyclerViewMateri);
        icHome = findViewById(R.id.icHome);

        titleText.setText(titleMateri);
        if(titleMateri.equalsIgnoreCase(getString(R.string.materi_bilangan_bulat)))
            setupMateriAdapter(getResources().getStringArray(R.array.bilangan_bulat));
        else if(titleMateri.equalsIgnoreCase(getString(R.string.materi_bilangan_pecahan)))
            setupMateriAdapter(getResources().getStringArray(R.array.bilangan_pecahan));

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setupMateriAdapter(String[] stringArray){
        List<Materi> materiList = new ArrayList<>();
        for(int i = 0; i < stringArray.length; i++)
            materiList.add(new Materi(i , stringArray[i]));
        materiAdapter = new MateriAdapter(this , materiList);
        materiAdapter.setMateriClickListener(new MateriAdapter.MateriClickListener() {
            @Override
            public void onClick(int position) {
                startActivity(new Intent(getApplicationContext() , ActivityParentMateri.class));
            }
        });
        recyclerViewMateri.setHasFixedSize(true);
        recyclerViewMateri.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMateri.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        recyclerViewMateri.setAdapter(materiAdapter);
    }
}
