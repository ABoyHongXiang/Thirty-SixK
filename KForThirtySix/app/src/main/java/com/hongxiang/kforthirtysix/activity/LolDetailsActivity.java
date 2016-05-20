package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/20.
 */
public class LolDetailsActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loldetails);
        textView = (TextView) findViewById(R.id.loldetails_tv);
        Intent intent = getIntent();
        String a = intent.getStringExtra("urlid");
        textView.setText(a);
    }
}
