package com.example.wyr.cbimh;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.VideoView;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by wyr on 2018/3/27.
 */

public class Video extends AppCompatActivity{

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
    }

    public static native int[] faceDetect(int[] pixels, int w, int h);


    private Toolbar toolbar;

    private VideoView videoView;

    private Bitmap bitmap;
    private Bitmap resultImg;
    private MediaMetadataRetriever media;
    private ImageView imageView;

    private VideoCapture vc;

    private boolean run;

    public static final int UPDATE = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case UPDATE:
                    imageView.setImageBitmap(resultImg);
                    Log.d(TAG,"Set image successfully");
                    break;
                default:
                    break;
            }
        }
    };
    private static final String TAG = "Videoprint";

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

        videoView = findViewById(R.id.videoView);

        String url = "http://218.197.116.220/120.79.73.18/video/c.mp4";

        videoView.setVideoURI(Uri.parse(url));

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setMediaPlayer(videoView);


        media = new MediaMetadataRetriever();
        media.setDataSource(url,new HashMap());

        vc = new VideoCapture(url);
        Log.d(TAG,"read video success");

        imageView = findViewById(R.id.shot);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                run = false;
            }
        });

        new Thread(new Runnable() {

            @Override
            public void run() {
                run = true;
                while(run){
                    int position = videoView.getCurrentPosition();
                    Log.v(TAG,""+position);

                    bitmap = media.getFrameAtTime(TimeUnit.MICROSECONDS.convert(position, TimeUnit.MILLISECONDS),media.OPTION_CLOSEST);
                    Log.d(TAG,"bitmap done");

//                    saveBitmap(bitmap);
                    int w = bitmap.getWidth();
                    int h = bitmap.getHeight();
                    int[] pixels = new int[w * h];
                    bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
                    int[] resultInt = faceDetect(pixels, w, h);
                    resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                    resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
//                    imageView.setImageBitmap(resultImg);
                    Message message = new Message();
                    message.what = UPDATE;
                    handler.sendMessage(message);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }}) {
        }.start();
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

    @Override
    public void onStop(){
        super.onStop();
        run = false;

    }

    public void saveBitmap(Bitmap bm) {
        Log.e(TAG, "保存图片");
        File f = new File("/sdcard/videoShot", System.currentTimeMillis()+"picName.jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i(TAG, "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}