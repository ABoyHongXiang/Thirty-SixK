package com.hongxiang.kforthirtysix.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.BooleanResult;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.BaseActivity;


import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/6/2.
 */
public class SeetingActivity extends BaseActivity {
    private Button exitLog;
    private Boolean log;


    cn.sharesdk.framework.Platform platform = ShareSDK.getPlatform(QQ.NAME);

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        exitLog = (Button) findViewById(R.id.exit_log);
        exitLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (platform.isValid()) {
                    platform.removeAccount();
                    finish();
                }
            }
        });

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //接收一个布尔值判断退出登录是否显示
        log = intent.getBooleanExtra("log", false);
        if (log) {
            exitLog.setVisibility(View.VISIBLE);
        } else {
            exitLog.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

