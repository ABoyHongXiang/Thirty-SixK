package com.hongxiang.kforthirtysix.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dllo on 16/6/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    //这是加载布局的抽象方法
    protected abstract  int getLayout();

    //这是加载组件的方法
    protected  abstract  void initView();

    //这是加载数据的方法
    protected abstract void initData();}