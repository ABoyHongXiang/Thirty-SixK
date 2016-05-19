package com.hongxiang.kforthirtysix.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.FoundPeopleAdapter;
import com.hongxiang.kforthirtysix.bean.FoundPeopleBean;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;

/**
 * Created by dllo on 16/5/16.
 * 寻找投资人
 */
public class FoundPeopleActivity extends AppCompatActivity {
    private FoundPeopleBean foundPeopleBean;
    private FoundPeopleAdapter foundPeopleAdapter;
    private ListView listView ;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_foundpeople);
        listView  = (ListView) findViewById(R.id.foundpeople_listview);
        back = (ImageView) findViewById(R.id.foundpeople_back);
        foundPeopleAdapter =new FoundPeopleAdapter(this) ;
        listView.setAdapter(foundPeopleAdapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<FoundPeopleBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<FoundPeopleBean>() {
            @Override
            public void onResponse(FoundPeopleBean response) {
                foundPeopleBean = response;
                foundPeopleAdapter.setFoundPeopleBean(foundPeopleBean);

            }
        }, FoundPeopleBean.class);
        requestQueue.add(gsonRequest);
    }
}
