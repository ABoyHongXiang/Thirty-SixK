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
    private final static String url = "https://rong.36kr.com/api/mobi/investor?page=1&pageSize=";
    private FoundPeopleBean foundPeopleBean;
    private FoundPeopleAdapter foundPeopleAdapter;
    private ListView listView;
    private ImageView back;
    private PullToRefreshListView pullToRefreshListView;
    private int n = 2;//刷新数据时候用的变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_foundpeople);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.foundpeople_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        startVolley(20);
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //重新记载的时候要把变量初始化
                startVolley(20);
                n = 20;
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //每次刷新的时候 调用解析方法,通过改变n改变接口
                startVolley(20 * n);
                n = n + 1;
            }
        });
        listView = pullToRefreshListView.getRefreshableView();
        back = (ImageView) findViewById(R.id.foundpeople_back);
        foundPeopleAdapter = new FoundPeopleAdapter(this);
        listView.setAdapter(foundPeopleAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 解析的方法 page变量方便刷新的时候对接口进行改变
     *
     * @param page
     */
    private void startVolley(int page) {
        VolleySingle.addRequest(url + page, FoundPeopleBean.class, new Response.Listener<FoundPeopleBean>() {
            @Override
            public void onResponse(FoundPeopleBean response) {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
