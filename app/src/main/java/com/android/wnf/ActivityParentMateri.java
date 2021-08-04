package com.android.wnf;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.wnf.model.SubMateri;
import com.android.wnf.model.SubMateriData;
import com.android.wnf.model.SubMateris;
import com.android.wnf.model.SubMaterisData;

import java.util.List;

public class ActivityParentMateri extends AppCompatActivity {
    private List<SubMaterisData> subMateriDataList;
    private List<SubMateris> subMaterisList;
    private boolean isSubMateri = false;
    private ViewPager viewPager;
    private ImageView icBack , icHome , icNext;
    private int currentPosition = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_materi);
        isSubMateri = getIntent().getBooleanExtra("is_sub_materi" , false);
        viewPager = findViewById(R.id.viewPager);
        icBack = findViewById(R.id.icBack);
        icHome = findViewById(R.id.icHome);
        icNext = findViewById(R.id.icNext);

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition > 0){
                    currentPosition -= 1;
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        icNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSubMateri){
                    if(currentPosition < subMaterisList.size() - 1){
                        icBack.setVisibility(View.VISIBLE);
                        icNext.setVisibility(View.VISIBLE);
                        currentPosition += 1;
                        viewPager.setCurrentItem(currentPosition);
                    } else if(currentPosition == 0) {
                        icBack.setVisibility(View.INVISIBLE);
                    } else if(currentPosition == subMaterisList.size()){
                        icNext.setVisibility(View.INVISIBLE);
                    }
                } else {
                    if(currentPosition < subMateriDataList.size() - 1){
                        icBack.setVisibility(View.VISIBLE);
                        icNext.setVisibility(View.VISIBLE);
                        currentPosition += 1;
                        viewPager.setCurrentItem(currentPosition);
                    } else if(currentPosition == 0) {
                        icBack.setVisibility(View.INVISIBLE);
                    } else if(currentPosition == subMateriDataList.size()){
                        icNext.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        if(!isSubMateri)
            subMaterisList = getIntent().getParcelableArrayListExtra("sub_materi_list");
        else
            subMateriDataList = getIntent().getParcelableArrayListExtra("sub_materi_data_list");

        MateriPagerAdapter adapter = new MateriPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
    class MateriPagerAdapter extends FragmentPagerAdapter {
        public MateriPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public int getCount() {
            if(isSubMateri)
                return subMateriDataList.size();
            else
                return subMaterisList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(!isSubMateri)
                return FragmentMateri.newInstance(subMaterisList.get(position).getTitle() , "" , subMaterisList.get(position).getImageResource());
            else
                return FragmentMateri.newInstance(subMateriDataList.get(position).getTitle() , subMateriDataList.get(position).getMateri() , subMateriDataList.get(position).getImageResource());
        }
    }
}
