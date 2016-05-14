package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 */
public class FoundFragment extends BaseFragment implements View.OnClickListener {
    private TextView searchTv;

    @Override
    public void initView(View view) {
        searchTv = (TextView) view.findViewById(R.id.foundfragment_search);
    }

    @Override
    public void initData() {
        searchTv.setOnClickListener(this);
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_found;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.foundfragment_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;

        }
    }
}
