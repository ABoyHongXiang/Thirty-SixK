package com.hongxiang.kforthirtysix;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hongxiang.kforthirtysix.fragment.FoundFragment;
import com.hongxiang.kforthirtysix.fragment.InvestFragment;
import com.hongxiang.kforthirtysix.fragment.MineFragment;
import com.hongxiang.kforthirtysix.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

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
        initFragment();
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragmentList(fragmentList);
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);
        initTab();


    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new InvestFragment());
        fragmentList.add(new FoundFragment());
        fragmentList.add(new MineFragment());

    }

    private void initTab() {
        tabLayout.getTabAt(0).setIcon(R.drawable.table_selector_news);
        tabLayout.getTabAt(1).setIcon(R.drawable.table_selector_invest);
        tabLayout.getTabAt(2).setIcon(R.drawable.table_selector_found);
        tabLayout.getTabAt(3).setIcon(R.drawable.table_selsetor_mine);

    }

}
