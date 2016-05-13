package com.hongxiang.kforthirtysix;

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
import com.hongxiang.kforthirtysix.activity.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.NewsAdapter;
import com.hongxiang.kforthirtysix.adapter.NewsHeadAdapter;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class NewsAllFragment extends BaseFragment implements NewsRvListener {

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
        newsAdapter = new NewsAdapter(getContext());
        newsAdapter.setNewsRvListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);
        RecyclerViewHeader recyclerViewHeader = RecyclerViewHeader.fromXml(getContext(), R.layout.news_header);
        recyclerViewHeader.attachTo(recyclerView);
        viewpager = (ViewPager) recyclerViewHeader.findViewById(R.id.viewpager);
        newsHeadAdapter = new NewsHeadAdapter();
        addImage();
        newsHeadAdapter.setImageViews(imagviews);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160512/picad65631h5733f9d2.jpg").into(imageView1);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160509/picad65506h573031f7.jpg").into(imageView2);
        Picasso.with(getActivity()).load("http://avatar.anzogame.com/pic_v1/lol/news/20160504/picad65250h5729a7ea.jpg").into(imageView3);
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


    @Override
    public void Onclick(int pos) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
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


