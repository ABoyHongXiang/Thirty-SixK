package com.hongxiang.kforthirtysix.fragment.mine;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.MainActivity;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.logsql.Log;
import com.hongxiang.kforthirtysix.logsql.LogDao;
import com.hongxiang.kforthirtysix.util.LogDaoSingle;

import java.util.List;

/**
 * Created by dllo on 16/5/16.
 */
public class LogInFragment extends BaseFragment implements View.OnClickListener {
    private TextView withoutKey;
    private Button logIn;
    public EditText userNumEt, keyEt;
    private String user, key;
    private List<Log> logList;
    private LogDao logDao;
    private int i = 1;

    @Override

    public void initView(View view) {
        userNumEt = (EditText) view.findViewById(R.id.login_phonenumber_et);
        keyEt = (EditText) view.findViewById(R.id.login_key_et);
        withoutKey = (TextView) view.findViewById(R.id.login_withoutkey_tv);
        logIn = (Button) view.findViewById(R.id.login_btn);

    }

    @Override
    public void initData() {
        logIn.setOnClickListener(this);
        logDao = LogDaoSingle.getInstance().getLogDao();


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
                logList = logDao.queryBuilder().list();
                user = String.valueOf(userNumEt.getText());
                key = String.valueOf(keyEt.getText());
                if (logList.size() < 1) {
                    Toast.makeText(getContext(), "没注册", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.length() == 11 && key.length() > 6 && key.length() < 15) {
                        for (Log log : logList) {
                            if (log.getUser().equals(user)) {
                                i = 2;
                                if (log.getKey().equals(key)) {
                                    Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                                    Intent broadIntent = new Intent("LogBroad");
                                    broadIntent.putExtra("user",user);
                                    android.util.Log.d("LogInFragment", user);
                                    getContext().sendBroadcast(broadIntent);



                                } else {
                                    Toast.makeText(getContext(), "密码错误请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        if (i == 1) {
                            Toast.makeText(getContext(), "该用户没有注册", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "请输入正确的手机号和密码", Toast.LENGTH_SHORT).show();
                    }


                }

                break;
            //忘记密码
            case R.id.login_withoutkey_tv:
                Toast.makeText(getActivity(), "您的申请已提交,请等待服务开通", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
