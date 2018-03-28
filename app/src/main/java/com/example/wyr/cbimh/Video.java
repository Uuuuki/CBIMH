package com.example.wyr.cbimh;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by wyr on 2018/3/27.
 */

public class Video extends AppCompatActivity {

    private Toolbar toolbar;

    private VideoView videoView;



    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

        toolbar = (Toolbar) findViewById(R.id.toolbar_video);


        toolbar.setTitle("宝贝视频");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "01.flv";

        videoView = findViewById(R.id.videoView);

        //videoView.setVideoPath(path);

        videoView.setVideoURI(Uri.parse("http://download.yxybb.com/bbvideo/web/d1/d13/d11/d1/f11-web.mp4"));

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}