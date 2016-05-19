package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.RecentAdapter;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.bean.RecentBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.GsonRequest;

import java.util.List;

/**
 * Created by dllo on 16/5/12.
 */
public class RecentFragment extends BaseFragment {
    private RecentBean recentBean;
    private RecentAdapter recentAdapter;
    private ListView listView;

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.recent_listview);

    }

    @Override
    public void initData() {
        recentAdapter = new RecentAdapter(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<RecentBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=67&pagingAction=up", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<RecentBean>() {
            @Override
            public void onResponse(RecentBean response) {
                Log.d("aaaa", "解析成功");
                recentBean = response;
                recentAdapter.setRecentBean(recentBean);


            }
        }, RecentBean.class);
        requestQueue.add(gsonRequest);
        listView.setAdapter(recentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("url",recentBean.getData().getData().get(position).getFeedId());
                startActivity(intent);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_recent;
    }
}
