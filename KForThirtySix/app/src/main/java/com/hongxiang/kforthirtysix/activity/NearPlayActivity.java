package com.hongxiang.kforthirtysix.activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.NearPlayAdapter;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;


/**
 * Created by dllo on 16/5/19.
 * 近期活动
 */
public class NearPlayActivity extends AppCompatActivity {
    private NearPlayBean nearPlayBean;
    private NearPlayAdapter nearPlayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nearaplay);
        nearPlayAdapter = new NearPlayAdapter(this);
        listView = (ListView) findViewById(R.id.nearplay_listview);
        initData();


    }

    public void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<NearPlayBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/activity/list?page=1"
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
                nearPlayBean = response;
                nearPlayAdapter.setNearPlayBean(nearPlayBean);
                listView.setAdapter(nearPlayAdapter);

            }
        }, NearPlayBean.class);

        requestQueue.add(gsonRequest);

    }


}
