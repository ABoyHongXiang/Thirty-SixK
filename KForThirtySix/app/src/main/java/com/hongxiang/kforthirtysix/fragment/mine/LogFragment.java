package com.hongxiang.kforthirtysix.fragment.mine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteText;
import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.logsql.Log;
import com.hongxiang.kforthirtysix.logsql.LogDao;
import com.hongxiang.kforthirtysix.util.FavouritedaoSingle;
import com.hongxiang.kforthirtysix.util.LogDaoSingle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/16.
 */
public class LogFragment extends BaseFragment {
    private Button log;
    private EditText phoneNum, diglogUser, diglogKey;
    private LogDao logdao;
    private String user, key,userNum;
    private List<Log> logList;


    @Override
    public void initView(View view) {
        log = (Button) view.findViewById(R.id.log_btn);
        phoneNum = (EditText) view.findViewById(R.id.log_phonenumber);


    }

    @Override
    public void initData() {
        logdao = LogDaoSingle.getInstance().getLogDao();
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNum.getText().length() == 11) {
                    userNum= String.valueOf(phoneNum.getText());
                    showDialog();
                } else {
                    Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void showDialog() {
        AlertDialog.Builder bulider = new AlertDialog.Builder(getActivity());
        bulider.setTitle("注册");
        bulider.setMessage("请填写您的注册信息");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.logdialog, null);
        diglogUser = (EditText) view.findViewById(R.id.log_dialog_user);
        diglogKey = (EditText) view.findViewById(R.id.log_dialog_keys);
        android.util.Log.d("LogFragment", "phoneNum.getText():" + phoneNum.getText());
        diglogUser.setText(userNum);
        bulider.setNegativeButton("取消", null);
        bulider.setPositiveButton("注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user = String.valueOf(diglogUser.getText());
                key = String.valueOf(diglogKey.getText());
                if (diglogUser.length() == 11 && diglogKey.length() >= 6 && diglogKey.length() < 15) {
                    logList = new ArrayList<>();
                    logList = logdao.queryBuilder().list();
                    if (logList.size() < 1) {
                        Log a = new Log(user, key);
                        logdao.insert(a);
                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();

                    } else {
                        for (Log log1 : logList) {
                            if (log1.getUser().equals(user)) {
                                Toast.makeText(getActivity(), "该用户已经注册", Toast.LENGTH_SHORT).show();
                            } else {
                                Log a = new Log(user, key);
                                logdao.insert(a);
                                Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }


                } else {
                    Toast.makeText(getActivity(), "请输入正确的手机号和密码", Toast.LENGTH_SHORT).show();
                }


            }
        });
        bulider.setView(view);
        bulider.show();
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_log;
    }
}
