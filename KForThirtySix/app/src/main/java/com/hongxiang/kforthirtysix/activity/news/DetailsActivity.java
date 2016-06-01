package com.hongxiang.kforthirtysix.activity.news;

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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteText;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteTextDao;
import com.hongxiang.kforthirtysix.util.FavouritedaoSingle;
import com.hongxiang.kforthirtysix.util.MyScrollView;
import com.hongxiang.kforthirtysix.util.MySharePopWindow;
import com.hongxiang.kforthirtysix.util.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.news.WriterPopAdapter;
import com.hongxiang.kforthirtysix.bean.DetailsBean;

import com.hongxiang.kforthirtysix.bean.WriterBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;


import java.net.URL;


import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/14.
 * 新闻的详情界面
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String START_URL = "https://rong.36kr.com/api/mobi/news/";
    private final static String END_URL = "/author-region";
    private DetailsBean detailsBean;//详情的数据类
    private String url, imgurl;//用来接收上个界面传来的id ,拼接网址
    private TextView title, secondTitle, mcontext, writer, num, read, writeline;
    private ImageView writeImg, menudown, heart;
    private PopupWindow pop;
    private WriterBean writerBean;
    private WriterPopAdapter writerPopAdapter;
    private ListView listView;
    private RelativeLayout relativeLayout;
    private MyScrollView scrollView;
    private LinearLayout linearLayout;
    Animation animation_in, animation_out;
    private boolean heartBoolean = true;
    private boolean favourite;
    private MySharePopWindow sharepop;
    private FavouriteTextDao favouriteTextDao;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//使app标题栏消失
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
        setContentView(R.layout.avtivity_details);
        //数据库的对象
        favouriteTextDao = FavouritedaoSingle.getInstance().getFavouriteTextDao();
        scrollView = (MyScrollView) findViewById(R.id.details_scrollview);
        linearLayout = (LinearLayout) findViewById(R.id.details_bottom_title);
        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_in);
        animation_out = AnimationUtils.loadAnimation(this, R.anim.anim_out);
        ColorDrawable dw = new ColorDrawable(0x70000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        linearLayout.setBackgroundDrawable(dw);
        scrollView.setScrollListener(new MyScrollView.ScrollListener() {
            @Override
            public void scrollDownOritention(int oritention) {
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.startAnimation(animation_in);
            }

            @Override
            public void scrollUpOritention(int oritention) {
                linearLayout.setVisibility(View.INVISIBLE);

            }
        });

        //内容的组件
        writerPopAdapter = new WriterPopAdapter(this);
        title = (TextView) findViewById(R.id.details_bigtitle);
        secondTitle = (TextView) findViewById(R.id.details_smalltitle);
        mcontext = (TextView) findViewById(R.id.details_context);
        mcontext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pop.dismiss();
                return false;
            }
        });
        writeline = (TextView) findViewById(R.id.details_line);
        //顶部标题的组件
        relativeLayout = (RelativeLayout) findViewById(R.id.writer_layout);
        writer = (TextView) findViewById(R.id.details_writer_name);
        writeImg = (ImageView) findViewById(R.id.writer_img);
        menudown = (ImageView) findViewById(R.id.writer_menu_img);
        //底部标题栏组件
        heart = (ImageView) findViewById(R.id.bt_heart);
        heart.setOnClickListener(this);
        findViewById(R.id.bt_back).setOnClickListener(this);
        findViewById(R.id.bt_message).setOnClickListener(this);
        findViewById(R.id.bt_share).setOnClickListener(this);
        //标题的作者图片点击事件,跳转作者的详细信息界面.并发送一个作者id


        //接收上一个界面传来的id ,拼接网址
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        imgurl = intent.getStringExtra("imageurl");
        favourite = intent.getBooleanExtra("favourite", false);
        if (favourite == true) {
            heart.setImageResource(R.mipmap.heart_bt_true);
            heartBoolean = false;
        }

        startVolleyDetails(url);//解析详情的方法
        struct();//加载html的方法
        startVolleyWriter();//解析作者的方法
        showPop();//显示popupWindow
        //标题的点击事件,弹出一个popupwindow
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop.isShowing()) {
                    pop.dismiss();
                    menudown.setImageResource(R.mipmap.menudown);
                } else {
                    pop.showAsDropDown(writeline);
                    menudown.setImageResource(R.mipmap.menuup);
                }
            }
        });
    }

    //解析作者===解析的是popupwindow的内容,下面解析的是界面的
    private void startVolleyWriter() {
        VolleySingle.addRequest(START_URL + url + END_URL, WriterBean.class, new Response.Listener<WriterBean>() {
            @Override
            public void onResponse(WriterBean response) {
                Log.d("WriterActivity", "作者信息解析完毕" + url);
                writerBean = response;
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

    //popupwindow具体的设置
    private void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_writer, null);
        pop = new PopupWindow(view,ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //绑定布局
        pop.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(0x70000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        pop.setBackgroundDrawable(dw);
        pop.setAnimationStyle(R.style.pop_anim);
        num = (TextView) view.findViewById(R.id.pop_writer_num);
        read = (TextView) view.findViewById(R.id.pop_writer_readnum);
        listView = (ListView) view.findViewById(R.id.pop_wirter_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String writerTitle = writerBean.getData().getLatestArticle().get(position).getTitle();
                String detailsTitle = detailsBean.getData().getTitle();
                if (writerTitle.equals(detailsTitle)) {
                    Toast.makeText(DetailsActivity.this, "您正在阅读该文章", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                } else {
                    startVolleyDetails(String.valueOf(writerBean.getData().getLatestArticle().get(position).getPostId()));
                    pop.dismiss();


                }

            }
        });


    }

    //解析详情数据====界面的内容,上面解析的是popupwindow的内容
    public void startVolleyDetails(String myUrl) {
        VolleySingle.addRequest("https://rong.36kr.com/api/mobi/news/" + myUrl, DetailsBean.class, new Response.Listener<DetailsBean>() {
            @Override
            public void onResponse(DetailsBean response) {
                detailsBean = response;
                String url = detailsBean.getData().getUser().getAvatar();
                //标题栏的数据设置
                writer.setText(detailsBean.getData().getUser().getName());
                Picasso.with(getApplication()).load(url).transform(new PicassoCirclTransform()).resize(100, 100).centerCrop().error(R.mipmap.ic_launcher).into(writeImg);
                //大标题小标题
                title.setText(detailsBean.getData().getTitle());
                secondTitle.setText("[" + detailsBean.getData().getSummary() + "]");
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
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_heart:
                if (heartBoolean == true) {
                    heart.setImageResource(R.mipmap.heart_bt_true);
                    String writer = detailsBean.getData().getUser().getName();
                    String title = detailsBean.getData().getTitle();
                    id = detailsBean.getData().getPostId();
                    FavouriteText a = new FavouriteText((long) id, title, writer, url, imgurl);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                    favouriteTextDao.insert(a);
                    heartBoolean = false;
                } else {
                    heart.setImageResource(R.mipmap.heart_bt);
                    id = detailsBean.getData().getPostId();
                    favouriteTextDao.deleteByKey((long) id);
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    heartBoolean = true;
                }
                break;
            case R.id.bt_back:
                finish();

                break;
            case R.id.bt_message:
                new ShareAction(this).setDisplayList().open();
                break;
            case R.id.bt_share:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE)
                        .setCallback(umShareListener)
                        .open();
                //实例化SelectPicPopupWindow
               /* sharepop = new MySharePopWindow(this, itemsOnClick);
                //显示窗口
                sharepop.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                linearLayout.setVisibility(View.INVISIBLE);
                linearLayout.startAnimation(animation_out);
*/break;

        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            sharepop.dismiss();
            switch (v.getId()) {
                //微信
                case R.id.share_wechat:
                    Toast.makeText(DetailsActivity.this, "微信", Toast.LENGTH_SHORT).show();
                    break;
                //qq
                case R.id.share_qq:
                    Toast.makeText(DetailsActivity.this, "QQ", Toast.LENGTH_SHORT).show();
                    break;
                //复制网址
                case R.id.share_link:
                    Toast.makeText(DetailsActivity.this, "复制网址", Toast.LENGTH_SHORT).show();
                    break;
                //生活圈
                case R.id.share_living:
                    Toast.makeText(DetailsActivity.this, "生活圈", Toast.LENGTH_SHORT).show();
                    break;
                //朋友圈
                case R.id.share_moment:
                    Toast.makeText(DetailsActivity.this, "朋友圈", Toast.LENGTH_SHORT).show();
                    break;
                //微博
                case R.id.share_weibo:
                    Toast.makeText(DetailsActivity.this, "微博", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);



    }



    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(DetailsActivity.this,platform + " 收藏成功啦",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(DetailsActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailsActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailsActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result","onActivityResult");
    }

}
