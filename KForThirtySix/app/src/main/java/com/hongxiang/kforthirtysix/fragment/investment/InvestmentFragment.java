package com.hongxiang.kforthirtysix.fragment.investment;

import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.hongxiang.kforthirtysix.adapter.investment.InvestmentAdapter;
import com.hongxiang.kforthirtysix.bean.InvestmentBean;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.VolleySingle;

/**
 * Created by dllo on 16/5/9.
 */
public class InvestmentFragment extends BaseFragment {
    private ListView listView;
    private InvestmentAdapter investmentAdapter;
    private static final String START_URL = " https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=";
    private static final String END_URL = "&pageSize=";
    private InvestmentBean investmentBean;
    public String type;

    public InvestmentFragment(String type) {
        this.type = type;
    }

    public InvestmentFragment() {
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.investment_listview);
    }

    @Override
    public void initData() {
        investmentAdapter = new InvestmentAdapter(getContext());
        VolleySingle.addRequest(START_URL + type + END_URL + 20, InvestmentBean.class, new Response.Listener<InvestmentBean>() {
            @Override
            public void onResponse(InvestmentBean response) {
                investmentBean = response;
                investmentAdapter.setInvestmentBean(investmentBean);
                listView.setAdapter(investmentAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    @Override
    public int initLayout() {
        return R.layout.fragment_investment;
    }
}




