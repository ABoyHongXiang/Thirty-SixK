package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.View;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.LogActivity;

/**
 * Created by dllo on 16/5/9.
 * 我的Fragment
 */


public class MineFragment extends BaseFragment {
    private View logLayout;

    @Override
    public void initView(View view) {
        logLayout = view.findViewById(R.id.log_layout);
    }

    @Override
    public void initData() {
        logLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_mine;
    }
}
