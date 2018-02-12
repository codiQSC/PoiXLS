package com.test.jzxx.jiecaovideodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.test.jzxx.jiecaovideodemo.bean.VideoInfo;
import com.test.jzxx.jiecaovideodemo.utildemo.FindVideo;
import com.test.jzxx.jiecaovideodemo.utildemo.VideoAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class ShowActivity extends Activity {

    private GridView gridview;
    VideoAdapter mAdapter;
    List<VideoInfo> videoLists;
    int videoSize;
    AlertDialog alert_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        FindVideo findVideo=new FindVideo(this);
        videoLists = findVideo.getList();
//        videoSize = listVideos.size();
        gridview = (GridView) findViewById(R.id.gridview);
        mAdapter=new VideoAdapter(this,videoLists);

        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(ShowActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("videoInfo",videoLists.get(position));
//                bundle.putSerializable("videoInfo", videoLists.get(position));

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}
