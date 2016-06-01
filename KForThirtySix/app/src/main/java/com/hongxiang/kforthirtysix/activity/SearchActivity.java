package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.news.DetailsActivity;
import com.hongxiang.kforthirtysix.adapter.SearchAdapter;
import com.hongxiang.kforthirtysix.bean.SearchBean;
import com.hongxiang.kforthirtysix.util.VolleySingle;

/**
 * Created by dllo on 16/5/10.
 * 搜索界面  点击放大镜就会跳转的页面
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String START_URL = "https://rong.36kr.com/api/mobi/news/search?keyword=";
    private static final String END_URL = "&page=1&pagesize=20";
    private EditText editText;
    private TextView exitTextview;
    private ImageView searchBtn, searchIcon;
    private SearchAdapter searchAdapter;
    private ListView listview;
    private SearchBean searchBean;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.search_sth);
        exitTextview = (TextView) findViewById(R.id.search_exit);
        searchBtn = (ImageView) findViewById(R.id.search_imgbtn);
        searchIcon = (ImageView) findViewById(R.id.search_icon);
        listview = (ListView) findViewById(R.id.search_listview);
        searchAdapter = new SearchAdapter(this);
        searchBtn.setOnClickListener(this);
        exitTextview.setOnClickListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(SearchActivity.this, DetailsActivity.class);
                String feedId = searchBean.getData().getData().get(position).getFeedId();
                intent.putExtra("url",feedId);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //取消按钮
            case R.id.search_exit:
                finish();
                break;
            //搜索按钮
            case R.id.search_imgbtn:
                searchIcon.setVisibility(View.INVISIBLE);
                String msth = String.valueOf(editText.getText());

                startVolley(msth);
                break;
        }
    }

    private void startVolley(String sth) {
        Log.d("SearchActivity", START_URL + sth + END_URL);
        VolleySingle.addRequest(START_URL + sth + END_URL, SearchBean.class, new Response.Listener<SearchBean>() {
            @Override
            public void onResponse(SearchBean response) {
                searchBean = response;
                searchAdapter.setSearchBean(searchBean);
                if (searchBean.getData().getData().size() < 1) {
                    Toast.makeText(SearchActivity.this, "没有您想搜索的项", Toast.LENGTH_SHORT).show();
                } else {
                    listview.setAdapter(searchAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }
}
