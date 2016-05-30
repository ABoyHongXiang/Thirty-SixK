package com.hongxiang.kforthirtysix.fragment.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.mine.FavouriteActivity;
import com.hongxiang.kforthirtysix.activity.mine.LogActivity;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;

/**
 * Created by dllo on 16/5/9.
 * 我的Fragment
 */


public class MineFragment extends BaseFragment {
    private View logLayout, favoriteLayout;
    private String user = "";
    private TextView userTv;

    @Override
    public void initView(View view) {
        favoriteLayout = view.findViewById(R.id.mine_favourite_layout);
        logLayout = view.findViewById(R.id.log_layout);
        userTv = (TextView) view.findViewById(R.id.mine_user);
        if (user.length() > 0) {
            userTv.setText(user);
        }

    }

    public MineFragment(String user) {
        this.user = user;
        Log.d("MineFragment", user);
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
        favoriteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public int initLayout() {

        return R.layout.fragment_mine;
    }


}
