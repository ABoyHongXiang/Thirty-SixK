package com.hongxiang.kforthirtysix.fragment.news;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.fragment.BaseFragment;
import com.hongxiang.kforthirtysix.util.MyMenuPopWindow;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.activity.SearchActivity;

/**
 * Created by dllo on 16/5/9.
 * 新闻主Fragment
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private ImageView search, menu;
    private TextView title;
    private MyMenuPopWindow popupWindow;

    @Override
    public void initView(View view) {
        search = (ImageView) view.findViewById(R.id.newsfragment_search);
        menu = (ImageView) view.findViewById(R.id.fragmentnews_menu);
        title = (TextView) view.findViewById(R.id.news_fragment_title);

    }


    @Override
    public void initData() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.news_fragment, new NewsAllFragment()).commit();
        search.setOnClickListener(this);
        menu.setOnClickListener(this);


    }


    @Override
    public int initLayout() {

        return R.layout.fragment_news;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newsfragment_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.fragmentnews_menu:
                //动画


                //实例化SelectPicPopupWindow
                popupWindow = new MyMenuPopWindow(getContext(), itemsOnClick);
                //显示窗口
                popupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
        }

    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            popupWindow.dismiss();
            switch (v.getId()) {
                case R.id.pop_all:
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.news_fragment, new NewsAllFragment()).commit();
                    title.setText("新闻");
                    break;

                case R.id.pop_recent:
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.news_fragment, new RecentFragment()).commit();
                    title.setText("早期项目");
                    break;
                case R.id.pop_tv:
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.news_fragment, new TvFragment()).commit();
                    title.setText("氪TV");
                    break;


            }


        }

    };


}

