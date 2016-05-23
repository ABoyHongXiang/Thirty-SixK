package com.hongxiang.kforthirtysix.activity;

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


import java.util.ArrayList;
import java.util.List;

/*
* 主Activity
*
* */

public class MainActivity extends FragmentActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainAdapter mainAdapter;
    private List<Fragment> fragmentList;


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


}
