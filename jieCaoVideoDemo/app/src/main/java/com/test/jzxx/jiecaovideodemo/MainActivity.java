package com.test.jzxx.jiecaovideodemo;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.test.jzxx.jiecaovideodemo.bean.VideoInfo;

import java.io.File;
import java.io.Serializable;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {
    private JCVideoPlayerStandard jcVideoPlayerStandard;
    private Intent intent;
    private Long firstTime=0l;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ImageView imageView=new ImageView(this);
        super.onCreate(savedInstanceState);
//        设置横屏，一定要在setContentView（）之前,同时也可以在AndroidManiFest.xml中添加android:screenOrientation="landscape"
//        来设置强制横屏的设置
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        setContentView(R.layout.activity_video);



        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //去掉虚拟按键全屏显示,但有些机型点击屏幕还是会出现，所以不用
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        jcVideoPlayerStandard.TOOL_BAR_EXIST = false;

        //设置播放视频的地址
//        jcVideoPlayerStandard.setUp(videoInfo.getVideoPath(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoInfo.getVideoName());

//        jcVideoPlayerStandard.loop  = true;//是否循环播放

        //把播放的内容图片插入到要显示的播放器中
//        Glide.with(getApplicationContext()).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640")
//                .into(jcVideoPlayerStandard.thumbImageView);

//        jcVideoPlayerStandard.widthRatio = 4;//播放比例
//        jcVideoPlayerStandard.heightRatio = 3;

//        intent = this.getIntent();



        jcVideoPlayerStandard.backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        intent = getIntent();
        String action = intent.getAction();

        if (intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            String videoPath = uri.getPath();
            Log.e("uri", uri.toString());
//            ContentResolver cursor = this.getContentResolver();
            play(videoPath,"");

        }else{
            Bundle extras = intent.getExtras();
            VideoInfo videoInfo = (VideoInfo) extras.getParcelable("videoInfo");

            play(videoInfo.getVideoPath(),videoInfo.getVideoName());
        }



    }

    public void play(String videoPath,String videoName){


        jcVideoPlayerStandard.setUp(videoPath, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,videoName);
        jcVideoPlayerStandard.startWindowFullscreen();
    }



    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();

        if (secondTime - firstTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }

//        super.onBackPressed();


    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


}

