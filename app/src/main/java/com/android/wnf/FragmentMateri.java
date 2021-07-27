package com.android.wnf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.agog.mathdisplay.MTMathView;
import com.android.wnf.custom_widget.TeXView;

public class FragmentMateri extends Fragment {
    private String title;
    private String materi;
    private AppCompatTextView titleText;
    private TeXView materiText;
    public static FragmentMateri newInstance(String title , String materi) {
        FragmentMateri fragment = new FragmentMateri();
        Bundle bundle = new Bundle();
        bundle.putString("title" , title);
        bundle.putString("materi" , materi);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        materi = getArguments().getString("materi");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_materi , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleText = view.findViewById(R.id.titleText);
        materiText = view.findViewById(R.id.materiText);
        titleText.setText(title);
        materiText.setLaTeX(materi);
    }
}
