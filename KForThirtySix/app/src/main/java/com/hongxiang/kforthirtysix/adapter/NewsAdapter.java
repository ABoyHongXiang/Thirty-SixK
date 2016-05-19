package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.util.NewRvListener;
import com.hongxiang.kforthirtysix.bean.NewsBean;
import com.hongxiang.kforthirtysix.R;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/11.
 * 新闻Fragment RecyclerView的适配器
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private String title, writer, type, imageurl;
    private NewsBean newsBean;
    private NewRvListener newRvListener;

    public void setNewRvListener(NewRvListener newRvListener) {
        this.newRvListener = newRvListener;
    }

    //向Fragment索要解析好的数据类
    public void setNewsBean(NewsBean newsBean) {
        this.newsBean = newsBean;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_resyslerview, null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //获取想要的数据
        title = newsBean.getData().getData().get(position).getTitle();
        writer = newsBean.getData().getData().get(position).getUser().getName();
        type = newsBean.getData().getData().get(position).getColumnName();
        imageurl = newsBean.getData().getData().get(position).getFeatureImg();
        //改变type的字体颜色 不一样的type的字体是不一样的颜色
        if (type != null) {
            switch (type) {
                case "深度":
                    holder.type.setTextColor(0xffbb322d);
                    break;
                case "早期项目":
                    holder.type.setTextColor(0xff80be42);
                    break;
                case "大公司":
                    holder.type.setTextColor(0xff4397f6);
                    break;
                case "B轮后":
                    holder.type.setTextColor(0xFFa1ccf5);
                    break;
            }


        }
        //设置数据,把上面获取的数据设置在指定的位置上
        holder.title.setText(title);
        holder.writer.setText(writer);
        holder.type.setText(type);
        //通过毕加索 传入自己的图片组件,传入图片的网址 即可完成替换
        Picasso.with(context).load(imageurl).into(holder.imageView);
        if(newRvListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    newRvListener.Onclick(pos);
                }
            });

        }
    }



    @Override
    public int getItemCount() {
        return newsBean == null ? 0 : newsBean.getData().getData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, writer, type;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_title);
            writer = (TextView) itemView.findViewById(R.id.news_writer);
            type = (TextView) itemView.findViewById(R.id.news_type);
            imageView = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }


}



