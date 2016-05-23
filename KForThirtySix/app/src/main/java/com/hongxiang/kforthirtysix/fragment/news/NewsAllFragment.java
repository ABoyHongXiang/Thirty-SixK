package com.hongxiang.kforthirtysix.fragment.news;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.VolleySingle;
import com.hongxiang.kforthirtysix.activity.news.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.news.NewsAdapter;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.adapter.news.NewsHeadAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class NewsAllFragment extends BaseFragment {
    private static final String START_URL = "https://rong.36kr.com/api/mobi/news?pageSize=";
    private static final String END_URL = "&columnId=all&pagingAction=up";
    PullToRefreshListView pullToRefreshListView;
    private NewsAdapter newsAdapter;
    private ListView listView;
    private ViewPager viewpager;
    private NewsHeadAdapter newsHeadAdapter;
    private List<ImageView> imagviews;
    private ImageView imageView1, imageView2, imageView3;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem;
    private NewsBean newsBean;
    private int n = 2;

    @Override
    public void initView(View view) {
        pullToRefreshListView = (PullToRefreshListView) view.findViewById(R.id.news_listview);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
    }

    @Override
    public void initData() {
        startVolley(20);
        listView = pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(20*n);
                pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(20 * n);
                pullToRefreshListView.onRefreshComplete();
                n = n + 1;

            }
        });
        //适配器初始化
        newsAdapter = new NewsAdapter(getContext());
        //ListView绑定适配器
        listView.setAdapter(newsAdapter);
        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                String a = newsBean.getData().getData().get(position).getFeedId();
                intent.putExtra("url", a);
                startActivity(intent);
            }
        });
        View view = LayoutInflater.from(getContext()).inflate(R.layout.news_header, null);
        listView.addHeaderView(view);
        //头布局轮播图的ViewPager
        viewpager = (ViewPager) view.findViewById(R.id.viewpager);
        //头布局适配器初始化
        newsHeadAdapter = new NewsHeadAdapter(getContext());
        //添加轮播图图片
        addImage();
        //头布局适配器索要图片集合
        newsHeadAdapter.setImageViews(imagviews);
        //ViewPager设置适配器
        viewpager.setAdapter(newsHeadAdapter);


    }

    //=================>>>>>>>>>>>文字解析,将NewsBean传入Adapter
    private void startVolley(int page) {
        VolleySingle.addRequest(START_URL + page + END_URL, NewsBean.class, new Response.Listener<NewsBean>() {
            @Override
            public void onResponse(NewsBean response) {
                Log.d("NewestFragment", "解析成功");
                newsBean = response;
                newsAdapter.setNewsBean(newsBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    private void addImage() {
        addImssss();
        imageView1 = new ImageView(getContext());
        imageView2 = new ImageView(getContext());
        imageView3 = new ImageView(getContext());
        imagviews = new ArrayList<>();
        imagviews.add(imageView1);
        imagviews.add(imageView2);
        imagviews.add(imageView3);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160512/picad65631h5733f9d2.jpg").into(imageView1);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160509/picad65506h573031f7.jpg").into(imageView2);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160504/picad65250h5729a7ea.jpg").into(imageView3);
    }

    private void addImssss() {

    }


    @Override
    public int initLayout() {

        return R.layout.fragment_allnews;
    }


    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 1, 2, TimeUnit.SECONDS);

    }


    class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imagviews.size();
            mHandler.sendEmptyMessage(0);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewpager.setCurrentItem(currentItem);
        }
    };

    @Override
    public void onStop() {
        super.onStop();

    }


}
