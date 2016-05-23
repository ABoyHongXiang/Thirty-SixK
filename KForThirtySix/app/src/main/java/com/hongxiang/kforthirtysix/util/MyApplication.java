package com.hongxiang.kforthirtysix.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/5/20.
 */
public class MyApplication extends Application {
    private static Context mContext;
    //Application创建的原因是因为我们需要一个属于自己的大"环境"Context
    //保证自己的app拥有单独的context对象

    //第一个生命周期我们队context赋值
    @Override
    public void onCreate() {
        super.onCreate();
        //this代表当前的环境
        mContext = this;
    }

    //对外提供一个方法 这个方法就是让别的类获取自己的context
    public static Context getContext(){
        return mContext;
    }
}
