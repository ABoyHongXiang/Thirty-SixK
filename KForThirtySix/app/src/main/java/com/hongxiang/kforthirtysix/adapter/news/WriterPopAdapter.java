package com.hongxiang.kforthirtysix.adapter.news;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.WriterBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/21.
 */
public class WriterPopAdapter extends BaseAdapter {
    private WriterBean writerBean;
    private Context context;
    private Animation left_in,right_in;
    public WriterPopAdapter(Context context) {
        this.context = context;
    }

    public void setWriterBean(WriterBean writerBean) {
        this.writerBean = writerBean;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return writerBean == null ? 0 : writerBean.getData().getLatestArticle().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_writer_listview, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        String imgUrl = writerBean.getData().getLatestArticle().get(position).getFeatureImg();
        Picasso.with(context).load(imgUrl).resize(100, 100).into(holder.imageView);
        holder.textView_one.setText(writerBean.getData().getLatestArticle().get(position).getTitle());
        left_in= AnimationUtils.loadAnimation(context, R.anim.item_left);
        right_in=AnimationUtils.loadAnimation(context, R.anim.item_right);
        if (position % 2 == 0) {

            convertView.setAnimation(left_in);
        } else {

            convertView.setAnimation(right_in);
        }
        return convertView;
    }

    class MyViewHolder {
        private ImageView imageView;
        private TextView textView_one;

        public MyViewHolder(View itemView) {

            imageView = (ImageView) itemView.findViewById(R.id.writer_item_img);
            textView_one = (TextView) itemView.findViewById(R.id.writer_item_tv_one);
        }
    }
}
