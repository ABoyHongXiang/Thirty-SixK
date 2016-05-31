package com.hongxiang.kforthirtysix.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.VolleySingle;
import com.hongxiang.kforthirtysix.bean.WriterBean;

/**
 * Created by dllo on 16/5/21.
 */
public class WriterActivity extends AppCompatActivity {

    private final static String START_URL = "https://rong.36kr.com/api/mobi/new/";
    private final static String END_URL = "/author-region";
    private TextView textView;
    private String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("writerid", 0);

        url = START_URL + id + END_URL;
        setContentView(R.layout.activity_writer);
        VolleySingle.addRequest(url, WriterBean.class, new Response.Listener<WriterBean>() {
            @Override
            public void onResponse(WriterBean response) {
                Log.d("WriterActivity", "翔哥第一次解析成功"+url);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
