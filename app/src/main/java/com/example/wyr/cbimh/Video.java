package com.example.wyr.cbimh;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.VideoView;

import org.opencv.android.OpenCVLoader;
import org.opencv.videoio.VideoCapture;

import java.util.HashMap;

/**
 * Created by wyr on 2018/3/27.
 */

public class Video extends AppCompatActivity implements Runnable{

    private Toolbar toolbar;

    private VideoView videoView;

    private Bitmap bitmap;
    private MediaMetadataRetriever media;
    private ImageView imageView;

    private static final String TAG = "Video";

    static {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG,"opencv not loaded");
        }else{
            Log.d(TAG,"Hello opencv4Android");
        }
    }



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

        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/f11.mp4";

        videoView = findViewById(R.id.videoView);

        //videoView.setVideoPath(path);

        videoView.setVideoURI(Uri.parse("http://218.197.116.221/120.79.73.18/video/b.mp4"));

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);

        videoView.start();

        media = new MediaMetadataRetriever();
        media.setDataSource("http://218.197.116.221/120.79.73.18/video/b.mp4",new HashMap());

        imageView = findViewById(R.id.shot);


//        Bitmap bitmap = media.getFrameAtTime();
//
//        image.setImageBitmap(bitmap);


        Thread screenShootThread = new Thread(this);
        screenShootThread.start();
//        imageView.setImageBitmap(bitmap);

    }

    @Override
    public void run(){
        for (int i = 0; i < 50 * 1000; i += 1 * 1000) {

            bitmap = media.getFrameAtTime(videoView.getCurrentPosition()*1000, MediaMetadataRetriever.OPTION_CLOSEST);
            Log.d(TAG,"run");

        }

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