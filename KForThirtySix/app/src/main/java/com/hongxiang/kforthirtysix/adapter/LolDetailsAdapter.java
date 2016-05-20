package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.LolDetailsBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/20.
 */
public class LolDetailsAdapter extends BaseAdapter {
    private Context context;
    private LolDetailsBean lolDetailsBean;

    public LolDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setLolDetailsBean(LolDetailsBean lolDetailsBean) {

        this.lolDetailsBean = lolDetailsBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return lolDetailsBean==null?0:lolDetailsBean.getData().size();
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
        MyViewHolder holder ;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_loldetails, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        String imgurl = lolDetailsBean.getData().get(position).getPic_url();
        holder.title.setText(lolDetailsBean.getData().get(position).getTitle());
        holder.smalltitle.setText(lolDetailsBean.getData().get(position).getDesc());
        Picasso.with(context).load(imgurl).resize(100,100).into(holder.imageView);
        return convertView;

    }
    class MyViewHolder{
        private TextView title,smalltitle;
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            title= (TextView) itemView.findViewById(R.id.lol_details_title);
            smalltitle = (TextView) itemView.findViewById(R.id.lol_details_smalltitle);
            imageView = (ImageView) itemView.findViewById(R.id.lol_details_img);
        }
    }
}
