package com.hongxiang.kforthirtysix.util;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;

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

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    //对外提供一个方法 这个方法就是让别的类获取自己的context
    public static Context getContext(){

        return mContext;
    }

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret
        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad");
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey
        PlatformConfig.setAlipay("2015111700822536");
        //支付宝 appid
    }
}
