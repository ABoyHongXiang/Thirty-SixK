package com.hongxiang.kforthirtysix.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.news.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.mine.FavouriteAdapter;
import com.hongxiang.kforthirtysix.sql.FavouriteText;
import com.hongxiang.kforthirtysix.sql.FavouriteTextDao;
import com.hongxiang.kforthirtysix.util.GreendaoSingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/25.
 */
public class FavouriteActivity extends AppCompatActivity {
    private ListView listView;
    private FavouriteAdapter favouriteAdapter;
    private List<FavouriteText> favouriteTexts;
    private FavouriteTextDao favouriteTextDao;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_favourite);
        listView = (ListView) findViewById(R.id.favourite_listview);
        back = (ImageView) findViewById(R.id.favourite_back);
        favouriteAdapter = new FavouriteAdapter(this);
        favouriteTexts = new ArrayList<>();
        favouriteTextDao = GreendaoSingle.getInstance().getPersonDao();
        favouriteTexts = favouriteTextDao.queryBuilder().list();
        favouriteAdapter.setFavouriteTexts(favouriteTexts);
        listView.setAdapter(favouriteAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavouriteActivity.this, DetailsActivity.class);
                intent.putExtra("url",favouriteTexts.get(position).getUrlid());
                intent.putExtra("favourite", true);
                startActivity(intent);
            }
        });


    }


}
