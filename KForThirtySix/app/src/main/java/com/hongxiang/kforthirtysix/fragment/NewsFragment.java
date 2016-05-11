package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hongxiang.kforthirtysix.NewsBean;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.SearchActivity;
import com.hongxiang.kforthirtysix.adapter.NewsAdapter;

import org.json.JSONObject;

/**
 * Created by dllo on 16/5/9.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private ImageView search, menu;
    private DrawerLayout drawerLayout;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;


    @Override
    public void initView(View view) {
        search = (ImageView) view.findViewById(R.id.newsfragment_search);
        menu = (ImageView) view.findViewById(R.id.fragmentnews_menu);

        recyclerView = (RecyclerView) view.findViewById(R.id.news_RecyclerView);

    }

    @Override
    public void initData() {
        search.setOnClickListener(this);
        menu.setOnClickListener(this);
        newsAdapter = new NewsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);

        //=================>>>>>>>>>>>文字解析,将NewsBean传入Adapter
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up"
                , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                NewsBean bean = gson.fromJson(String.valueOf(response), NewsBean.class);
                newsAdapter.setNewsBean(bean);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);


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

                break;
        }
    }
}
