package com.hongxiang.kforthirtysix.activity.found;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.found.NearPlayAdapter;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.VolleySingle;


/**
 * Created by dllo on 16/5/19.
 * 近期活动
 */
public class NearPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private static String URL = "https://rong.36kr.com/api/mobi/activity/list?page=";
    private NearPlayBean nearPlayBean,nearPlayBean1; //近期活动实体类
    private NearPlayAdapter nearPlayAdapter;//近期活动适配器
    private ListView listView;
    private View playTime, playType;
    private TextView playTimeTv, playTypeTv;
    private PopupWindow timePop, typePop;
    private PullToRefreshListView pullToRefreshListView;
    private int n = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_nearaplay);
        startVolley(1);
        nearPlayAdapter = new NearPlayAdapter(this);
        pullToRefreshListView =(PullToRefreshListView) findViewById(R.id.nearplay_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //startVolley(n);
                VolleySingle.addRequest(URL + n, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
                    @Override
                    public void onResponse(NearPlayBean response) {
                        Log.d("NearPlayActivity", "近期活动数据解析完成");
                        nearPlayBean1 = response;
                        nearPlayBean.getData().getData().addAll(nearPlayBean1.getData().getData());
                        nearPlayAdapter.setNearPlayBean(nearPlayBean);
                        listView.setAdapter(nearPlayAdapter);
                        pullToRefreshListView.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                n = n + 1;

            }
        });
        playTime = findViewById(R.id.play_time);
        playType = findViewById(R.id.play_type);
        playTimeTv = (TextView) findViewById(R.id.play_time_tv);
        playTime.setOnClickListener(this);
        playType.setOnClickListener(this);

        showTypePop();
        showTimePop();


    }


    private void startVolley(int page) {
        VolleySingle.addRequest(URL + page, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
                Log.d("NearPlayActivity", "近期活动数据解析完成");
                nearPlayBean = response;
                nearPlayAdapter.setNearPlayBean(nearPlayBean);
                listView.setAdapter(nearPlayAdapter);
                pullToRefreshListView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_time:
                if (typePop != null && typePop.isShowing()) {
                    typePop.dismiss();
                    if (timePop != null && timePop.isShowing()) {
                        timePop.dismiss();

                    } else {
                        timePop.showAsDropDown(playTime);
                    }
                } else {
                    if (timePop != null && timePop.isShowing()) {
                        timePop.dismiss();

                    } else {
                        timePop.showAsDropDown(playTime);
                    }
                }

                break;
            case R.id.play_type:
                if (timePop != null && timePop.isShowing()) {
                    timePop.dismiss();
                    if (typePop != null && typePop.isShowing()) {
                        typePop.dismiss();

                    } else {
                        typePop.showAsDropDown(playType);
                    }
                } else {
                    if (typePop != null && typePop.isShowing()) {
                        typePop.dismiss();

                    } else {
                        typePop.showAsDropDown(playType);
                    }
                }

                break;

        }
    }


    private void showTimePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_time, null, false);
        timePop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        timePop.setAnimationStyle(R.style.pop_anim);
    }

    private void showTypePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_type, null, false);
        typePop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        typePop.setAnimationStyle(R.style.pop_anim);

    }

}
