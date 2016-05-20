package com.hongxiang.kforthirtysix.childfragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.VolleySingle;
import com.hongxiang.kforthirtysix.adapter.FindanceAdapter;
import com.hongxiang.kforthirtysix.bean.LolBean;
import com.hongxiang.kforthirtysix.bean.NearPlayBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.GsonRequest;

/**
 * Created by dllo on 16/5/9.
 */
public class FinanceFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private LolBean lolBean;
    private FindanceAdapter findanceAdapter;

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.finance_recyclerview);
    }

    @Override
    public void initData() {
        findanceAdapter = new FindanceAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<LolBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "http://lol.zhangyoubao.com/apis/rest/CatalogsService/all?cattype=video&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438755336&p_=18353&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol"
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<LolBean>() {
            @Override
            public void onResponse(LolBean response) {
                lolBean = response;
                findanceAdapter.setLolBean(lolBean);
                recyclerView.setAdapter(findanceAdapter);

            }
        }, LolBean.class);

        requestQueue.add(gsonRequest);


    }

    @Override
    public int initLayout() {
        return R.layout.fragment_finance;
    }
}
