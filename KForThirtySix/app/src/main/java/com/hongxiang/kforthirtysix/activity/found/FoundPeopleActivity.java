package com.hongxiang.kforthirtysix.activity.found;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.found.FoundPeopleAdapter;
import com.hongxiang.kforthirtysix.bean.FoundPeopleBean;

/**
 * Created by dllo on 16/5/16.
 * 寻找投资人
 */
public class FoundPeopleActivity extends AppCompatActivity {
    private final static String url = "https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20";//需要解析的网址
    private FoundPeopleBean foundPeopleBean;//寻找投资人实体类
    private FoundPeopleAdapter foundPeopleAdapter;//寻找投资人适配器
    private ListView listView;  //listview
    private ImageView back;//返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//标题栏隐藏
        setContentView(R.layout.activity_foundpeople);
        listView = (ListView) findViewById(R.id.foundpeople_listview);//listview
        back = (ImageView) findViewById(R.id.foundpeople_back);//返回键
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
        //解析FoundPeopleBean
        startVolley();

    }

    //解析的方法
    private void startVolley() {
        VolleySingle.addRequest(url, FoundPeopleBean.class, new Response.Listener<FoundPeopleBean>() {
            @Override
            public void onResponse(FoundPeopleBean response) {
                Log.d("FoundPeopleActivity", "寻找联系人数据解析完成");
                foundPeopleBean = response;
                foundPeopleAdapter.setFoundPeopleBean(foundPeopleBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}
