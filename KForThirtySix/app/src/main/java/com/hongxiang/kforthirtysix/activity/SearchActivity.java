package com.hongxiang.kforthirtysix.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.hongxiang.kforthirtysix.R;
import com.hongxiang.kforthirtysix.fragment.NewsFragment;

/**
 * Created by dllo on 16/5/10.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView exitTextview;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        exitTextview = (TextView) findViewById(R.id.search_exit);
        exitTextview.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_exit:

                finish();

                break;
        }
    }
}
