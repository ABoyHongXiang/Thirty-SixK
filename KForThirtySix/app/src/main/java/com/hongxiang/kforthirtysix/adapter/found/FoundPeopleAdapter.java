package com.hongxiang.kforthirtysix.adapter.found;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.util.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.FoundPeopleBean;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/16.
 */
public class FoundPeopleAdapter extends BaseAdapter {
    private FoundPeopleBean foundPeopleBean;
    private Context context;
    private Animation left_in,right_in;
    public FoundPeopleAdapter(Context context) {
        this.context = context;
    }

    public void setFoundPeopleBean(FoundPeopleBean foundPeopleBean) {
        this.foundPeopleBean = foundPeopleBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foundPeopleBean == null ? 0 : foundPeopleBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return foundPeopleBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_foundpeople, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = foundPeopleBean.getData().getData().get(position).getUser().getName();
        List<String> context_one = foundPeopleBean.getData().getData().get(position).getFocusIndustry();
        List<String> context_two = foundPeopleBean.getData().getData().get(position).getInvestPhases();
        String imageUrl = foundPeopleBean.getData().getData().get(position).getUser().getAvatar();
        String one = "";
        String two = "";
        //遍历集合后设置数据 例: 解析的数据是{A,B,C},需要在界面显示的是 A B C;
        //如果集合没有数据显示未披露
        if (context_one.size() == 0) {
            holder.context_one.setText("未披露");

        } else {
            for (int i = 0; i < context_one.size(); i++) {
                String a = context_one.get(i);
                one = one + " " + a;
            }
            holder.context_one.setText(one);
        }

        if (context_two.size() == 0) {
            holder.context_two.setText("未披露");

        } else {
            for (int i = 0; i < context_two.size(); i++) {
                String a = context_two.get(i);
                two = two + " " + a;
            }
            holder.context_two.setText(two);
        }
     //判断图片网址是否为空,设置圆形图片和图片大小
        if (imageUrl != null) {
            Picasso.with(context).load(imageUrl).transform(new PicassoCirclTransform()).resize(200, 200).centerCrop().error(R.mipmap.ic_launcher).into(holder.imageView);
        }
        holder.name.setText(name);
        left_in= AnimationUtils.loadAnimation(context, R.anim.item_left);
        right_in=AnimationUtils.loadAnimation(context, R.anim.item_right);
        if (position % 2 == 0) {

            convertView.setAnimation(left_in);
        } else {

            convertView.setAnimation(right_in);
        }
        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView name, context_one, context_two;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.people_img);
            name = (TextView) itemView.findViewById(R.id.people_name);
            context_one = (TextView) itemView.findViewById(R.id.people_context_one);
            context_two = (TextView) itemView.findViewById(R.id.people_context_two);

        }
    }
}

