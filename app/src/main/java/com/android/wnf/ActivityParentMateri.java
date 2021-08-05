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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_materi);
        isSubMateri = getIntent().getBooleanExtra("is_sub_materi" , false);
        viewPager = findViewById(R.id.viewPager);
        icBack = findViewById(R.id.icBack);
        icHome = findViewById(R.id.icHome);
        icNext = findViewById(R.id.icNext);

        icHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSubMateri){
                    if(viewPager.getCurrentItem() > 0){
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                } else {
                    if(viewPager.getCurrentItem() > 0){
                        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    }
                }
            }
        });

        icNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSubMateri){
                    if(viewPager.getCurrentItem() < subMaterisList.size() - 1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                } else {
                    if(viewPager.getCurrentItem() < subMateriDataList.size() - 1){
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                }
            }
        });

        if(!isSubMateri)
            subMaterisList = getIntent().getParcelableArrayListExtra("sub_materi_list");
        else
            subMateriDataList = getIntent().getParcelableArrayListExtra("sub_materi_data_list");

        if(!isSubMateri){
            if(subMaterisList.size() > 1){
                icBack.setVisibility(View.INVISIBLE);
                icNext.setVisibility(View.VISIBLE);
            } else if(subMaterisList.size() == 1){
                icBack.setVisibility(View.INVISIBLE);
                icNext.setVisibility(View.INVISIBLE);
            }
        } else {
            if(subMateriDataList.size() > 1){
                icBack.setVisibility(View.INVISIBLE);
                icNext.setVisibility(View.VISIBLE);
            } else if(subMateriDataList.size() == 1){
                icBack.setVisibility(View.INVISIBLE);
                icNext.setVisibility(View.INVISIBLE);
            }
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(!isSubMateri){
                    if(viewPager.getCurrentItem() > 0 && viewPager.getCurrentItem() < subMaterisList.size() - 1){
                        Log.d("viewPager" , "masuk sini");
                        icBack.setVisibility(View.VISIBLE);
                        icNext.setVisibility(View.VISIBLE);
                    } else if(viewPager.getCurrentItem() >= 0 && viewPager.getCurrentItem() < subMaterisList.size() - 1){
                        Log.d("viewPager" , "masuk sini 1");
                        icNext.setVisibility(View.VISIBLE);
                        icBack.setVisibility(View.INVISIBLE);
                    } else {
                        if(viewPager.getCurrentItem() == 0){
                            Log.d("viewPager" , "masuk sini 2");
                            icBack.setVisibility(View.INVISIBLE);
                        } else if(viewPager.getCurrentItem() == subMaterisList.size() - 1){
                            Log.d("viewPager" , "masuk sini 3");
                            icBack.setVisibility(View.VISIBLE);
                            icNext.setVisibility(View.INVISIBLE);
                        }
                    }
                } else {
                    if(viewPager.getCurrentItem() > 0 && viewPager.getCurrentItem() < subMateriDataList.size() - 1){
                        icBack.setVisibility(View.VISIBLE);
                        icNext.setVisibility(View.VISIBLE);
                    } else if(viewPager.getCurrentItem() >= 0 && viewPager.getCurrentItem() < subMateriDataList.size() - 1){
                        icNext.setVisibility(View.VISIBLE);
                        icBack.setVisibility(View.INVISIBLE);
                    } else {
                        if(viewPager.getCurrentItem() == 0){
                            icBack.setVisibility(View.INVISIBLE);
                        } else if(viewPager.getCurrentItem() == subMateriDataList.size() - 1){
                            icNext.setVisibility(View.INVISIBLE);
                            icBack.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
