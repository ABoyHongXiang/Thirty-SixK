package com.hongxiang.kforthirtysix.adapter.news;

import android.content.Context;

import android.support.v4.view.PagerAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hongxiang.kforthirtysix.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/12.
 */
public class NewsHeadAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private Context context;

    public NewsHeadAdapter(Context context) {
        this.context = context;
    }


    public void setImageViews(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {

        return imageViews == null ? 0 : imageViews.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageViews.get(position));
        return imageViews.get(position);
    }
}