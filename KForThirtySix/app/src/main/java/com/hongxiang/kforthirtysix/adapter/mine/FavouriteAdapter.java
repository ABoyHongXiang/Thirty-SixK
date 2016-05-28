package com.hongxiang.kforthirtysix.adapter.mine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteText;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/25.
 */
public class FavouriteAdapter extends BaseAdapter {
    private Context context;
    private List<FavouriteText> favouriteTexts;

    public FavouriteAdapter(Context context) {
        this.context = context;
    }

    public void setFavouriteTexts(List<FavouriteText> favouriteTexts) {
        this.favouriteTexts = favouriteTexts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return favouriteTexts == null ? 0 : favouriteTexts.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_favourite, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        String imgurl = favouriteTexts.get(position).getImgurl();

        holder.title.setText(favouriteTexts.get(position).getTitle());
        holder.writer.setText(favouriteTexts.get(position).getWriter());
        Picasso.with(context).load(imgurl).resize(150,150).into(holder.imageView);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_PARENT, 0f,
                Animation.RELATIVE_TO_SELF, -1,
                Animation.RELATIVE_TO_PARENT, 0f);
        translateAnimation.setDuration(1000);
        convertView.setAnimation(translateAnimation);
        return convertView;
    }

    class MyViewHolder {
        private TextView title, writer;

        private ImageView imageView;
        public MyViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.item_favourite_title);
            writer = (TextView) itemView.findViewById(R.id.item_favourite_writer);
            imageView = (ImageView) itemView.findViewById(R.id.item_facourite_img);

        }
    }
}
