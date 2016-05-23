package com.hongxiang.kforthirtysix.activity.lol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/20.
 * lol的详情界面
 */
public class LolNewsDetailsActivity extends AppCompatActivity {
    private final  static String START_URL=" http://lol.zhangyoubao.com/mobiles/item/";
    private final  static String END_URL="?user_id=&token=&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438754752&p_=16601&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol&size=middle";
   //显示网页的组件
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lolmain_details);
        webView = (WebView) findViewById(R.id.webview);
        //获取上个界面传来的id;
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url =START_URL+id+END_URL;
        webView.loadUrl(url);

    }
}
