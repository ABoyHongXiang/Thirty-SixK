package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.DetailsBean;
import com.hongxiang.kforthirtysix.util.GsonRequest;

import java.net.URL;
import java.text.SimpleDateFormat;

import it.sephiroth.android.library.picasso.Picasso;

import static com.hongxiang.kforthirtysix.R.id.details_context;

/**
 * Created by dllo on 16/5/14.
 * 详情界面
 */
public class DetailsActivity extends AppCompatActivity {
    private DetailsBean detailsBean;
    String url;
    private TextView title, secondTitle, mcontext,writer;
    private ImageView writeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.avtivity_details);
        title = (TextView) findViewById(R.id.details_bigtitle);
        secondTitle = (TextView) findViewById(R.id.details_smalltitle);
        mcontext = (TextView) findViewById(R.id.details_context);
        writer = (TextView) findViewById(R.id.details_writer_name);
        writeImg = (ImageView) findViewById(R.id.writer_img);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initData();
        struct();
    }
    public void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        GsonRequest<DetailsBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "https://rong.36kr.com/api/mobi/news/" + url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<DetailsBean>() {
            @Override
            public void onResponse(DetailsBean response) {
                detailsBean = response;
                String url = detailsBean.getData().getUser().getAvatar();
                writer.setText(detailsBean.getData().getUser().getName());
                Picasso.with(getApplication()).load(url).transform(new PicassoCirclTransform()).resize(70, 70).centerCrop().error(R.mipmap.ic_launcher).into(writeImg);
                title.setText(detailsBean.getData().getTitle());
                secondTitle.setText(detailsBean.getData().getSummary());
                String datas = response.getData().getContent();
                mcontext.setMovementMethod(ScrollingMovementMethod.getInstance());
                mcontext.setMovementMethod(LinkMovementMethod.getInstance());
                mcontext.setText(Html.fromHtml(datas, imgGetter, null));

            }
        }, DetailsBean.class);
        requestQueue.add(gsonRequest);

    }


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

    public static void struct() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // or
                        // .detectAll()
                        // for
                        // all
                        // detectable
                        // problems
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }

}
