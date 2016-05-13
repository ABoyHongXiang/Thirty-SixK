package com.hongxiang.kforthirtysix;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by dllo on 16/5/12.
 */
public class NewsViewPagerAdapter extends BaseAdapter {
    List<ImageView> imageViewList;

    public void setImageViewList(List<ImageView> imageViewList) {
        this.imageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return imageViewList == null ? 0 : imageViewList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
