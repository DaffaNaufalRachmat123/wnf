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
import com.android.wnf.model.Materis;
import com.android.wnf.model.SubMateri;
import com.android.wnf.model.SubMateris;
import com.android.wnf.model.SubMaterisData;

import java.util.ArrayList;
import java.util.List;

public class ActivitySubMateri extends AppCompatActivity {
    private AppCompatTextView titleText;
    private RecyclerView recyclerViewSubMateri;
    private ImageView icHome , icBack;
    private SubMateriAdapter subMateriAdapter;
    private Materis materi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_materi);
        materi = getIntent().getParcelableExtra("materi");
        titleText = findViewById(R.id.titleText);
        titleText.setText(materi.getTitle());
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
        setupMateriAdapter(materi.getSubMaterisList());
    }
    private void setupMateriAdapter(List<SubMateris> materiList){
        subMateriAdapter = new SubMateriAdapter(this , materiList);
        subMateriAdapter.setMateriClickListener(new SubMateriAdapter.MateriClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext() , ActivityParentMateri.class);
                intent.putExtra("is_sub_materi" , true);
                intent.putParcelableArrayListExtra("sub_materi_data_list" , (ArrayList<SubMaterisData>)materiList.get(position).getSubMaterisDataList());
                startActivity(intent);
            }
        });
        recyclerViewSubMateri.setHasFixedSize(true);
        recyclerViewSubMateri.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSubMateri.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        recyclerViewSubMateri.setAdapter(subMateriAdapter);
    }
}
