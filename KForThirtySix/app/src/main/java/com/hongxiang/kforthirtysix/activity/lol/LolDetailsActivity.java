package com.hongxiang.kforthirtysix.activity.lol;

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
import com.hongxiang.kforthirtysix.adapter.lol.LolDetailsAdapter;
import com.hongxiang.kforthirtysix.bean.LolDetailsBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.VolleySingle;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/20.
 * Lol视频列表的Activity
 */
public class LolDetailsActivity extends AppCompatActivity {
    private LolDetailsBean lolDetailsBean;//lol视频列表的实体类
    private ListView listView;
    private LolDetailsAdapter lolDetailsAdapter; //lol视频列表的适配器
    private String url;
    private TextView head_title, head_smalltitle;//界面上方标题栏的大小标题
    private ImageView headimg;//上方标题栏的头像
    private final static String START_URL = "http://lol.zhangyoubao.com/apis/rest/ItemsService/videos?catwordid=";
    private final static String END_URL = "&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438760163&p_=2834&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隐藏标题栏
        setContentView(R.layout.activity_loldetails);
        listView = (ListView) findViewById(R.id.lolDetails_listview);
        lolDetailsAdapter = new LolDetailsAdapter(this); //绑定适配器
        head_title = (TextView) findViewById(R.id.lol_details_head_title);
        head_smalltitle = (TextView) findViewById(R.id.lol_details_head_smalltitle);
        headimg = (ImageView) findViewById(R.id.lol_details_head_img);
        //接受上个Fragment传来的值得方法
        intentReceive();
        //解析数据的方法
        startVolley();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(LolDetailsActivity.this, LolVideoActivity.class);
                intent1.putExtra("videourl", lolDetailsBean.getData().get(position).getVideo_url());
                startActivity(intent1);
            }
        });
    }
    //解析数据的方法
    private void startVolley() {

        VolleySingle.addRequest(url, LolDetailsBean.class, new Response.Listener<LolDetailsBean>() {
            @Override
            public void onResponse(LolDetailsBean response) {
                Log.d("LolDetailsActivity", "Lol视频列表的数据解析完成");
                lolDetailsBean = response;
                lolDetailsAdapter.setLolDetailsBean(lolDetailsBean);
                listView.setAdapter(lolDetailsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


    //通过Intent接受从上个Fragment传来的值;
    private void intentReceive() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("urlid");//拼接url的id
        String title = intent.getStringExtra("head_title");//标题栏的大标题
        String smalltitle = intent.getStringExtra("head_smalltitle");//标题栏的小标题
        String imgurl = intent.getStringExtra("head_img");//标题栏的头像
        head_title.setText(title);
        head_smalltitle.setText(smalltitle);
        Picasso.with(this).load(imgurl).resize(120, 120).into(headimg);
        url = START_URL + id + END_URL;
    }
}
