package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.SearchBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/27.
 */
public class SearchAdapter extends BaseAdapter {
    private Context context;
    private SearchBean searchBean;

    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchBean(SearchBean searchBean) {
        this.searchBean = searchBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return searchBean == null ? 0 : searchBean.getData().getData().size();
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String imageUrl=searchBean.getData().getData().get(position).getFeatureImg();
        Picasso.with(context).load(imageUrl).resize(200,200).into(holder.img);
        holder.title.setText(searchBean.getData().getData().get(position).getTitle());
        holder.writer.setText(searchBean.getData().getData().get(position).getUser().getName());
        holder.type.setText(searchBean.getData().getData().get(position).getColumnName());
        return convertView;
    }

    class ViewHolder {
        private ImageView img;
        private TextView writer, title, type;

        public ViewHolder(View itemView) {
            img = (ImageView) itemView.findViewById(R.id.search_image);
            title = (TextView) itemView.findViewById(R.id.search_title);
            type = (TextView) itemView.findViewById(R.id.search_type);
            writer = (TextView) itemView.findViewById(R.id.search_writer);
        }
    }
}
