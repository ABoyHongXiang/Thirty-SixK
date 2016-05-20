package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/19.
 */
public class NearPlayAdapter extends BaseAdapter {
    private NearPlayBean nearPlayBean;
    private Context context;

    public NearPlayAdapter(Context context) {
        this.context = context;
    }

    public void setNearPlayBean(NearPlayBean nearPlayBean) {
        this.nearPlayBean = nearPlayBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.d("NearPlayAdapter", "nearPlayBean.getData().getData().size():" + nearPlayBean.getData().getData().size());
        return nearPlayBean == null ? 0 : nearPlayBean.getData().getData().size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_nearplay, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String title =nearPlayBean.getData().getData().get(position).getActivityName();
        String smalltitle = nearPlayBean.getData().getData().get(position).getActivityDesc();
        String type = nearPlayBean.getData().getData().get(position).getActivityStatus();
        String  date = nearPlayBean.getData().getData().get(position).getActivityTime();
        String city =nearPlayBean.getData().getData().get(position).getActivityCity();
        String url = nearPlayBean.getData().getData().get(position).getActivityImg();
        holder.city.setText(city);
        holder.date.setText(date);
        holder.title.setText(title);
        holder.smalltitle.setText(smalltitle);
        holder.type.setText(type);
        Picasso.with(context).load(url).into(holder.imageView);
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
          private TextView title,smalltitle,type,city,date;
        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.nearplay_img);
            title  = (TextView) itemView.findViewById(R.id.nearplay_title);
            smalltitle = (TextView) itemView.findViewById(R.id.nearplay_smalltitle);
            type = (TextView) itemView.findViewById(R.id.nearplay_type);
            city = (TextView) itemView.findViewById(R.id.nearplay_city);
            date= (TextView) itemView.findViewById(R.id.nearplay_date);

        }
    }
}
