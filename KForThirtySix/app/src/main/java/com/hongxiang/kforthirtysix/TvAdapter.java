package com.hongxiang.kforthirtysix;

import android.content.Context;
import android.media.session.MediaController;
import android.net.Uri;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.VideoView;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 */
public class TvAdapter extends BaseAdapter {
    private TvBean tvBean;
    private Context context;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public void setTvBean(TvBean tvBean) {
        this.tvBean = tvBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tvBean == null ? 0 : tvBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return tvBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tv, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//      String imageurl = tvBean.getData().getData().get(position).getTv().getFeatureImg();
//      Picasso.with(context).load(imageurl).into(holder.imageView);
        String videoUri = tvBean.getData().getData().get(position).getTv().getVideoSource();
        holder.videoView.setVideoURI(Uri.parse(videoUri));
        holder.videoView.setMediaController(new android.widget.MediaController(context));
        holder.videoView.start();
        return convertView;
    }


    class ViewHolder {
        private VideoView videoView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            videoView = (VideoView) itemView.findViewById(R.id.videoview);
            imageView = (ImageView) itemView.findViewById(R.id.tv_imageview);

        }
    }

}
