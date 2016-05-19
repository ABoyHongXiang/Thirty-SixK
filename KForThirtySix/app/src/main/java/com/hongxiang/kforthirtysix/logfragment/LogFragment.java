package com.hongxiang.kforthirtysix.logfragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;

/**
 * Created by dllo on 16/5/16.
 */
public class LogFragment extends BaseFragment {
    private Button log;


    @Override
    public void initView(View view) {
        log = (Button) view.findViewById(R.id.log_btn);



    }

    @Override
    public void initData() {
       log.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getContext(), "敬请期待服务开通", Toast.LENGTH_SHORT).show();
           }
       });

    }

    @Override
    public int initLayout() {
        return R.layout.fragment_log;
    }
}
