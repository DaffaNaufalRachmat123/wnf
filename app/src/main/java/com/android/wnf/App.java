package com.android.wnf;

import android.app.Application;

import io.nano.tex.LaTeX;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LaTeX.instance().init(this);
    }
}
