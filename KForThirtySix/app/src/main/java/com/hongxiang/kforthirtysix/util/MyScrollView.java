package com.hongxiang.kforthirtysix.util;

/**
 * Created by dllo on 16/5/24.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 可以监听ScrollView的上下滑动 ，实现ScrollListener接口，调用setScrollListener(ScrollListener l)方法。
 * SCROLL_UP :ScrollView正在向上滑动
 * SCROLL_DOWN :ScrollView正在向下滑动
 * @author xm
 */
public class MyScrollView extends ScrollView {

    private ScrollListener mListener;

    public static interface ScrollListener {
        public void scrollDownOritention(int oritention);
        public void scrollUpOritention(int oritention);
    }


    /**
     * ScrollView正在向上滑动
     */
    public static final int SCROLL_UP = 0x01;

    /**
     * ScrollView正在向下滑动
     */
    public static final int SCROLL_DOWN = 0x10;

    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 10;

    public MyScrollView(Context context) {
        super(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (oldt > t && oldt - t > SCROLLLIMIT) {// 向下
            if (mListener != null)
                mListener.scrollDownOritention(SCROLL_DOWN);
        } else if (oldt < t && t - oldt > SCROLLLIMIT) {// 向上
            if (mListener != null)
                mListener.scrollUpOritention(SCROLL_UP);
        }
    }

    public void setScrollListener(ScrollListener l) {
        this.mListener = l;
    }
}
