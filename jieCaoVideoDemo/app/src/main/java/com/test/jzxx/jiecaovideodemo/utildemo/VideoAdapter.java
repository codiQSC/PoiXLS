package com.test.jzxx.jiecaovideodemo.utildemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.jzxx.jiecaovideodemo.R;
import com.test.jzxx.jiecaovideodemo.bean.LoadedImage;
import com.test.jzxx.jiecaovideodemo.bean.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class VideoAdapter extends BaseAdapter{
    private List<VideoInfo> list;
    private Context context;
    private LayoutInflater mInflater;
//    private ArrayList<LoadedImage> photos = new ArrayList<LoadedImage>();
    public VideoAdapter(Context context ,List<VideoInfo> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

//    public void addPhoto(LoadedImage image){
//        photos.add(image);
//    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




            convertView = mInflater.inflate(R.layout.video_item, null);
            ImageView  img = (ImageView) convertView.findViewById(R.id.iv_img);
            TextView  title = (TextView) convertView.findViewById(R.id.iv_name);
//            hold.tv_time = (TextView) convertView.findViewById(R.id.time);



        title.setText(list.get(position).getVideoName());
//        long min = list.get(position).getDuration() /1000 / 60;
//        long sec = list.get(position).getDuration() /1000 % 60;

//        hold.tv_time.setText(min+":"+sec);

        img.setImageBitmap(list.get(position).getVideoThumbnail());
        return convertView;
    }

}
