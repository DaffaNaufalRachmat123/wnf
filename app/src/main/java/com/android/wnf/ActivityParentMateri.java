package com.android.wnf;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.wnf.model.Materi;

import java.util.ArrayList;
import java.util.List;

public class ActivityParentMateri extends AppCompatActivity {
    private List<Materi> materiList;
    private ViewPager viewPager;
    private ImageView icBack , icHome , icNext;
    private int currentPosition = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_materi);
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
                if(currentPosition < materiList.size() - 1){
                    currentPosition += 1;
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });

        initMateriList();
        MateriPagerAdapter adapter = new MateriPagerAdapter(getSupportFragmentManager() , materiList);
        viewPager.setAdapter(adapter);
    }
    private void initMateriList(){
        materiList = new ArrayList<>();
        materiList.add(new Materi(0 , "Perkalian" , "\\textbf{Perkalian merupakan penjumlahan secara\\\\ berulang-ulang. Contoh : }\n" +
                "\\\\\\textbf{Berlaku : }\n" +
                "\\\\\\quad\\quad\\bullet \\quad \\quad \\textbf{a x b}"));
        materiList.add(new Materi(1 , "Perkalian" , getString(R.string.example)));
        materiList.add(new Materi(2 , "Perkalian" , getString(R.string.example)));
        materiList.add(new Materi(3 , "Perkalian" , getString(R.string.example)));
    }
    class MateriPagerAdapter extends FragmentPagerAdapter {
        private List<Materi> materiList = new ArrayList<>();
        public MateriPagerAdapter(FragmentManager manager , List<Materi> materiList){
            super(manager);
            this.materiList = materiList;
        }

        @Override
        public int getCount() {
            return materiList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return FragmentMateri.newInstance(materiList.get(position).getTitleMateri() , materiList.get(position).getMateri());
        }
    }
}
