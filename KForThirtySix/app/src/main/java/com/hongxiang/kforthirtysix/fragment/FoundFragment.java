package com.hongxiang.kforthirtysix.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.FoundPeopleActivity;
import com.hongxiang.kforthirtysix.activity.LogActivity;
import com.hongxiang.kforthirtysix.activity.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 * //发现界面的Fragment
 */
public class FoundFragment extends BaseFragment implements View.OnClickListener {
    private TextView searchTv;
    private View good, leaf, title_one, title_two, title_three, title_four, sv_one, sv_two, sv_three, sv_four, sv_five, sv_six;

    @Override
    public void initView(View view) {
        searchTv = (TextView) view.findViewById(R.id.foundfragment_search);
        good = view.findViewById(R.id.found_good);
        leaf = view.findViewById(R.id.found_leaf);
        title_one = view.findViewById(R.id.found_title_one);
        title_two = view.findViewById(R.id.found_title_two);
        title_three = view.findViewById(R.id.found_title_three);
        title_four = view.findViewById(R.id.found_title_four);
        sv_one = view.findViewById(R.id.found_sv_one);
        sv_two = view.findViewById(R.id.found_sv_two);
        sv_three = view.findViewById(R.id.found_sv_three);
        sv_four = view.findViewById(R.id.found_sv_four);
        sv_five = view.findViewById(R.id.found_sv_five);
        sv_six = view.findViewById(R.id.found_sv_six);
    }

    @Override
    public void initData() {
        searchTv.setOnClickListener(this);
        good.setOnClickListener(this);
        leaf.setOnClickListener(this);
        title_one.setOnClickListener(this);
        title_two.setOnClickListener(this);
        title_three.setOnClickListener(this);
        title_four.setOnClickListener(this);
        sv_one.setOnClickListener(this);
        sv_two.setOnClickListener(this);
        sv_three.setOnClickListener(this);
        sv_four.setOnClickListener(this);
        sv_five.setOnClickListener(this);
        sv_six.setOnClickListener(this);
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
            //深度创业服务
            case R.id.found_good:
                intent();
                break;
            //创业公司
            case R.id.found_leaf:
                intent();
                break;
            //最具爆发力
            case R.id.found_title_one:
                intent();
                break;
            //最新涌现
            case R.id.found_title_two:
                intent();
                break;
            //项目动态
            case R.id.found_title_three:
                intent();
                break;
            //投资人再看
            case R.id.found_title_four:
                intent();
                break;
            //36氪指数
            case R.id.found_sv_one:
                intent();
                break;
            //近期活动
            case R.id.found_sv_two:
                Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            //36氪研究所
            case R.id.found_sv_three:
                intent();
                break;
            //寻找投资人
            case R.id.found_sv_four:
                Intent intent1 = new Intent(getActivity(), FoundPeopleActivity.class);
                startActivity(intent1);
                break;
            //申请融资
            case R.id.found_sv_five:
                intent();
                break;
            //氪空气报名
            case R.id.found_sv_six:
                intent();
                break;

        }

    }

    public void intent() {
        Intent intent = new Intent(getActivity(), LogActivity.class);
        startActivity(intent);

    }
}
