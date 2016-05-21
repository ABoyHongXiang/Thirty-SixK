package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.WriterPopAdapter;
import com.hongxiang.kforthirtysix.bean.DetailsBean;

import com.hongxiang.kforthirtysix.bean.WriterBean;


import java.net.URL;


import it.sephiroth.android.library.picasso.Picasso;


/**
 * Created by dllo on 16/5/14.
 * 新闻的详情界面
 */
public class DetailsActivity extends AppCompatActivity {
    private final static String START_URL = "https://rong.36kr.com/api/mobi/news/";
    private final static String END_URL = "/author-region";
    private DetailsBean detailsBean;//详情的数据类
    private String url;//用来接收上个界面传来的id ,拼接网址
    private TextView title, secondTitle, mcontext, writer, num, read;
    private ImageView writeImg, menudown;
    private PopupWindow pop;
    private WriterBean writerBean;
    private WriterPopAdapter writerPopAdapter;
    private ListView listView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//使app标题栏消失
        setContentView(R.layout.avtivity_details);
        //内容的组件
        writerPopAdapter = new WriterPopAdapter(this);
        title = (TextView) findViewById(R.id.details_bigtitle);
        secondTitle = (TextView) findViewById(R.id.details_smalltitle);
        mcontext = (TextView) findViewById(R.id.details_context);
        //标题的组件
        relativeLayout = (RelativeLayout) findViewById(R.id.writer_layout);
        writer = (TextView) findViewById(R.id.details_writer_name);
        writeImg = (ImageView) findViewById(R.id.writer_img);
        menudown = (ImageView) findViewById(R.id.writer_menu_img);

        //标题的作者图片点击事件
        writeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int writerId = detailsBean.getData().getPostId();
                Intent intent = new Intent(DetailsActivity.this, WriterActivity.class);
                intent.putExtra("writerid", writerId);
                startActivity(intent);

            }
        });
        //接收上一个界面传来的id ,拼接网址
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        //解析详情的方法
        initDataDetails();
        //加载html的方法
        struct();
        //显示popupWindow
        initDataWriter();
        showpop();
        //标题文字的点击事件
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop.isShowing()) {
                    pop.dismiss();
                    menudown.setImageResource(R.mipmap.menudown);
                } else {
                    pop.showAsDropDown(writeImg);
                    menudown.setImageResource(R.mipmap.menuup);
                }

            }
        });
    }

    //解析作者
    private void initDataWriter() {
        VolleySingle.addRequest(START_URL + url + END_URL, WriterBean.class, new Response.Listener<WriterBean>() {
            @Override
            public void onResponse(WriterBean response) {
                Log.d("WriterActivity", "翔哥第一次解析成功" + url);
                writerBean = response;
                Log.d("DetailsActivity", "writerBean.getData().getTotalCount():" + writerBean.getData().getTotalCount());
                num.setText(writerBean.getData().getTotalCount() + "篇");
                read.setText(writerBean.getData().getTotalView() + "次");
                writerPopAdapter.setWriterBean(writerBean);
                listView.setAdapter(writerPopAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void showpop() {
        pop = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //绑定布局
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_writer, null);
        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0x70000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        pop.setBackgroundDrawable(dw);
        pop.setContentView(view);
        num = (TextView) view.findViewById(R.id.pop_writer_num);
        read = (TextView) view.findViewById(R.id.pop_writer_readnum);
        listView = (ListView) view.findViewById(R.id.pop_wirter_listview);


    }

    //解
    public void initDataDetails() {
        //解析数据
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news/" + url, DetailsBean.class, new Response.Listener<DetailsBean>() {
            @Override
            public void onResponse(DetailsBean response) {
                detailsBean = response;
                String url = detailsBean.getData().getUser().getAvatar();
                //标题栏的数据设置
                writer.setText(detailsBean.getData().getUser().getName());
                Picasso.with(getApplication()).load(url).transform(new PicassoCirclTransform()).resize(100, 100).centerCrop().error(R.mipmap.ic_launcher).into(writeImg);
                //大标题小标题
                title.setText(detailsBean.getData().getTitle());
                secondTitle.setText(detailsBean.getData().getSummary());
                //以下是TextView显示Html
                String datas = response.getData().getContent();
                mcontext.setMovementMethod(ScrollingMovementMethod.getInstance());
                mcontext.setMovementMethod(LinkMovementMethod.getInstance());
                mcontext.setText(Html.fromHtml(datas, imgGetter, null));

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
