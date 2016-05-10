package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.gson.Gson;
import com.hongxiang.kforthirtysix.BaseFragment;
import com.hongxiang.kforthirtysix.NewsBean;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private ImageView search, menu;
    private DrawerLayout drawerLayout;


    @Override
    public void initView(View view) {
        search = (ImageView) view.findViewById(R.id.newsfragment_search);
        menu = (ImageView) view.findViewById(R.id.fragmentnews_menu);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawlayout);

    }

    @Override
    public void initData() {
        search.setOnClickListener(this);
        menu.setOnClickListener(this);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsBean  bean = gson.fromJson(String.valueOf(response), NewsBean.class);
                Log.d("NewsFragment", "bean.getData().getData().get(1):" + bean.getData().getData());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsfragment_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fragmentnews_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }
}
