package com.hongxiang.kforthirtysix.activity.found;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

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
public class NearPlayActivity extends AppCompatActivity implements View.OnClickListener {
    private static String URL = "https://rong.36kr.com/api/mobi/activity/list?page=1";
    private NearPlayBean nearPlayBean; //近期活动实体类
    private NearPlayAdapter nearPlayAdapter;//近期活动适配器
    private ListView listView;
    private View playTime, playType;
    private TextView playTimeTv, playTypeTv;
    private PopupWindow timePop, typePop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//隐藏标题栏
        setContentView(R.layout.activity_nearaplay);
        nearPlayAdapter = new NearPlayAdapter(this);
        listView = (ListView) findViewById(R.id.nearplay_listview);
        playTime = findViewById(R.id.play_time);
        playType = findViewById(R.id.play_type);
        playTimeTv = (TextView) findViewById(R.id.play_time_tv);
        playTime.setOnClickListener(this);
        startVolley();
        showTypePop();
        showTimePop();


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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_time:

                if (timePop != null && timePop.isShowing()) {
                    timePop.dismiss();

                } else {
                    timePop.showAsDropDown(playTime);
                }
                break;
            case R.id.play_type:

                break;

        }
    }

    private void showTypePop() {


    }

    private void showTimePop() {
        timePop = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0x70000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        timePop.setBackgroundDrawable(dw);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_time, null, false);
        timePop.setContentView(view);
    }

}
