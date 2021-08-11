package com.android.wnf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.wnf.adapter.QuestionAdapter;
import com.android.wnf.custom_widget.NonSwipeableViewPager;
import com.android.wnf.model.Answer;
import com.android.wnf.model.ParentQuiz;
import com.android.wnf.model.Quiz;
import com.android.wnf.model.QuizResult;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ActivityQuizDetail extends AppCompatActivity {
    private ConstraintLayout bgSubmit , badgePageText;
    private NonSwipeableViewPager viewPager;
    private ViewPager viewPagerReview;
    private CardView btnSubmit , btnPrev , btnNext;
    private AppCompatTextView submitText , cardTitleText , badgeText;
    private ParentQuiz parentQuiz;
    private ImageView icMenu , icCloseBlue;
    private int model_position = 0;
    private int lastChoosePosition = -1;
    private boolean isReviewQuiz = false;
    private List<QuizResult> resultsQuiz = new ArrayList<>();
    private List<Quiz> quizListReview = new ArrayList<>();
    private QuizPagerAdapter adapter;
    private ExpandableLayout expandableNav;
    private RecyclerView recyclerViewNav;
    private boolean isExpanded = false;
    private boolean isResultAdded = false;
    public static boolean isPlayingMusic = false;
    public static boolean isInitialized = false;
    private int quizPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);
        isReviewQuiz = getIntent().getBooleanExtra("is_review" , false);
        quizPosition = getIntent().getIntExtra("quiz_position" , 0);
        viewPager = findViewById(R.id.viewPagerQuestion);
        viewPagerReview = findViewById(R.id.viewPagerReview);
        icCloseBlue = findViewById(R.id.icCloseBlue);
        icMenu = findViewById(R.id.icMenu);
        expandableNav = findViewById(R.id.expandableNav);
        recyclerViewNav = findViewById(R.id.recyclerViewNav);
        btnSubmit = findViewById(R.id.btnSubmit);
        bgSubmit = findViewById(R.id.bgSubmit);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        submitText = findViewById(R.id.submitText);
        cardTitleText = findViewById(R.id.cardTitleText);
        badgePageText = findViewById(R.id.badgePageText);
        badgeText = findViewById(R.id.badgeText);
        if(!isReviewQuiz){
            viewPager.setOffscreenPageLimit(1);
            parentQuiz = getIntent().getParcelableExtra("parent_quiz");
            initializeQuizList();
            initializeNavigation();
            cardTitleText.setText(getResources().getString(R.string.title_quiz , "1" , String.valueOf(parentQuiz.getQuizList().size())));
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position <= parentQuiz.getQuizList().size() - 1){
                        btnSubmit.setVisibility(View.VISIBLE);
                        btnPrev.setVisibility(View.GONE);
                        btnNext.setVisibility(View.GONE);
                        int totalPage = 0;
                        if(isResultAdded)
                            totalPage = parentQuiz.getQuizList().size() - 1;
                        else
                            totalPage = parentQuiz.getQuizList().size();
                        cardTitleText.setText(getResources().getString(R.string.title_quiz , String.valueOf(position + 1) , String.valueOf(totalPage)));
                        model_position = position;
                        boolean isCheckedOne = false;
                        for(int i = 0; i < parentQuiz.getQuizList().get(model_position).getAnswerList().size(); i++){
                            if(parentQuiz.getQuizList().get(model_position).getAnswerList().get(i).isChecked() == 1 &&
                                parentQuiz.getQuizList().get(model_position).getAnswerList().get(i).getResult() != -1){
                                isCheckedOne = true;
                                lastChoosePosition = i;
                                break;
                            }
                        }
                        /*boolean isCheckedOne = parentQuiz.getQuizList().get(model_position).getLastChoosePosition() == -1;
                        lastChoosePosition = parentQuiz.getQuizList().get(model_position).getLastChoosePosition();
                        */
                        Log.d("ISCHECKEDONE" , String.valueOf(isCheckedOne));
                        Log.d("LAST_CHOOSE" , String.valueOf(lastChoosePosition));
                        if(isCheckedOne){
                            submitText.setText("NEXT");
                            initializeBtnNext();
                        } else {
                            if(parentQuiz.getQuizList().get(position).getId() != -999){
                                EventBus.getDefault().postSticky(new FragmentQuiz.PlaySound(parentQuiz.getQuizList().get(position).getId() , parentQuiz.getQuizList().get(position).getSoundResource()));
                            }
                            initializeBtnSubmit();
                        }
                    }

                    if(parentQuiz.getQuizList().get(position).getId() == -999){
                        cardTitleText.setText("Quiz Result");
                        btnSubmit.setVisibility(View.GONE);
                        btnPrev.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.VISIBLE);
                        btnPrev.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                                Log.d("prev_pos" , String.valueOf(viewPager.getCurrentItem()));
                            }
                        });
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean isOtherQuiz = false;
                                if(quizPosition + 1 < new QuizData().getParentQuizList().size())
                                    isOtherQuiz = true;
                                startActivity(new Intent(getApplicationContext() , ActivityQuizPage.class)
                                .putExtra("is_another_quiz" , isOtherQuiz)
                                .putExtra("number_quiz" , quizPosition + 1));
                            }
                        });
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
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
            if(parentQuiz.getQuizList().size() == 0){
                bgSubmit.setVisibility(View.GONE);
            } else if(parentQuiz.getQuizList().size() > 0) {
                model_position = 0;
                bgSubmit.setVisibility(View.VISIBLE);
                Intent soundIntent = new Intent("SOUND_RECEIVER");
                soundIntent.putExtra("quiz_id" , 0);
                sendBroadcast(soundIntent);
            }
            initializeBtnSubmit();
        } else {
            quizListReview = getIntent().getParcelableArrayListExtra("quiz_list_review");
            icCloseBlue.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            viewPagerReview.setVisibility(View.VISIBLE);
            badgePageText.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            btnPrev.setVisibility(View.VISIBLE);
            btnNext.setVisibility(View.VISIBLE);
            List<Quiz> quizLists = new ArrayList<>();
            for(Quiz quiz : quizListReview){
                if(quiz.getId() != -999){
                    quizLists.add(quiz);
                }
            }
            quizListReview = quizLists;
            initializeNavigation();
            initializeQuizReviewList();
            cardTitleText.setText(getResources().getString(R.string.title_quiz , "1" , String.valueOf(quizListReview.size())));
            boolean isTrue = false;
            Quiz quiz = quizListReview.get(0);
            for(Answer answer : quiz.getAnswerList()){
                if(answer.isChecked() == 1 && answer.isCorrect() == 1){
                    isTrue = true;
                    break;
                }
            }
            if(isTrue){
                badgePageText.setBackground(ContextCompat.getDrawable(ActivityQuizDetail.this , R.drawable.bg_badge_true));
                badgeText.setText(getString(R.string.badge_text , "1" , String.valueOf(quizListReview.size()) , getString(R.string.correct_badge)));
            } else {
                badgePageText.setBackground(ContextCompat.getDrawable(ActivityQuizDetail.this , R.drawable.bg_badge_false));
                badgeText.setText(getString(R.string.badge_text , "1" , String.valueOf(quizListReview.size()) , getString(R.string.incorrect_badge)));
            }
            viewPagerReview.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    EventBus.getDefault().post(new FragmentQuiz.StopSound());
                }

                @Override
                public void onPageSelected(int position) {
                    cardTitleText.setText(getResources().getString(R.string.title_quiz , String.valueOf(position + 1) , String.valueOf(quizListReview.size())));
                    if(position == 0){
                        btnPrev.setCardBackgroundColor(getResources().getColor(R.color.blue_1));
                        btnPrev.setEnabled(false);
                    } else {
                        if(position > 0 && position < quizListReview.size() - 1){
                            btnPrev.setCardBackgroundColor(getResources().getColor(R.color.blue_5));
                            btnPrev.setEnabled(true);
                            btnNext.setCardBackgroundColor(getResources().getColor(R.color.blue_5));
                            btnNext.setEnabled(true);
                        } else {
                            btnNext.setCardBackgroundColor(getResources().getColor(R.color.blue_1));
                            btnNext.setEnabled(false);
                        }
                    }
                    Quiz quiz = quizListReview.get(position);
                    boolean isCorrect = false;
                    for(int i = 0; i < quiz.getAnswerList().size(); i++){
                        if(quiz.getAnswerList().get(i).isChecked() == 1 && quiz.getAnswerList().get(i).isCorrect() == 1){
                            isCorrect = true;
                            break;
                        }
                    }
                    if(isCorrect){
                        badgePageText.setBackground(ContextCompat.getDrawable(ActivityQuizDetail.this , R.drawable.bg_badge_true));
                        badgeText.setText(getString(R.string.badge_text , String.valueOf(position + 1) , String.valueOf(quizListReview.size()) , getString(R.string.correct_badge)));
                    } else {
                        badgePageText.setBackground(ContextCompat.getDrawable(ActivityQuizDetail.this , R.drawable.bg_badge_false));
                        badgeText.setText(getString(R.string.badge_text , String.valueOf(position + 1) , String.valueOf(quizListReview.size()) , getString(R.string.incorrect_badge)));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            icCloseBlue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
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
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewPagerReview.getCurrentItem() > 0){
                        viewPagerReview.setCurrentItem(viewPagerReview.getCurrentItem() - 1);
                    }
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(viewPagerReview.getCurrentItem() < quizListReview.size() - 1){
                        viewPagerReview.setCurrentItem(viewPagerReview.getCurrentItem() + 1);
                    }
                }
            });
            btnPrev.setEnabled(false);
            btnPrev.setCardBackgroundColor(getResources().getColor(R.color.blue_1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!isReviewQuiz){
            if(!EventBus.getDefault().isRegistered(this))
                EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityQuizDetail.isPlayingMusic = false;
    }

    private void addQuizResultPage(){
        if(!isResultAdded){
            int score_points = 0;
            for(int i = 0; i < parentQuiz.getQuizList().size(); i++){
                Quiz quiz = parentQuiz.getQuizList().get(i);
                for(int j = 0; j < quiz.getAnswerList().size(); j++){
                    Answer answer = quiz.getAnswerList().get(j);
                    if(answer.isChecked() == 1 && answer.isCorrect() == 1){
                        score_points += 1;
                    }
                }
            }
            score_points = score_points * 20;
            int isSuccess = 0;
            if(score_points < 80)
                isSuccess = 0;
            else
                isSuccess = 1;
            Quiz result = new Quiz(-999 , "" , 0 , new ArrayList<>() , 0, score_points , isSuccess , 0, -1);
            parentQuiz.getQuizList().add(result);
            adapter.notifyDataSetChanged();
            if(!isReviewQuiz){
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                isResultAdded = true;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WrapperPosition data){
        Log.d("passed_positions" , String.format("%s AND %s" , String.valueOf(data.model_position) , String.valueOf(data.lastChoosePosition)));
        parentQuiz.getQuizList().get(data.model_position).setAnswerList(data.answerList);
        parentQuiz.getQuizList().get(data.model_position).setLastChoosePosition(data.lastChoosePosition);
        this.model_position = data.model_position;
        this.lastChoosePosition = data.lastChoosePosition;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WrapperCallback data){
        Log.d("MODEL_POSITION" , String.valueOf(data.model_position));
        parentQuiz.getQuizList().get(data.model_position).setAnswer(1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WrapperReview review){
        Intent intent = new Intent(this , ActivityQuizDetail.class);
        intent.putExtra("is_review" , true);
        intent.putParcelableArrayListExtra("quiz_list_review" , (ArrayList<Quiz>)parentQuiz.getQuizList());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WrapperReset data){
        if(!isReviewQuiz){
            for(int i = 0; i < parentQuiz.getQuizList().size(); i++){
                if(parentQuiz.getQuizList().get(i).getId() == -999){
                    parentQuiz.getQuizList().remove(i);
                } else {
                    for(int j = 0; j < parentQuiz.getQuizList().get(i).getAnswerList().size(); j++){
                        parentQuiz.getQuizList().get(i).getAnswerList().get(j).setClickable(1);
                        parentQuiz.getQuizList().get(i).getAnswerList().get(j).setChecked(0);
                        parentQuiz.getQuizList().get(i).getAnswerList().get(j).setResult(-1);
                    }
                }
                parentQuiz.getQuizList().get(i).setAnswer(0);
            }
            isResultAdded = false;
            cardTitleText.setText(getString(R.string.title_quiz , "1" , String.valueOf(parentQuiz.getQuizList().size())));
            btnPrev.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.VISIBLE);
            initializeBtnSubmit();
            initializeQuizList();
            if(parentQuiz.getQuizList().size() > 0)
                viewPager.setCurrentItem(0);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(FinishQuiz data){
        EventBus.getDefault().post(new FragmentQuiz.ResetSound());
        finish();
    }

    static class WrapperPosition {
        int model_position;
        int lastChoosePosition;
        ArrayList<Answer> answerList;
        WrapperPosition(int model_position , int lastChoosePosition , ArrayList<Answer> answerList){
            this.model_position = model_position;
            this.lastChoosePosition = lastChoosePosition;
            this.answerList = answerList;
        }
    }

    static class WrapperCallback {
        int model_position;
        ArrayList<Answer> answerList;
        WrapperCallback(int model_position , ArrayList<Answer> answerList){
            this.model_position = model_position;
            this.answerList = answerList;
        }
    }

    static class WrapperReview {}
    static class WrapperReset {}
    static class FinishQuiz{}

    private void initializeBtnSubmit(){
        submitText.setText("SUBMIT");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < parentQuiz.getQuizList().get(model_position).getAnswerList().size(); i++){
                    if(parentQuiz.getQuizList().get(model_position).getAnswerList().get(i).isChecked() == 1){
                        lastChoosePosition = i;
                        break;
                    }
                }
                //lastChoosePosition = parentQuiz.getQuizList().get(model_position).getLastChoosePosition();
                if(lastChoosePosition != -1){
                    EventBus.getDefault().post(new FragmentQuiz.StopSound());
                    EventBus.getDefault().post(new FragmentQuiz.TriggerChange(model_position , lastChoosePosition));
                    parentQuiz.getQuizList().get(model_position).setIsResult(1);
                    initializeBtnNext();
                } else {
                    Toast.makeText(getApplicationContext() , "Pilih Jawaban Dahulu" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeBtnNext(){
        submitText.setText("NEXT");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = viewPager.getCurrentItem();
                if(count < parentQuiz.getQuizList().size() - 1){
                    model_position = count + 1;
                    lastChoosePosition = -1;

                    boolean atleast_clicked = false;
                    for(int i = 0; i < parentQuiz.getQuizList().get(count + 1).getAnswerList().size(); i++){
                        if(parentQuiz.getQuizList().get(count + 1).getAnswerList().get(i).isChecked() == 1){
                            atleast_clicked = true;
                            break;
                        }
                    }
                    if(atleast_clicked){
                        initializeBtnNext();
                    } else {
                        initializeBtnSubmit();
                    }
                    viewPager.setCurrentItem(count + 1);
                } else {
                    boolean atleast_one = false;
                    for(Quiz quiz : parentQuiz.getQuizList()){
                        if(quiz.getIsAnswer() != 1){
                            atleast_one = true;
                        }
                    }
                    if(atleast_one){
                        Toast.makeText(getApplicationContext() , "Harap isi semua soal" , Toast.LENGTH_SHORT).show();
                    } else {
                        addQuizResultPage();
                    }
                }
            }
        });
    }

    private void initializeQuizList(){
        cardTitleText.setText(getResources().getString(R.string.title_quiz , "1" , String.valueOf(parentQuiz.getQuizList().size())));
        adapter = new QuizPagerAdapter(getSupportFragmentManager() , parentQuiz.getQuizList());
        viewPager.setAdapter(adapter);
    }
    private void initializeQuizReviewList(){
        cardTitleText.setText(getResources().getString(R.string.title_quiz , "1" , String.valueOf(quizListReview.size())));
        QuizPagerReview reviewAdapter = new QuizPagerReview(getSupportFragmentManager() , quizListReview);
        viewPagerReview.setAdapter(reviewAdapter);
    }
    private void initializeNavigation(){
        recyclerViewNav.setHasFixedSize(true);
        recyclerViewNav.setItemAnimator(null);
        recyclerViewNav.setLayoutManager(new LinearLayoutManager(this , RecyclerView.VERTICAL , false));
        List<Quiz> quizNavList = new ArrayList<>();
        if(!isReviewQuiz){
            for(Quiz quiz : parentQuiz.getQuizList()){
                if(quiz.getId() != -999)
                    quizNavList.add(quiz);
            }
        } else {
            quizNavList = quizListReview;
        }
        QuestionAdapter adapter = new QuestionAdapter(this, quizNavList, new QuestionAdapter.QuestionListener() {
            @Override
            public void onClick(int position) {
                if(!isReviewQuiz){
                    EventBus.getDefault().post(new FragmentQuiz.StopSound());
                    viewPager.setCurrentItem(position);
                    model_position = position;
                    Log.d("question_adapter" , "masuk sini daf");
                    lastChoosePosition = -1;
                } else {
                    viewPagerReview.setCurrentItem(position);
                }
                if(isExpanded){
                    isExpanded = false;
                    icMenu.setImageResource(R.drawable.ic_menu_blue);
                    expandableNav.collapse();
                }
                Log.d("positions" , String.valueOf(position));
            }
        });
        recyclerViewNav.setAdapter(adapter);
    }
    class QuizPagerAdapter extends FragmentStatePagerAdapter {
        private List<Quiz> quizList = new ArrayList<>();
        public QuizPagerAdapter(FragmentManager manager , List<Quiz> quizList){
            super(manager , BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.quizList = quizList;
        }

        @Override
        public int getCount() {
            return quizList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(quizList.get(position).getId() == - 999){
                return FragmentQuiz.newInstance(quizList.get(position) , quizList.get(position).getAnswerList() , quizPosition, position , true , true);
            } else {
                Log.d("POSITION_VIEW" , String.valueOf(position));
                for(int i = 0; i < quizList.get(position).getAnswerList().size(); i++){
                    Answer data = quizList.get(position).getAnswerList().get(i);
                    Log.d("DATA_JAWAB" , data.getAnswer());
                }
                return FragmentQuiz.newInstance(quizList.get(position) , quizList.get(position).getAnswerList() , quizPosition, position , false , false);
            }
        }
    }
    class QuizPagerReview extends FragmentStatePagerAdapter {
        private List<Quiz> quizList = new ArrayList<>();
        public QuizPagerReview(FragmentManager manager , List<Quiz> quizList){
            super(manager);
            this.quizList = quizList;
        }

        @Override
        public int getCount() {
            return quizList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return FragmentQuiz.newInstance(quizList.get(position) ,  quizList.get(position).getAnswerList() , quizPosition , position , true , false);
        }
    }
}