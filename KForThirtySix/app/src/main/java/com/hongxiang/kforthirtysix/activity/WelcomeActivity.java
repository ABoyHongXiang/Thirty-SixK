package com.hongxiang.kforthirtysix.activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.hongxiang.kforthirtysix.R;
import cn.jpush.android.api.JPushInterface;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/17.
 */
public class WelcomeActivity extends BaseActivity {
    private TextView timeTv;
    private CountDownTimer timer;
    private static final String URL = "http://img4.duitang.com/uploads/item/201407/06/20140706134951_tc2US.thumb.700_0.jpeg";
    private ImageView imageView;


    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        imageView = (ImageView) findViewById(R.id.welcome_img);
        timeTv = (TextView) findViewById(R.id.wel_time);
        imageView = (ImageView) findViewById(R.id.welcome_img);
        timeTv = (TextView) findViewById(R.id.wel_time);
        Picasso.with(this).load(URL).into(imageView);
    }

    @Override
    protected void initData() {
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();//在跳转的时候 取消timer
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                finish();
            }
        });

        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTv.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timeTv.setText("跳转");
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
