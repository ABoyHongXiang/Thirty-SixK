package com.hongxiang.kforthirtysix;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.GsonRequest;

/**
 * Created by dllo on 16/5/13.
 */
public class TvFragment extends BaseFragment {
    private TvBean tvBean;
    private TvAdapter tvAdapter;
    private ListView listView;

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.tv_listview);
    }

    @Override
    public void initData() {
        tvAdapter = new TvAdapter(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<TvBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=tv&pagingAction=up", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<TvBean>() {
            @Override
            public void onResponse(TvBean response) {
                tvBean = response;
                tvAdapter.setTvBean(tvBean);

            }
        }, TvBean.class);
        requestQueue.add(gsonRequest);
        listView.setAdapter(tvAdapter);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_tv;
    }
}
