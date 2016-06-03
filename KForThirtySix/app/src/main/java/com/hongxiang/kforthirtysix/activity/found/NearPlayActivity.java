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
import com.hongxiang.kforthirtysix.activity.BaseActivity;
import com.hongxiang.kforthirtysix.adapter.found.NearPlayAdapter;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.util.VolleySingle;

/**
 * Created by dllo on 16/5/19.
 * 近期活动
 */
public class NearPlayActivity extends BaseActivity implements View.OnClickListener {
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
    protected int getLayout() {
        return R.layout.activity_nearaplay;
    }
    @Override
    protected void initView() {
        getSupportActionBar().hide();
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.nearplay_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        listView = pullToRefreshListView.getRefreshableView();
        playTime = findViewById(R.id.play_time);
        playType = findViewById(R.id.play_type);
        playTimeTv = (TextView) findViewById(R.id.play_time_tv);
        playTypeTv = (TextView) findViewById(R.id.play_type_tv);
        playTime.setOnClickListener(this);
        playType.setOnClickListener(this);
       //两个popUpWindow
        showTypePop();
        showTimePop();


    }

    @Override
    protected void initData() {
        nearPlayAdapter = new NearPlayAdapter(this);
        startVolley(1, "");
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                switch (playTypeTv.getText().toString()) {
                    //下拉刷新的监听,保证每次刷新的的当前类型的数据,并把上拉加载时候需要的变量变成初始值;
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
                    //上拉加载的监听,保证没次加载的都是当前类型的数据
                    case "Demo Day":
                        String sth1 = "&categoryId=1&pageSize=" + i;
                        pushVolley(1, sth1);
                        i = i + 20;

                        break;
                    case "氪空间":
                        String sth4 = "&categoryId=4&pageSize=" + q;
                        pushVolley(1, sth4);
                        q = q + 20;

                        break;
                    case "股权投资":
                        String sth5 = "&categoryId=5&pageSize=" + w;
                        pushVolley(1, sth5);
                        w = w + 20;
                        break;
                    case "企业服务":
                        String sth6 = "&categoryId=6&pageSize=" + e;
                        pushVolley(1, sth6);
                        e = e + 20;
                        break;
                    case "极速融资":
                        String sth7 = "&categoryId=7&pageSize=" + r;
                        pushVolley(1, sth7);
                        r = r + 20;
                        break;
                    case "全部":
                        pushVolley(n, "");
                        n = n + 1;
                        break;
                }


            }
        });

    }

    /**
     * 刷新时候调用的解析方法,解析成功时向已有的数据类里加入新解析的数据类
     * n是页数,即每次加载需要改变的值,除了全部类型需要改变n,其他类型一直是1, 因为全部类型的url和其他类型不同
     * a是一小段网址,拼接后会是不同类型的接口
     * @param n
     * @param a
     */
    private void pushVolley(int n, String a) {
        VolleySingle.addRequest(URL + n + a, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {

                //解析成功时向已有的数据类里加入新解析的数据类
                nearPlayBean.getData().getData().addAll(response.getData().getData());
                nearPlayAdapter.setNearPlayBean(nearPlayBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        listView.setAdapter(nearPlayAdapter);
        pullToRefreshListView.onRefreshComplete();
    }

    /**
     * 与上方法一样,区别在于这只是解析数据的方法 只是没有加载新数据并添加到新的数据类;
     * @param page
     * @param sth
     */
    private void startVolley(int page, String sth) {
        VolleySingle.addRequest(URL + page + sth, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
                nearPlayBean = response;
                nearPlayAdapter.setNearPlayBean(nearPlayBean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        listView.setAdapter(nearPlayAdapter);
        pullToRefreshListView.onRefreshComplete();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_time:
                //其他window显示==>>其他消失==>>显示自己
                //其他window不显示==>>显示自己
                //自己window显示==>>消失
                //自己window不显示==>>显示自己
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
                //同上
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
            //点击,换文字,换接口,关window
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


}
