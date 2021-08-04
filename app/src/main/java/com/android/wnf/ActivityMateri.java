package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.android.wnf.model.Materis;
import com.android.wnf.model.ParentMateri;
import com.android.wnf.model.ParentMateris;
import com.android.wnf.model.SubMateri;
import com.android.wnf.model.SubMateris;

import java.util.ArrayList;
import java.util.List;

public class ActivityMateri extends AppCompatActivity {
    private AppCompatTextView titleText;
    private RecyclerView recyclerViewMateri;
    private ImageView icHome;
    private MateriAdapter materiAdapter;
    private ParentMateris parentMateri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        parentMateri = getIntent().getParcelableExtra("parent_materi");
        titleText = findViewById(R.id.titleText);
        recyclerViewMateri = findViewById(R.id.recyclerViewMateri);
        icHome = findViewById(R.id.icHome);

        titleText.setText(parentMateri.getTitle());
        setupMateriAdapter(parentMateri.getMaterisList());
        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setupMateriAdapter(List<Materis> materiList){
        materiAdapter = new MateriAdapter(this , materiList);
        materiAdapter.setMateriClickListener(new MateriAdapter.MateriClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent;
                if(parentMateri.getMaterisList().get(position).getIsSubMateri() == 1) {
                    intent = new Intent(getApplicationContext(), ActivitySubMateri.class);
                    intent.putExtra("materi", parentMateri.getMaterisList().get(position));
                } else {
                    intent = new Intent(getApplicationContext(), ActivityParentMateri.class);
                    intent.putExtra("is_sub_materi" , false);
                    intent.putParcelableArrayListExtra("sub_materi_list" , (ArrayList<SubMateris>)parentMateri.getMaterisList().get(position).getSubMaterisList());
                }
                startActivity(intent);
            }
        });
        recyclerViewMateri.setHasFixedSize(true);
        recyclerViewMateri.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMateri.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        recyclerViewMateri.setAdapter(materiAdapter);
    }
}
