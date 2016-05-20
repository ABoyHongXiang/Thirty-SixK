package com.hongxiang.kforthirtysix.childfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.hongxiang.kforthirtysix.activity.AllLolDetails;
import com.hongxiang.kforthirtysix.adapter.AllLolAdapter;
import com.hongxiang.kforthirtysix.bean.LolAllBean;
import com.hongxiang.kforthirtysix.bean.TvBean;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.util.GsonRequest;

/**
 * Created by dllo on 16/5/9.
 */
public class AllFragment extends BaseFragment {
    private ListView listView;
    private AllLolAdapter allLolAdapter;
    private LolAllBean lolAllBean;

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.lol_all_listview);
    }

    @Override
    public void initData() {
        allLolAdapter = new AllLolAdapter(getContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        GsonRequest<LolAllBean> gsonRequest = new GsonRequest<>(Request.Method.GET, "http://lol.zhangyoubao.com/apis/rest/ItemsService/lists?cattype=news&catid=10000&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438746202&p_=20951&v_=40050303&d_=ios&osv_=8.3&version=0&a_=lol", new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, new Response.Listener<LolAllBean>() {
            @Override
            public void onResponse(LolAllBean response) {
                lolAllBean = response;
                allLolAdapter.setLolAllBean(lolAllBean);

            }
        }, LolAllBean.class);
        requestQueue.add(gsonRequest);
        listView.setAdapter(allLolAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), AllLolDetails.class);
                intent.putExtra("id",lolAllBean.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_all;
    }

}
