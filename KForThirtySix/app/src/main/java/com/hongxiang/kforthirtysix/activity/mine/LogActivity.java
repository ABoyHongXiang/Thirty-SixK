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
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.BaseActivity;
import com.hongxiang.kforthirtysix.adapter.mine.LogAdapter;
import com.hongxiang.kforthirtysix.fragment.mine.LogFragment;
import com.hongxiang.kforthirtysix.fragment.mine.LogInFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 登录界面
 */
public class LogActivity extends BaseActivity {
    private List<Fragment> fragmentList;
    private LogAdapter logAdapter;
    private ViewPager viewpager;
    private TabLayout tabLayout;
    private ImageView finish;

   // private MyBroad myBroad;
    private MyQQBroad myqqBroad;


    @Override
    protected int getLayout() {
        return R.layout.activity_log;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        viewpager = (ViewPager) findViewById(R.id.log_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.log_tablayout);
        finish = (ImageView) findViewById(R.id.log_back);
        addFrament();
    }

    @Override
    protected void initData() {
        logAdapter = new LogAdapter(getSupportFragmentManager());
        logAdapter.setFragmentList(fragmentList);
        viewpager.setAdapter(logAdapter);
        tabLayout.setupWithViewPager(viewpager);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tabLayout.getTabAt(0).setCustomView(R.layout.tablayout_viewone);
        tabLayout.getTabAt(1).setCustomView(R.layout.tablayout_viewtwo);
        myqqBroad = new MyQQBroad();
        //intentFilter.addAction("LogBroad");
        //registerReceiver(myBroad, intentFilter)
        //myBroad = new MyBroad();
        //注册广播
       IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("QQLogBroad");
        registerReceiver(myqqBroad, intentFilter);
    }


    //添加两个Fragment的方法
    private void addFrament() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LogInFragment());
        fragmentList.add(new LogFragment());
    }

//    class MyBroad extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            finish();
//
//        }
//    }

    /**
     * 接到广播后执行的方法
     * 一秒后让Activity finish
     */
    class MyQQBroad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Toast.makeText(context, "请稍后", Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  unregisterReceiver(myBroad);
        unregisterReceiver(myqqBroad);

    }
}
