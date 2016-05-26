package com.hongxiang.kforthirtysix.activity.found;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.found.FoundPeopleAdapter;
import com.hongxiang.kforthirtysix.bean.FoundPeopleBean;

/**
 * Created by dllo on 16/5/16.
 * 寻找投资人
 */
public class FoundPeopleActivity extends AppCompatActivity {
    private final static String url = "https://rong.36kr.com/api/mobi/investor?page=1&pageSize=";//需要解析的网址
    private FoundPeopleBean foundPeopleBean;//寻找投资人实体类
    private FoundPeopleAdapter foundPeopleAdapter;//寻找投资人适配器
    private ListView listView;  //listview
    private ImageView back;//返回按钮
    private PullToRefreshListView pullToRefreshListView;
    private int n = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//标题栏隐藏
        setContentView(R.layout.activity_foundpeople);
        //解析FoundPeopleBean

        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.foundpeople_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        startVolley(20);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(20);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(20 * n);
                n = n + 1;
            }
        });
        listView = pullToRefreshListView.getRefreshableView();
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


    }

    //解析的方法
    private void startVolley(int page) {
        VolleySingle.addRequest(url + page, FoundPeopleBean.class, new Response.Listener<FoundPeopleBean>() {
            @Override
            public void onResponse(FoundPeopleBean response) {
                Log.d("FoundPeopleActivity", "寻找联系人数据解析完成");
                foundPeopleBean = response;
                foundPeopleAdapter.setFoundPeopleBean(foundPeopleBean);
                pullToRefreshListView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

}
