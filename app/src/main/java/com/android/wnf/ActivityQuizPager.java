package com.android.wnf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.wnf.adapter.QuestionAdapter;
import com.android.wnf.event_class.UpdateCallback;
import com.android.wnf.event_class.UpdatePosition;
import com.android.wnf.interfaces.QuizListener;
import com.android.wnf.model.ParentQuiz;
import com.android.wnf.model.Quiz;
import com.google.android.material.snackbar.Snackbar;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class ActivityQuizPager extends AppCompatActivity {
    private ViewPager viewPagerQuestion , viewPagerReview;
    private CardView btnSubmit , btnPrev , btnNext;
    private AppCompatTextView submitText , cardTitleText , badgeText;
    private ImageView icMenu , icCloseBlue;
    private ExpandableLayout expandableNav;
    private RecyclerView recyclerViewNav;
    private ParentQuiz parentQuiz;
    private int parent_position;
    private int lastChoosePosition = -1;
    private boolean isReview = false;
    private boolean isExpanded = false;
    private QuestionPagerAdapter adapter;
    private boolean isResultAdded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        parentQuiz = getIntent().getParcelableExtra("parent_quiz");
        parent_position = getIntent().getIntExtra("quiz_position" , 0);
        initView();
        icMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded){
                    isExpanded = false;
                    icMenu.setImageResource(R.drawable.ic_menu_blue);
                    expandableNav.collapse();
                } else {
                    isExpanded = true;
                    icMenu.setImageResource(R.drawable.ic_close_blue);
                    expandableNav.expand();
                }
            }
        });
        adapter = new QuestionPagerAdapter(getSupportFragmentManager() , parentQuiz);
        viewPagerQuestion.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(parentQuiz.getQuizList().get(position).getId() == -999){
                    btnSubmit.setVisibility(View.GONE);
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    btnPrev.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            QuizData data = new QuizData();
                            int next_pos = parent_position + 1;
                            if(next_pos < data.getParentQuizList().size() - 1){
                                Intent intent = new Intent(ActivityQuizPager.this , ActivityQuizPager.class);
                                intent.putExtra("parent_quiz" , new QuizData().getParentQuizList().get(next_pos));
                                intent.putExtra("quiz_position" , next_pos);
                                startActivity(intent);
                            }
                        }
                    });
                } else {
                    btnSubmit.setVisibility(View.VISIBLE);
                    btnPrev.setVisibility(View.GONE);
                    btnNext.setVisibility(View.GONE);
                    Log.d("ON_PAGE_SELECTED" , String.valueOf(position));
                    boolean atleast_one = false;
                    for(int i = 0; i < parentQuiz.getQuizList().get(position).getAnswerList().size(); i++){
                        if(parentQuiz.getQuizList().get(position).getAnswerList().get(i).isChecked() == 1){
                            atleast_one = true;
                            break;
                        }
                    }
                    if(atleast_one)
                        initBtnNext();
                    else
                        initBtnSubmit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerQuestion.setAdapter(adapter);
        initNavigation();
        initBtnSubmit();
    }

    private void initBtnSubmit(){
        submitText.setText("SUBMIT");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastChoosePosition != -1){
                    Log.d("choose_pos" , String.valueOf(lastChoosePosition));
                    EventBus.getDefault().post(new FragmentQuizDetail.TriggerChange());
                    initBtnNext();
                } else {
                    Snackbar.make(v , "Harap pilih jawaban dulu" , Snackbar.LENGTH_SHORT);
                }
            }
        });
    }

    private void initBtnNext(){
        submitText.setText("NEXT");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isReview){
                    int count = viewPagerQuestion.getCurrentItem();
                    if(count < parentQuiz.getQuizList().size() - 1){
                        viewPagerQuestion.setCurrentItem(count + 1);
                        lastChoosePosition = -1;
                        int next_count = count + 1;
                        boolean atleast_one = false;
                        for(int i = 0; i < parentQuiz.getQuizList().get(next_count).getAnswerList().size(); i++){
                            if(parentQuiz.getQuizList().get(next_count).getAnswerList().get(i).isChecked() == 1){
                                atleast_one = true;
                                break;
                            }
                        }
                        if(atleast_one){
                            initBtnNext();
                        } else {
                            initBtnSubmit();
                        }
                    } else {
                        if(!isResultAdded){
                            addQuizResultPage();
                            adapter.notifyDataSetChanged();
                            viewPagerQuestion.setCurrentItem(viewPagerQuestion.getCurrentItem() + 1);
                        }
                    }
                }
            }
        });
    }

    private void addQuizResultPage(){
        int score_points = 0;
        for(int i = 0; i < parentQuiz.getQuizList().size(); i++){
            if(parentQuiz.getQuizList().get(i).getIsAnswer() == 1 &&
                parentQuiz.getQuizList().get(i).getIsResult() == 1){
                score_points += 1;
            }
        }
        int minimum_score = 80;
        score_points *= 20;
        int isSuccess = score_points < minimum_score ? 0 : 1;
        parentQuiz.getQuizList().add(new Quiz(-999 , "" , 0 , new ArrayList() , 0 , score_points ,isSuccess,0));
    }

    private void initNavigation(){
        recyclerViewNav.setHasFixedSize(true);
        recyclerViewNav.setItemAnimator(null);
        recyclerViewNav.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        QuestionAdapter qAdapter = new QuestionAdapter(this, parentQuiz.getQuizList(), new QuestionAdapter.QuestionListener() {
            @Override
            public void onClick(int position) {
                if(isExpanded){
                    isExpanded = false;
                    if(isReview){
                        viewPagerReview.setCurrentItem(position);
                    } else {
                        viewPagerQuestion.setCurrentItem(position);
                    }
                    icMenu.setImageResource(R.drawable.ic_menu_blue);
                    expandableNav.collapse();
                }
            }
        });
        recyclerViewNav.setAdapter(qAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdatePosition data){
        lastChoosePosition = data.lastChoosePosition;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateCallback data){
        parentQuiz.getQuizList().get(data.model_position).setAnswer(data.isAnswer);
        parentQuiz.getQuizList().get(data.model_position).setIsResult(data.isResult);
    }

    static class ReviewPosition {
        public int model_position;
        public ReviewPosition(int model_position){
            this.model_position = model_position;
        }
    }

    private void initView(){
        viewPagerQuestion = findViewById(R.id.viewPagerQuestion);
        viewPagerReview = findViewById(R.id.viewPagerReview);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        submitText = findViewById(R.id.submitText);
        cardTitleText = findViewById(R.id.cardTitleText);
        badgeText = findViewById(R.id.badgeText);
        icMenu = findViewById(R.id.icMenu);
        icCloseBlue = findViewById(R.id.icCloseBlue);
        expandableNav = findViewById(R.id.expandableNav);
        recyclerViewNav = findViewById(R.id.recyclerViewNav);
    }
    class QuestionPagerAdapter extends FragmentPagerAdapter {
        private ParentQuiz parentQuiz;
        public QuestionPagerAdapter(FragmentManager manager , ParentQuiz parentQuiz){
            super(manager);
            this.parentQuiz = parentQuiz;
        }

        @Override
        public int getCount() {
            return parentQuiz.getQuizList().size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(parentQuiz.getQuizList().get(position).getId() == -999){
                return FragmentQuizDetail.newInstance(parentQuiz.getQuizList().get(position) , parentQuiz.getQuizList().get(position).getAnswerList() , false , true , position, parent_position);
            } else {
                return FragmentQuizDetail.newInstance(parentQuiz.getQuizList().get(position) , parentQuiz.getQuizList().get(position).getAnswerList() , false , false, position, parent_position);
            }
        }
    }
}
