package com.hongxiang.kforthirtysix.fragment.mine;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;

/**
 * Created by dllo on 16/5/16.
 */
public class LogInFragment extends BaseFragment implements View.OnClickListener {
    private TextView withoutKey;
    private Button logIn;
    public EditText userNum, key;

    @Override

    public void initView(View view) {
        userNum = (EditText) view.findViewById(R.id.login_phonenumber_et);
        key = (EditText) view.findViewById(R.id.login_key_et);
        withoutKey = (TextView) view.findViewById(R.id.login_withoutkey_tv);
        logIn = (Button) view.findViewById(R.id.login_btn);

    }

    @Override
    public void initData() {
        logIn.setOnClickListener(this);
        withoutKey.setOnClickListener(this);
          if (userNum.getText().length() > 0 && key.getText().length() > 0) {
                logIn.setTextColor(Color.BLUE);
            } else {
                logIn.setTextColor(Color.BLACK);
            }




    }

    @Override
    public int initLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登录按钮
            case R.id.login_btn:
                Toast.makeText(getActivity(), "敬请期待服务开通", Toast.LENGTH_SHORT).show();
                break;
            //忘记密码
            case R.id.login_withoutkey_tv:
                Toast.makeText(getActivity(), "您的申请已提交,请等待服务开通", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
