package com.example.mechrevo.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        initView();
        initData();
        bindEvent();
    }

    protected abstract int bindLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void bindEvent();

    protected <T extends View> T bindView(int resid){
        return findViewById(resid);
    }

}
