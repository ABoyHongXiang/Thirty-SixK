package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.LolDetailsAdapter;
import com.hongxiang.kforthirtysix.bean.LolBean;
import com.hongxiang.kforthirtysix.bean.LolDetailsBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;

import it.sephiroth.android.library.picasso.Picasso;

import static com.hongxiang.kforthirtysix.R.id.lol_details_head_title;

/**
 * Created by dllo on 16/5/20.
 */
public class LolDetailsActivity extends AppCompatActivity {
    private LolDetailsBean lolDetailsBean;
    private ListView listView;
    private LolDetailsAdapter lolDetailsAdapter;
    private String url;
    private TextView head_title, head_smalltitle;
    private ImageView headimg;
    private final static String START_URL = "http://lol.zhangyoubao.com/apis/rest/ItemsService/videos?catwordid=";
    private final static String END_URL = "&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438760163&p_=2834&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loldetails);
        //listview
        listView = (ListView) findViewById(R.id.lolDetails_listview);
        //绑定适配器
        lolDetailsAdapter = new LolDetailsAdapter(this);

        head_title = (TextView) findViewById(R.id.lol_details_head_title);
        head_smalltitle = (TextView) findViewById(R.id.lol_details_head_smalltitle);
        headimg = (ImageView) findViewById(R.id.lol_details_head_img);
        final Intent intent = getIntent();
        String id = intent.getStringExtra("urlid");//拼接url的id
        String title = intent.getStringExtra("head_title");
        String smalltitle = intent.getStringExtra("head_smalltitle");
        String imgurl = intent.getStringExtra("head_img");
        head_title.setText(title);
        head_smalltitle.setText(smalltitle);
        Picasso.with(this).load(imgurl).resize(120, 120).into(headimg);
        url = START_URL + id + END_URL;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<LolDetailsBean> gsonRequest = new GsonRequest<>(Request.Method.GET, url
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<LolDetailsBean>() {
            @Override
            public void onResponse(LolDetailsBean response) {
                lolDetailsBean = response;
                lolDetailsAdapter.setLolDetailsBean(lolDetailsBean);
                listView.setAdapter(lolDetailsAdapter);

            }


        }, LolDetailsBean.class);
        requestQueue.add(gsonRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(LolDetailsActivity.this, LolVideoActivity.class);
                intent1.putExtra("videourl", lolDetailsBean.getData().get(position).getVideo_url());
                startActivity(intent1);
            }
        });
    }
}
