package com.hongxiang.kforthirtysix.adapter.investment;

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

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.bean.InvestmentBean;
import com.hongxiang.kforthirtysix.util.PicassoCirclTransform;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/5/20.
 */
public class InvestmentAdapter extends BaseAdapter {
    private Context context;
    private InvestmentBean investmentBean;
    private Animation left_in,right_in;
    public InvestmentAdapter(Context context) {
        this.context = context;
    }

    public void setInvestmentBean(InvestmentBean investmentBean) {
        this.investmentBean = investmentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return investmentBean == null ? 0 : investmentBean.getData().getData().size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_investment, parent, false);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        String company_logo_url = investmentBean.getData().getData().get(position).getCompany_logo();
        String file_list_img_url   = investmentBean.getData().getData().get(position).getFile_list_img();
        holder.company_name.setText(investmentBean.getData().getData().get(position).getCompany_name());
        holder.company_brief.setText(investmentBean.getData().getData().get(position).getCompany_brief());
        holder.investment_adcontent.setText(investmentBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        holder.investment_adcontent_two.setText(investmentBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        holder.investment_lead_name.setText(investmentBean.getData().getData().get(position).getLead_name());
        Picasso.with(context).load(company_logo_url).transform(new PicassoCirclTransform()).resize(100, 100).centerCrop().into(holder.company_logo);
        Picasso.with(context).load(file_list_img_url).into(holder.file_list_img);

        left_in= AnimationUtils.loadAnimation(context, R.anim.item_left);
        right_in=AnimationUtils.loadAnimation(context, R.anim.item_right);
        if (position % 2 == 0) {

            convertView.setAnimation(left_in);
        } else {

            convertView.setAnimation(right_in);
        }
        return convertView;
    }

    class MyViewHolder {
        private TextView company_name, company_brief,investment_adcontent,investment_adcontent_two,investment_lead_name;
        private ImageView company_logo,file_list_img;

        public MyViewHolder(View itemView) {
            company_name = (TextView) itemView.findViewById(R.id.investment_company_name);
            company_brief = (TextView) itemView.findViewById(R.id.investment_company_brief);
            investment_adcontent = (TextView) itemView.findViewById(R.id.investment_adcontent);
            investment_adcontent_two = (TextView) itemView.findViewById(R.id.investment_adcontent_two);
            investment_lead_name = (TextView) itemView.findViewById(R.id.investment_lead_name);
            company_logo = (ImageView) itemView.findViewById(R.id.investment_company_logo);
            file_list_img = (ImageView) itemView.findViewById(R.id.investment_file_list_img);
        }
    }
}
