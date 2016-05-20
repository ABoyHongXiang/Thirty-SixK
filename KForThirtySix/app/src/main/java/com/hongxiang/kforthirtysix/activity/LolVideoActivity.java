package com.hongxiang.kforthirtysix.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.hongxiang.kforthirtysix.R;

/**
 * Created by dllo on 16/5/20.
 */
public class LolVideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lolvideo);
        videoView = (VideoView) findViewById(R.id.lol_videoview);
        Intent intent = getIntent();
       String videoUrl =  intent.getStringExtra("videourl");
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();

    }
}
