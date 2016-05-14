package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.RecentBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/14.
 */
public class RecentAdapter extends BaseAdapter {
    private Context context;
    private RecentBean recentBean;

    public RecentAdapter(Context context) {
        this.context = context;
      }

    public void setRecentBean(RecentBean recentBean) {
        this.recentBean = recentBean;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return recentBean == null ? 0 : recentBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return recentBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recent_recyclerview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String title = recentBean.getData().getData().get(position).getTitle();
        String writer = recentBean.getData().getData().get(position).getUser().getName();
        String imgurl = recentBean.getData().getData().get(position).getFeatureImg();
        Log.d("RecentAdapter", title);
        Picasso.with(context).load(imgurl).into(holder.imageView);
        holder.title.setText(title);
        holder.writer.setText(writer);


        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView writer, title;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.recent_img);
            writer = (TextView) itemView.findViewById(R.id.recent_writer);
            title = (TextView) itemView.findViewById(R.id.recent_title);
        }
    }
}
