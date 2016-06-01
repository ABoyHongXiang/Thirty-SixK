package com.hongxiang.kforthirtysix.fragment.news;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.news.TvAdapter;
import com.hongxiang.kforthirtysix.bean.TvBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.VolleySingle;

/**
 * Created by dllo on 16/5/13.
 */
public class TvFragment extends BaseFragment {
    private TvBean tvBean;
    private TvAdapter tvAdapter;
    private ListView listView;
    private PullToRefreshListView pullToRefreshListView;
    private int n = 2;
    private static final String START_URL = "https://rong.36kr.com/api/mobi/news?pageSize=";
    private static final String END_URL = "&columnId=tv&pagingAction=up";

    @Override
    public void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.tv_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

    }

    @Override
    public void initData() {
        tvAdapter = new TvAdapter(getActivity());
        startVolley(20);
        listView = pullToRefreshListView.getRefreshableView();
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
    }

    private void startVolley(int page) {
        VolleySingle.addRequest(START_URL + page + END_URL, TvBean.class, new Response.Listener<TvBean>() {
            @Override
            public void onResponse(TvBean response) {
                tvBean = response;
                tvAdapter.setTvBean(tvBean);
                listView.setAdapter(tvAdapter);
                pullToRefreshListView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_tv;
    }
}