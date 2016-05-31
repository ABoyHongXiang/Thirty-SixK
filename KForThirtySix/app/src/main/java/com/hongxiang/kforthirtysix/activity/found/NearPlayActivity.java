package com.hongxiang.kforthirtysix.activity.found;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.found.NearPlayAdapter;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.util.VolleySingle;

/**
 * Created by dllo on 16/5/19.
 * 近期活动
 */
public class NearPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private static String URL = "https://rong.36kr.com/api/mobi/activity/list?page=";
    private NearPlayBean nearPlayBean;
    private NearPlayAdapter nearPlayAdapter;
    private ListView listView;
    private View playTime, playType;
    private TextView playTimeTv, playTypeTv;
    private PopupWindow timePop, typePop;
    private PullToRefreshListView pullToRefreshListView;
    private int n = 2;
    private int i = 40, q = 40, w = 40, e = 40, r = 40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nearaplay);
        startVolley(1, "");
        nearPlayAdapter = new NearPlayAdapter(this);
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.nearplay_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                switch (playTypeTv.getText().toString()) {
                    case "Demo Day":
                        startVolley(1, "&categoryId=1&pageSize=20");
                        i = 40;
                        break;
                    case "氪空间":
                        startVolley(1, "&categoryId=4&pageSize=20");
                        q = 40;
                        break;
                    case "股权投资":
                        startVolley(1, "&categoryId=5&pageSize=20");
                        w = 40;
                        break;
                    case "企业服务":
                        startVolley(1, "&categoryId=6&pageSize=20");
                        e = 50;
                        break;
                    case "极速融资":
                        startVolley(1, "&categoryId=7&pageSize=20");
                        r = 40;
                        break;
                    case "全部":
                        startVolley(1, "");
                        n = 2;
                        break;
                }

            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                switch (playTypeTv.getText().toString()) {
                    case "Demo Day":
                        String sth1 = "&categoryId=1&pageSize=" + i;
                        pushVolley(1, sth1);
                        i = i + 20;
                        listView.setAdapter(nearPlayAdapter);
                        break;
                    case "氪空间":
                        String sth4 = "&categoryId=4&pageSize=" + i;
                        pushVolley(1, sth4);
                        q = q + 20;
                        listView.setAdapter(nearPlayAdapter);
                        break;
                    case "股权投资":
                        String sth5 = "&categoryId=5&pageSize=" + i;
                        pushVolley(1, sth5);
                        w = w + 20;
                        listView.setAdapter(nearPlayAdapter);
                        ;
                        break;
                    case "企业服务":
                        String sth6 = "&categoryId=6&pageSize=" + i;
                        pushVolley(1, sth6);
                        e = e + 20;
                        listView.setAdapter(nearPlayAdapter);
                        break;
                    case "极速融资":
                        String sth7 = "&categoryId=7&pageSize=" + i;
                        pushVolley(1, sth7);
                        r = r + 20;
                        listView.setAdapter(nearPlayAdapter);
                        break;
                    case "全部":
                        pushVolley(n, "");
                        listView.setAdapter(nearPlayAdapter);
                        n = n + 1;
                        break;
                }


            }
        });
        playTime = findViewById(R.id.play_time);
        playType = findViewById(R.id.play_type);
        playTimeTv = (TextView) findViewById(R.id.play_time_tv);
        playTypeTv = (TextView) findViewById(R.id.play_type_tv);
        playTime.setOnClickListener(this);
        playType.setOnClickListener(this);
        showTypePop();
        showTimePop();


    }

    private void pushVolley(int n, String a) {
        VolleySingle.addRequest(URL + n + a, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
                nearPlayBean.getData().getData().addAll(response.getData().getData());
                nearPlayAdapter.setNearPlayBean(nearPlayBean);
                pullToRefreshListView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void startVolley(int page, String sth) {
        VolleySingle.addRequest(URL + page + sth, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
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
            case R.id.recent_pop_all:
                timePop.dismiss();
                playTimeTv.setText("全部");
                break;
            case R.id.recent_pop_thisweek:
                timePop.dismiss();
                playTimeTv.setText("本周");
                break;
            case R.id.recent_pop_thisweekend:
                timePop.dismiss();
                playTimeTv.setText("本周末");
                break;
            case R.id.recent_pop_nextweek:
                timePop.dismiss();
                playTimeTv.setText("下周");
                break;
            case R.id.recent_pop_id1:
                typePop.dismiss();
                playTypeTv.setText("Demo Day");
                startVolley(1, "&categoryId=1&pageSize=20");
                break;
            case R.id.recent_pop_id4:
                typePop.dismiss();
                playTypeTv.setText("氪空间");
                startVolley(1, "&categoryId=4&pageSize=20");
                break;
            case R.id.recent_pop_id5:
                typePop.dismiss();
                playTypeTv.setText("股权投资");
                startVolley(1, "&categoryId=5&pageSize=20");
                break;
            case R.id.recent_pop_id6:
                typePop.dismiss();
                playTypeTv.setText("企业服务");
                startVolley(1, "&categoryId=6&pageSize=20");
                break;
            case R.id.recent_pop_id7:
                typePop.dismiss();
                playTypeTv.setText("极速融资");
                startVolley(1, "&categoryId=7&pageSize=20");
                break;
            case R.id.recent_pop_idall:
                typePop.dismiss();
                playTypeTv.setText("全部");
                startVolley(1, "");
                break;


        }
    }


    private void showTimePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_time, null, false);
        timePop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.findViewById(R.id.recent_pop_all).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_thisweek).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_thisweekend).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_nextweek).setOnClickListener(this);
        timePop.setAnimationStyle(R.style.pop_anim);
    }

    private void showTypePop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_type, null, false);
        typePop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.findViewById(R.id.recent_pop_id1).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_id4).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_id5).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_id6).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_id7).setOnClickListener(this);
        view.findViewById(R.id.recent_pop_idall).setOnClickListener(this);
        typePop.setAnimationStyle(R.style.pop_anim);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
