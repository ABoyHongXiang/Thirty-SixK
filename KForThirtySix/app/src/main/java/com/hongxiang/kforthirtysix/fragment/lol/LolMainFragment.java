package com.hongxiang.kforthirtysix.fragment.lol;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.hongxiang.kforthirtysix.adapter.lol.LolMainAdapter;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.SearchActivity;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/9.
 */
public class LolMainFragment extends BaseFragment implements View.OnClickListener {
    private LolMainAdapter lolMainAdapter;
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
        lolMainAdapter = new LolMainAdapter(getChildFragmentManager());
        initFragment();
        viewPager.setAdapter(lolMainAdapter);
        lolMainAdapter.setFragmentList(fragmentList);
        tabLayout.setupWithViewPager(viewPager);
        search.setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_invest;
    }

    public void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new LolNewsFragment());
        fragmentList.add(new LolVideoFragment(0));
        fragmentList.add(new LolVideoFragment(1));
        fragmentList.add(new LolVideoFragment(2));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //搜索按钮
            case R.id.investfragment_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);

                break;
        }
    }
}
