package com.hongxiang.kforthirtysix.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.LogAdapter;
import com.hongxiang.kforthirtysix.logfragment.LogFragment;
import com.hongxiang.kforthirtysix.logfragment.LogInFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/16.
 * 登录界面
 */
public class LogActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    private LogAdapter logAdapter;
    private ViewPager  viewpager;
    private TabLayout tabLayout;
    private ImageView finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_log);
        viewpager = (ViewPager) findViewById(R.id.log_viewpager);
        tabLayout = (TabLayout) findViewById(R.id.log_tablayout);
        finish = (ImageView) findViewById(R.id.log_back);
        addFrament();
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
    }



    private void addFrament() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LogInFragment());
        fragmentList.add(new LogFragment());
    }

}
