package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/20.
 */
public class AllLolDetails extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alllolderails);
        webView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url =" http://lol.zhangyoubao.com/mobiles/item/"+id+"?user_id=&token=&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438754752&p_=16601&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol&size=middle";
        webView.loadUrl(url);

    }
}
