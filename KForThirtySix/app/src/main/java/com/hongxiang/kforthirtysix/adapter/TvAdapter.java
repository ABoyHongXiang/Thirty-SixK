package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.TvBean;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/13.
 * 氪TV
 */
public class TvAdapter extends BaseAdapter {
    private TvBean tvBean;
    private Context context;
    private List<Boolean> flag;

    public TvAdapter(Context context) {
        this.context = context;
    }

    public void setTvBean(TvBean tvBean) {
        this.tvBean = tvBean;
        notifyDataSetChanged();
        flag = new ArrayList<>();
        for (int i  = 0 ;i<=tvBean.getData().getData().size();i++){
            flag.add(false);
        }
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
        String imageurl = tvBean.getData().getData().get(position).getTv().getFeatureImg();
        String videoUri = tvBean.getData().getData().get(position).getTv().getVideoSource();
        String title = tvBean.getData().getData().get(position).getTv().getTitle();
        Picasso.with(context).load(imageurl).into(holder.imageView);
        holder.title.setText(title);
        holder.videoView.setVideoURI(Uri.parse(videoUri));
        holder.videoView.setMediaController(new android.widget.MediaController(context));
        final ViewHolder finalHolder = holder;
        //图片的点击事件
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //如果点击就让图片文字消失,播放视频
                finalHolder.title.setVisibility(v.INVISIBLE);
                finalHolder.imageView.setVisibility(v.INVISIBLE);
                finalHolder.videoView.setVisibility(v.VISIBLE);
                finalHolder.videoView.start();
            }
        });


        return convertView;
    }


    class ViewHolder {
        private VideoView videoView;
        private ImageView imageView;
        private TextView title;


        public ViewHolder(View itemView) {
            videoView = (VideoView) itemView.findViewById(R.id.videoview);
            imageView = (ImageView) itemView.findViewById(R.id.tv_imageview);
            title = (TextView) itemView.findViewById(R.id.tv_title);


        }
    }

}