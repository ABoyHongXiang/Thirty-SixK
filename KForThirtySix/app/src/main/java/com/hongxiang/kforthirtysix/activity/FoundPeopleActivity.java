package com.hongxiang.kforthirtysix.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.FoundPeopleAdapter;
import com.hongxiang.kforthirtysix.bean.FoundPeopleBean;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;

/**
 * Created by dllo on 16/5/16.
 * 寻找投资人
 */
public class FoundPeopleActivity extends AppCompatActivity {
    private final static String url = "https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20";
    private FoundPeopleBean foundPeopleBean;
    private FoundPeopleAdapter foundPeopleAdapter;
    private ListView listView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //标题栏隐藏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_foundpeople);
        //listview
        listView = (ListView) findViewById(R.id.foundpeople_listview);
        //返回键
        back = (ImageView) findViewById(R.id.foundpeople_back);
        //初始化适配器
        foundPeopleAdapter = new FoundPeopleAdapter(this);
        listView.setAdapter(foundPeopleAdapter);
        //返回按钮的监听
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
      //  解 析  FoundPeopleBean
        VolleySingle.addRequest(url, FoundPeopleBean.class, new Response.Listener<FoundPeopleBean>() {
            @Override
            public void onResponse(FoundPeopleBean response) {
                foundPeopleBean = response;
                //解析后传适配器
                foundPeopleAdapter.setFoundPeopleBean(foundPeopleBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
