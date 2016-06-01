package com.hongxiang.kforthirtysix.fragment.news;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.MainActivity;
import com.hongxiang.kforthirtysix.bean.ImageviewBean;
import com.hongxiang.kforthirtysix.bean.InvestmentBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteText;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteTextDao;
import com.hongxiang.kforthirtysix.util.FavouritedaoSingle;
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
    private NewsBean newsBean;
    private int n = 2;
    private FavouriteTextDao favouriteTextDao;
    private List<FavouriteText> favouriteTexts;
    private ImageviewBean imageviewBean;
    private LayoutInflater inflater;
    private ViewPager mviewPager;
    /**
     * 用于小圆点图片
     */
    private List<ImageView> dotViewList;
    /**
     * 用于存放轮播效果图片
     */
    private List<ImageView> list;
    LinearLayout dotLayout;
    private int currentItem = 0;//当前页面
    boolean isAutoPlay = true;//是否自动轮播
    private ScheduledExecutorService scheduledExecutorService;
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
                startVolley(20);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                startVolley(20 * n);
                n = n + 1;

            }
        });
        //适配器初始化
        newsAdapter = new NewsAdapter(getActivity());
        //ListView绑定适配器
        listView.setAdapter(newsAdapter);
        //点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                String feedId = newsBean.getData().getData().get(position - 2).getFeedId();
                String imgurl = newsBean.getData().getData().get(position).getFeatureImg();
                favouriteTexts = new ArrayList<>();
                favouriteTextDao = FavouritedaoSingle.getInstance().getFavouriteTextDao();
                favouriteTexts = favouriteTextDao.queryBuilder().list();
                for (FavouriteText favouriteText : favouriteTexts) {
                    if (favouriteText.getUrlid().equals(feedId)) {
                        intent.putExtra("favourite", true);
                    }
                }
                intent.putExtra("imageurl", imgurl);
                intent.putExtra("url", feedId);
                Log.d("NewsAllFragment", imgurl);
                startActivity(intent);

            }
        });
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_header, null);
        listView.addHeaderView(view);
        //轮播图

        inflater = LayoutInflater.from(getActivity());
        mviewPager = (ViewPager) view.findViewById(R.id.viewpager);
        dotLayout = (LinearLayout) view.findViewById(R.id.title_point);
        dotLayout.removeAllViews();

        if (isAutoPlay) {
            startPlay();
        }

        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/roundpics/v4", ImageviewBean.class, new Response.Listener<ImageviewBean>() {
            @Override
            public void onResponse(ImageviewBean response) {
                imageviewBean = response;
                titleInitView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    private void titleInitView() {
        dotViewList = new ArrayList<ImageView>();
        list = new ArrayList<ImageView>();
        for (int i = 0; i < imageviewBean.getData().getPics().size(); i++) {
            ImageView dotView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 15;//设置小圆点的外边距
            params.rightMargin = 15;
            params.height = 15;//设置小圆点的大小
            params.width = 15;

            if (i == 0) {
                dotView.setBackgroundResource(R.mipmap.point_pressed);
            } else {

                dotView.setBackgroundResource(R.mipmap.point_unpressed);
            }
            dotLayout.addView(dotView, params);

            dotViewList.add(dotView);
            //上面是动态添加了四个小圆点
        }

        for (int i = 0; i < imageviewBean.getData().getPics().size(); i++) {
            ImageView img = (ImageView) inflater.inflate(R.layout.imageview, null);
            Picasso.with(getActivity()).load(imageviewBean.getData().getPics().get(i).getImgUrl()).into(img);
            list.add(img);
        }
        NewsHeadAdapter headAdapter = new NewsHeadAdapter((ArrayList) list);

        mviewPager.setAdapter(headAdapter);
        mviewPager.setCurrentItem(0);
        mviewPager.setOnPageChangeListener(new MyPageChangeListener());
    }


    //=================>>>>>>>>>>>文字解析,将NewsBean传入Adapter
    private void startVolley(int page) {
        VolleySingle.addRequest(START_URL + page + END_URL, NewsBean.class, new Response.Listener<NewsBean>() {
            @Override
            public void onResponse(NewsBean response) {
                Log.d("NewestFragment", "解析成功");
                newsBean = response;
                newsAdapter.setNewsBean(newsBean);
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

        return R.layout.fragment_allnews;
    }






    @Override
    public void onStop() {
        super.onStop();

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                mviewPager.setCurrentItem(currentItem);
            }
        }

    };


    /**
     * 开始轮播图切换
     */

    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }

    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (mviewPager) {
                currentItem = (currentItem + 1) % list.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;

                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;

                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (mviewPager.getCurrentItem() == mviewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                        mviewPager.setCurrentItem(0);

                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (mviewPager.getCurrentItem() == 0 && !isAutoPlay) {
                        mviewPager.setCurrentItem(mviewPager.getAdapter().getCount() - 1);

                    }
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            //这里面动态改变小圆点的被背景，来实现效果
            currentItem = pos;
            for (int i = 0; i < dotViewList.size(); i++) {
                if (i == pos) {
                    ((View) dotViewList.get(pos)).setBackgroundResource(R.mipmap.point_pressed);
                } else {
                    ((View) dotViewList.get(i)).setBackgroundResource(R.mipmap.point_unpressed);
                }
            }
        }

    }

}


