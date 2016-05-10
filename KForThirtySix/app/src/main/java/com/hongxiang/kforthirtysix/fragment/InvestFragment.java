package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hongxiang.kforthirtysix.BaseFragment;
import com.hongxiang.kforthirtysix.InvestAdapter;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.SearchActivity;
import com.hongxiang.kforthirtysix.investfragment.AllFragment;
import com.hongxiang.kforthirtysix.investfragment.FinanceFragment;
import com.hongxiang.kforthirtysix.investfragment.FundraisedFragment;
import com.hongxiang.kforthirtysix.investfragment.FundraisingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class InvestFragment extends BaseFragment implements View.OnClickListener {
    private InvestAdapter investAdapter;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView search;

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.invest_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.invest_tablayout);
        search = (ImageView) view.findViewById(R.id.investfragment_search);
    }

    @Override
    public void initData() {
        investAdapter = new InvestAdapter(getChildFragmentManager());
        initFragment();
        viewPager.setAdapter(investAdapter);
        investAdapter.setFragmentList(fragmentList);
        tabLayout.setupWithViewPager(viewPager);
        search.setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_invest;
    }

    public void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new AllFragment());
        fragmentList.add(new FinanceFragment());
        fragmentList.add(new FundraisedFragment());
        fragmentList.add(new FundraisingFragment());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.investfragment_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
