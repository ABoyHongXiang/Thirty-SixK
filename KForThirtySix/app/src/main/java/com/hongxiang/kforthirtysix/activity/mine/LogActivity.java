package com.hongxiang.kforthirtysix.activity.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.mine.LogAdapter;
import com.hongxiang.kforthirtysix.fragment.mine.LogFragment;
import com.hongxiang.kforthirtysix.fragment.mine.LogInFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 登录界面
 */
public class LogActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private LogAdapter logAdapter;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private ImageView finish;
    private String broad;
    private MyBroad myBroad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        getSupportActionBar().hide();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        setContentView(R.layout.activity_log);
        //viewpager
        viewpager = (ViewPager) findViewById(R.id.log_viewpager);
        //tablayout
        tabLayout = (TabLayout) findViewById(R.id.log_tablayout);
        //返回键
        finish = (ImageView) findViewById(R.id.log_back);
        //添加Fragment的方法 两个Fragment 一个是登录一个是注册
        addFrament();
        logAdapter = new LogAdapter(getSupportFragmentManager());
        logAdapter.setFragmentList(fragmentList);
        viewpager.setAdapter(logAdapter);
        tabLayout.setupWithViewPager(viewpager);
        //返回键的监听
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //tablayout 设置自定义View
        tabLayout.getTabAt(0).setCustomView(R.layout.tablayout_viewone);
        tabLayout.getTabAt(1).setCustomView(R.layout.tablayout_viewtwo);
        myBroad = new MyBroad();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LogBroad");
        registerReceiver(myBroad, intentFilter);


    }


    //添加两个Fragment的方法
    private void addFrament() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LogInFragment());
        fragmentList.add(new LogFragment());
    }

    class MyBroad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroad);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
