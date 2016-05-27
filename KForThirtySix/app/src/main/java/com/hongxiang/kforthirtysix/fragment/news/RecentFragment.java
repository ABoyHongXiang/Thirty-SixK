package com.hongxiang.kforthirtysix.fragment.news;

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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.news.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.news.RecentAdapter;
import com.hongxiang.kforthirtysix.bean.RecentBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.sql.FavouriteText;
import com.hongxiang.kforthirtysix.sql.FavouriteTextDao;
import com.hongxiang.kforthirtysix.util.GreendaoSingle;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.VolleySingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/12.
 */
public class RecentFragment extends BaseFragment {

    private static final String START_URL = "https://rong.36kr.com/api/mobi/news?pageSize=";
    private static final String END_URL = "&columnId=67&pagingAction=up";
    private RecentBean recentBean;
    private RecentAdapter recentAdapter;
    private ListView listView;
    private PullToRefreshListView pullToRefreshListView;
    private int n;
    private FavouriteTextDao favouriteTextDao;
    private List<FavouriteText> favouriteTexts;

    @Override
    public void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.recent_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);


    }

    @Override
    public void initData() {
        listView = pullToRefreshListView.getRefreshableView();
        recentAdapter = new RecentAdapter(getContext());
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
        listView.setAdapter(recentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                String a = recentBean.getData().getData().get(position-1).getFeedId();
                favouriteTexts = new ArrayList<>();
                favouriteTextDao = GreendaoSingle.getInstance().getPersonDao();
                favouriteTexts = favouriteTextDao.queryBuilder().list();
                intent.putExtra("url",a);
                for (FavouriteText favouriteText : favouriteTexts) {
                    if (favouriteText.getUrlid().equals(a)) {
                        intent.putExtra("favourite", true);

                    }
                }
                startActivity(intent);

            }

        });
    }

    private void startVolley(int page) {

        VolleySingle.addRequest(START_URL + page + END_URL, RecentBean.class, new Response.Listener<RecentBean>() {
            @Override
            public void onResponse(RecentBean response) {
                recentBean = response;
                recentAdapter.setRecentBean(recentBean);
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
        return R.layout.fragment_recent;
    }
}
