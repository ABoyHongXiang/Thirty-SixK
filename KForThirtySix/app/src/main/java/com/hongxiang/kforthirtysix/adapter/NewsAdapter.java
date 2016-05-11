package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.hongxiang.kforthirtysix.NewsBean;
import com.hongxiang.kforthirtysix.R;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by dllo on 16/5/11.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context context;
    private String title, writer, type, imageurl;
    private NewsBean newsBean;

    public void setNewsBean(NewsBean newsBean) {
        this.newsBean = newsBean;
        notifyDataSetChanged();
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_newslistview, null);
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        title = newsBean.getData().getData().get(position).getTitle();
        writer = newsBean.getData().getData().get(position).getUser().getName();
        type = newsBean.getData().getData().get(position).getColumnName();
        imageurl = newsBean.getData().getData().get(position).getFeatureImg();
        holder.title.setText(title);
        holder.writer.setText(writer);
        holder.type.setText(type);
        setImage(holder.imageView);
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

    public void setImage(final ImageView imageView ) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(imageurl,
                new com.android.volley.Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageView.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.ALPHA_8, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }

        });

        requestQueue.add(imageRequest);
}}


