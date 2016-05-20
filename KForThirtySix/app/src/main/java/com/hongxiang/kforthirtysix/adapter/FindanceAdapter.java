package com.hongxiang.kforthirtysix.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.PicassoCirclTransform;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.LolBean;
import com.hongxiang.kforthirtysix.util.LolRvLinstener;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/20.
 */
public class FindanceAdapter extends RecyclerView.Adapter<FindanceAdapter.MyViewHolder> {
    private LolBean lolBean;
    private Context context;
    private int pos;
    private LolRvLinstener lolRvLinstener;

    public void setLolRvLinstener(LolRvLinstener lolRvLinstener) {
        this.lolRvLinstener = lolRvLinstener;

    }

    public void setPos(int pos) {
        this.pos = pos;
        Log.d("FindanceAdapter", "pos:" + pos);
        notifyDataSetChanged();
    }

    public FindanceAdapter(Context context) {
        this.context = context;
    }

    public void setLolBean(LolBean lolBean) {
        this.lolBean = lolBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lol, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.textView.setText(lolBean.getData().get(pos).getCatword_id().get(position).getName());
        String url = lolBean.getData().get(pos).getCatword_id().get(position).getPic_url();
        Picasso.with(context).load(url).transform(new PicassoCirclTransform()).resize(200, 200).centerCrop().error(R.mipmap.ic_launcher).into(holder.imageView);
   holder.itemView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           int itemPos = holder.getLayoutPosition();
           lolRvLinstener.Onclick(itemPos);
       }
   });

    }

    @Override
    public int getItemCount() {
        return lolBean.getData().get(pos).getCatword_id().size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.lol_img);
            textView = (TextView) itemView.findViewById(R.id.lol_text);
        }
    }
}
