package com.hongxiang.kforthirtysix.adapter.lol;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by dllo on 16/5/9.
 * 股权投资Fragment里面viewpager和tablayout的适配器
 */
public class InvestmentMainAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[] tab = {"全部", "募资中", "募资完成", "融资成功"};
    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
        notifyDataSetChanged();
    }

    public InvestmentMainAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
