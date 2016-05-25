package com.hongxiang.kforthirtysix.activity.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.DetailsBean;
import com.hongxiang.kforthirtysix.util.VolleySingle;

import java.net.URL;



/**
 * Created by dllo on 16/5/25.
 */
public class FavouriteDetailsActivity extends AppCompatActivity {
    private TextView title, writer, context;
    String mUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        struct();
        getSupportActionBar().hide();
        setContentView(R.layout.actibvity_favourite_details);
        title = (TextView) findViewById(R.id.favourite_details_title);
        writer = (TextView) findViewById(R.id.favourite_details_writer);
        context = (TextView) findViewById(R.id.favourite_details_context);

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        String mWriter = intent.getStringExtra("writer");
        mUrl = intent.getStringExtra("url");
        intData();
        title.setText(mTitle);
        writer.setText(mWriter);


    }

    private void intData() {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news/" + mUrl, DetailsBean.class, new Response.Listener<DetailsBean>() {
            @Override
            public void onResponse(DetailsBean response) {

                String datas = response.getData().getContent();
                context.setMovementMethod(ScrollingMovementMethod.getInstance());
                context.setMovementMethod(LinkMovementMethod.getInstance());
                context.setText(Html.fromHtml(datas, imgGetter, null));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    //加载Html的方法
    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());

            return drawable;
        }
    };

    //加载html的方法
    public static void struct() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }


}
