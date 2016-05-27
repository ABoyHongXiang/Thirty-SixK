package com.hongxiang.kforthirtysix.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.hongxiang.kforthirtysix.adapter.MainAdapter;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.found.FoundFragment;
import com.hongxiang.kforthirtysix.fragment.lol.LolMainFragment;
import com.hongxiang.kforthirtysix.fragment.mine.MineFragment;
import com.hongxiang.kforthirtysix.fragment.news.NewsFragment;
import com.hongxiang.kforthirtysix.util.ExampleUtil;


import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

/*
* 主Activity
*
* */

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainAdapter mainAdapter;
    private List<Fragment> fragmentList;

    public static boolean isForeground = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        initFragment();//添加四个Fragment的方法
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);
        initTab();//设置tab的方法


        registerMessageReceiver();  // used for receive msg
    }

    //添加四个Fragment的方法
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new LolMainFragment());
        fragmentList.add(new FoundFragment());
        fragmentList.add(new MineFragment());

    }

    //设置tab的方法
    private void initTab() {
        tabLayout.getTabAt(0).setIcon(R.drawable.table_selector_news);
        tabLayout.getTabAt(1).setIcon(R.drawable.table_selector_invest);
        tabLayout.getTabAt(2).setIcon(R.drawable.table_selector_found);
        tabLayout.getTabAt(3).setIcon(R.drawable.table_selsetor_mine);

    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(getApplicationContext());
    }


    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }


    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }

            }
        }
    }



}
