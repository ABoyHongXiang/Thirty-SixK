package com.hongxiang.kforthirtysix.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;

import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/12.
 */
public class MyMenuPopWindow extends PopupWindow {

    private View mMenuView;
    private Button recent, tv, exit, all;

    public MyMenuPopWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.menu_popwindow, null);
        tv = (Button) mMenuView.findViewById(R.id.pop_tv);
        tv.setOnClickListener(itemsOnClick);
        all = (Button) mMenuView.findViewById(R.id.pop_all);
        all.setOnClickListener(itemsOnClick);
        recent = (Button) mMenuView.findViewById(R.id.pop_recent);
        recent.setOnClickListener(itemsOnClick);
        exit = (Button) mMenuView.findViewById(R.id.pop_exit);
        //取消按钮
        exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        AlphaAnimation alphaAnimation
                //从多少透明度 到多少透明度
                = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
        alphaAnimation.setDuration(1000);
        mMenuView.setAnimation(alphaAnimation);
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
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