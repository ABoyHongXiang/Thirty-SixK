package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.google.gson.Gson;
import com.hongxiang.kforthirtysix.MyPopWindow;
import com.hongxiang.kforthirtysix.NewsBean;
import com.hongxiang.kforthirtysix.NewsHeadAdapter;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.RecentFragment;
import com.hongxiang.kforthirtysix.activity.MainActivity;
import com.hongxiang.kforthirtysix.activity.SearchActivity;
import com.hongxiang.kforthirtysix.adapter.NewsAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/9.
 * 新闻Fragment
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private ImageView search, menu;
    private NewsAdapter newsAdapter;
    private RecyclerView recyclerView;
    private MyPopWindow popupWindow;
    private JsonObjectRequest request;
    private ViewPager viewpager;
    private NewsHeadAdapter newsHeadAdapter;
    private List<ImageView>imagviews;


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
        RecyclerViewHeader recyclerViewHeader = RecyclerViewHeader.fromXml(getContext(), R.layout.news_header);
        recyclerViewHeader.attachTo(recyclerView);
//        viewpager = (ViewPager) recyclerViewHeader.findViewById(R.id.news_viewpager);
//        newsHeadAdapter = new NewsHeadAdapter(getContext());
//        addImage();
//        newsHeadAdapter.setImageViews(imagviews);
//        viewpager.setAdapter(newsHeadAdapter);
        //=================>>>>>>>>>>>文字解析,将NewsBean传入Adapter
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        aaa("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up");
        requestQueue.add(request);


    }

    private void addImage() {
        imagviews =new ArrayList<>();
        imagviews.add(new ImageView(getContext()));
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
                //实例化SelectPicPopupWindow
                popupWindow = new MyPopWindow(getContext(), itemsOnClick);
                //显示窗口
                popupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
        }

    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            popupWindow.dismiss();
            switch (v.getId()) {
                case R.id.pop_recent:
                  Toast.makeText(getContext(), "敬请期待早期项目", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pop_tv:
                    Toast.makeText(getContext(), "敬请期待氪TV", Toast.LENGTH_SHORT).show();
                    break;


            }


        }

    };


    public void aaa(String url) {
        request = new JsonObjectRequest(url
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
    }


}



