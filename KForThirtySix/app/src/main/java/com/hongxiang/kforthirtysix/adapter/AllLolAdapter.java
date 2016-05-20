package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.LolAllBean;
import com.hongxiang.kforthirtysix.childfragment.AllFragment;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/20.
 */
public class AllLolAdapter extends BaseAdapter {
    private Context context;
    private LolAllBean lolAllBean;

    public AllLolAdapter(Context context) {
        this.context = context;
    }

    public void setLolAllBean(LolAllBean lolAllBean) {

        this.lolAllBean = lolAllBean;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return lolAllBean == null ? 0 : lolAllBean.getData().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lol_all, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        String imgurl = lolAllBean.getData().get(position).getPic_url();
        holder.smalltitle.setText(lolAllBean.getData().get(position).getDesc());
        holder.title.setText(lolAllBean.getData().get(position).getTitle());
//        if (imgurl != null) {
//            Picasso.with(context).load(imgurl).into(holder.imageView);
//        }
        return convertView;
    }

    class MyViewHolder {
        private TextView title, smalltitle;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.lol_all_title);
            smalltitle = (TextView) itemView.findViewById(R.id.lol_all_smalltitle);
            imageView = (ImageView) itemView.findViewById(R.id.lol_all_img);
        }
    }
}
