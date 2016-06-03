package com.hongxiang.kforthirtysix.fragment.mine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.SeetingActivity;
import com.hongxiang.kforthirtysix.activity.mine.FavouriteActivity;
import com.hongxiang.kforthirtysix.activity.mine.LogActivity;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.PicassoCirclTransform;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/9.
 * 我的Fragment
 */


public class MineFragment extends BaseFragment {
    private View logLayout, favoriteLayout;
    private String user = "";
    private TextView userTv;
    //  private MyBroad myBroad;
    private ImageView icon, setting;
    private MyQqBroad myqqBroad;


    @Override
    public void initView(View view) {
        favoriteLayout = view.findViewById(R.id.mine_favourite_layout);
        logLayout = view.findViewById(R.id.log_layout);
        userTv = (TextView) view.findViewById(R.id.mine_user);
        icon = (ImageView) view.findViewById(R.id.log_icon);
        setting = (ImageView) view.findViewById(R.id.setting);


    }


    @Override
    public void initData() {
        ShareSDK.initSDK(getActivity());
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeetingActivity.class);
                if (!(userTv.getText().equals("未登录"))) {
                    intent.putExtra("log", true);
                }
                startActivity(intent);
            }
        });
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
        // myBroad = new MyBroad();
        myqqBroad = new MyQqBroad();
        IntentFilter intentFilter = new IntentFilter();
        //  intentFilter.addAction("LogBroad");
        intentFilter.addAction("QQLogBroad");
        //  getActivity().registerReceiver(myBroad, intentFilter);
        getActivity().registerReceiver(myqqBroad, intentFilter);


    }

    @Override
    public int initLayout() {

        return R.layout.fragment_mine;
    }

    //
//    class MyBroad extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            user = intent.getStringExtra("user");
//            if (user.length() > 0) {
//                userTv.setText(user);
//            }
//        }
//    }
    class MyQqBroad extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Picasso.with(getActivity()).load(intent.getStringExtra("icon")).transform(new PicassoCirclTransform()).resize(150, 150).centerCrop().into(icon);
            userTv.setText(intent.getStringExtra("qqname"));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        getContext().unregisterReceiver(myBroad);
        getContext().unregisterReceiver(myqqBroad);
    }

    @Override
    public void onStart() {
        super.onStart();
        Platform platform = ShareSDK.getPlatform(QQ.NAME);

        if (!(platform.isValid())) {
            icon.setImageResource(R.mipmap.common_avatar);
            userTv.setText("未登录");
        }
    }
}
