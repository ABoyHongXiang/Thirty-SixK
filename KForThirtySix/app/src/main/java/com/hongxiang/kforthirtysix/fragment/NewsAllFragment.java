package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.NewRvListener;
import com.hongxiang.kforthirtysix.activity.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.NewsAdapter;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.childfragment.NewsHeadAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class NewsAllFragment extends BaseFragment implements NewRvListener {

    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private ViewPager viewpager;
    private NewsHeadAdapter newsHeadAdapter;
    private List<ImageView> imagviews;
    private ImageView imageView1, imageView2, imageView3;
    private ScheduledExecutorService scheduledExecutorService;
    private int currentItem;
    private NewsBean newsBean;


    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.news_RecyclerView);

    }

    @Override
    public void initData() {
        //适配器初始化
        newsAdapter = new NewsAdapter(getContext());
        //RecyclerView设置监听
        newsAdapter.setNewRvListener(this);
        //RecyclerView的布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //RecyclerView绑定适配器
        recyclerView.setAdapter(newsAdapter);
        //RecyclerView设置头布局
        RecyclerViewHeader recyclerViewHeader = RecyclerViewHeader.fromXml(getContext(), R.layout.news_header);
        recyclerViewHeader.attachTo(recyclerView);
        //头布局轮播图的ViewPager
        viewpager = (ViewPager) recyclerViewHeader.findViewById(R.id.viewpager);
        //头布局适配器初始化
        newsHeadAdapter = new NewsHeadAdapter(getContext());
        //添加轮播图图片
        addImage();
        //头布局适配器索要图片集合
        newsHeadAdapter.setImageViews(imagviews);
        //ViewPager设置适配器
        viewpager.setAdapter(newsHeadAdapter);
        //=================>>>>>>>>>>>文字解析,将NewsBean传入Adapter
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<NewsBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up\n", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<NewsBean>() {
            @Override
            public void onResponse(NewsBean response) {
                Log.d("NewestFragment", "解析成功");
                newsBean = response;
                newsAdapter.setNewsBean(newsBean);

            }
        }, NewsBean.class);
        requestQueue.add(gsonRequest);
    }


    private void addImage() {
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


    @Override
    public int initLayout() {

        return R.layout.fragment_allnews;
    }



    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 1, 1, TimeUnit.SECONDS);

    }

    //
    @Override
    public void Onclick(int pos) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        String a = newsBean.getData().getData().get(pos).getFeedId();
        intent.putExtra("url",a);
        startActivity(intent);
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
