package com.hongxiang.kforthirtysix.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.BaseActivity;
import com.hongxiang.kforthirtysix.activity.news.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.mine.FavouriteAdapter;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteText;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteTextDao;
import com.hongxiang.kforthirtysix.util.FavouritedaoSingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/25.
 * <p>
 * 收藏Activity
 */
public class FavouriteActivity extends BaseActivity {
    private ListView listView;
    private FavouriteAdapter favouriteAdapter;
    private List<FavouriteText> favouriteTexts;
    private FavouriteTextDao favouriteTextDao;
    private ImageView back;

    @Override
    protected int getLayout() {
        return R.layout.activity_favourite;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.favourite_listview);
        back = (ImageView) findViewById(R.id.favourite_back);

    }

    @Override
    protected void initData() {
        favouriteAdapter = new FavouriteAdapter(this);
        favouriteTexts = new ArrayList<>();
        favouriteTextDao = FavouritedaoSingle.getInstance().getFavouriteTextDao();
        favouriteTexts = favouriteTextDao.queryBuilder().list();
        favouriteAdapter.setFavouriteTexts(favouriteTexts);
        listView.setAdapter(favouriteAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        //item的点击事件,跳转至详情
        //url==>>用来详情界面的解析
        //favourite==>>详情界面接受判断让收藏图标实心
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavouriteActivity.this, DetailsActivity.class);
                intent.putExtra("url", favouriteTexts.get(position).getUrlid());
                intent.putExtra("favourite", true);
                startActivity(intent);
            }
        });

    }

    /**
     * 在Restart里重新将数据库里的东西取出来传给适配器
     * 实现了===>>在收藏界面进去的详情中,取消收藏,返回后收藏列表中该文章消失.
     * 简单来说就是刷新.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        favouriteTexts = favouriteTextDao.queryBuilder().list();
        favouriteAdapter.setFavouriteTexts(favouriteTexts);

    }
}
