package com.hongxiang.kforthirtysix.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CallLog;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;

import cn.jpush.android.api.JPushInterface;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/17.
 */
public class WelcomeActivity extends AppCompatActivity {
    private TextView timeTv;
    private CountDownTimer timer;
    private static final String URL = "http://img4.duitang.com/uploads/item/201407/06/20140706134951_tc2US.thumb.700_0.jpeg";
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();//隐藏标题栏
        imageView = (ImageView) findViewById(R.id.welcome_img);
        timeTv = (TextView) findViewById(R.id.wel_time);
        Picasso.with(this).load(URL).into(imageView);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();//在跳转的时候 取消timer
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                finish();
            }
        });

        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTv.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timeTv.setText("跳转");
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();


    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
