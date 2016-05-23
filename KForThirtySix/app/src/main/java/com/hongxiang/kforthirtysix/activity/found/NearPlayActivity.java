package com.hongxiang.kforthirtysix.activity.found;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.adapter.found.NearPlayAdapter;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;
import com.hongxiang.kforthirtysix.util.VolleySingle;


/**
 * Created by dllo on 16/5/19.
 * 近期活动
 */
public class NearPlayActivity extends AppCompatActivity {
    private  static String URL = "https://rong.36kr.com/api/mobi/activity/list?page=1";
    private NearPlayBean nearPlayBean; //近期活动实体类
    private NearPlayAdapter nearPlayAdapter;//近期活动适配器
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_nearaplay);
        nearPlayAdapter = new NearPlayAdapter(this);
        listView = (ListView) findViewById(R.id.nearplay_listview);
        startVolley();


    }

    private void startVolley() {
        VolleySingle.addRequest(URL, NearPlayBean.class, new Response.Listener<NearPlayBean>() {
            @Override
            public void onResponse(NearPlayBean response) {
                Log.d("NearPlayActivity", "近期活动数据解析完成");
                nearPlayBean = response;
                nearPlayAdapter.setNearPlayBean(nearPlayBean);
                listView.setAdapter(nearPlayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }




}
