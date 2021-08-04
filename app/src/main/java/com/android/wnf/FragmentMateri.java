package com.android.wnf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import io.github.kexanie.library.MathView;

public class FragmentMateri extends Fragment {
    private String title;
    private String materi;
    private AppCompatTextView titleText;
    private MathView mathView;
    private ImageView imageResource;
    private int imageMateri;
    public static FragmentMateri newInstance(String title , String materi , int imageSource) {
        FragmentMateri fragment = new FragmentMateri();
        Bundle bundle = new Bundle();
        bundle.putString("title" , title);
        bundle.putString("materi" , materi);
        bundle.putInt("image_source" , imageSource);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        materi = getArguments().getString("materi");
        imageMateri = getArguments().getInt("image_source" , 0);
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
        mathView = view.findViewById(R.id.mathText);
        imageResource = view.findViewById(R.id.imageMateri);
        titleText.setText(title);
        imageResource.setImageResource(imageMateri);
    }
}
