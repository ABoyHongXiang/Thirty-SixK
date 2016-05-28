package com.hongxiang.kforthirtysix.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.PopupWindow;

import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/24.
 */
public class MySharePopWindow extends PopupWindow {

    private View mShareView;
    private View wechat;//微信
    private View qq;//qq
    private View alipay;//支付宝
    private View link;//复制链接
    private View living;//生活圈
    private View weibo;//微博
    private View moment;//朋友圈
    private Button exit;

    public MySharePopWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mShareView = inflater.inflate(R.layout.popupwindow_bottom, null);
        wechat = mShareView.findViewById(R.id.share_wechat);
        wechat.setOnClickListener(itemsOnClick);
        qq = mShareView.findViewById(R.id.share_qq);
        qq.setOnClickListener(itemsOnClick);
        alipay = mShareView.findViewById(R.id.share_alipay);
        alipay.setOnClickListener(itemsOnClick);
        link = mShareView.findViewById(R.id.share_link);
        link.setOnClickListener(itemsOnClick);
        living = mShareView.findViewById(R.id.share_living);
        living.setOnClickListener(itemsOnClick);
        weibo = mShareView.findViewById(R.id.share_weibo);
        weibo.setOnClickListener(itemsOnClick);
        moment = mShareView.findViewById(R.id.share_moment);
        moment.setOnClickListener(itemsOnClick);
        exit = (Button) mShareView.findViewById(R.id.share_exit);
        //取消按钮
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置SelectPicPopupWindow的View
        this.setContentView(mShareView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x90d2d2d0);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        AlphaAnimation alphaAnimation
                //从多少透明度 到多少透明度
                = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setDuration(1000);
        mShareView.setAnimation(alphaAnimation);
        mShareView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = mShareView.findViewById(R.id.share_pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }
}

