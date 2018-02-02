package com.test.jzxx.imagedemo.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.test.jzxx.imagedemo.R;
import com.test.jzxx.imagedemo.bean.ImageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ImageAdapter extends BaseAdapter {

    private List<ImageInfo> list;
    private Context mContext;
    private LayoutInflater inflater;
//    private AsyImageLoader asyImageLoader=new AsyImageLoader();


    public ImageAdapter(List<ImageInfo> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            convertView=inflater.inflate(R.layout.image_item,null);

         final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        String url=list.get(position).getImagePath();
        if(cancelPotentialLoad(url,imageView))
        {
            AsyImageLoader task=new AsyImageLoader(imageView,list);
            LoadedDrawable loadedDrawable=new LoadedDrawable(task);
            imageView.setImageDrawable(loadedDrawable);
            task.execute(position);
        }


        return convertView;
    }


    private boolean cancelPotentialLoad(String url,ImageView imageview){
        AsyImageLoader loadImageTask = getAsyncLoadImageTask(imageview);

        if (loadImageTask != null) {
            String bitmapUrl = loadImageTask.url;
            if ((bitmapUrl == null) || (!bitmapUrl.equals(url))) {
                loadImageTask.cancel(true);
            } else {
                // 相同的url已经在加载中.
                return false;
            }
        }
        return true;

    }


    //当 loadImageTask.cancel(true)被执行的时候，则AsyncLoadImageTask 就会被取消，
    //当AsyncLoadImageTask 任务执行到onPostExecute的时候，如果这个任务加载到了图片，
    //它也会把这个bitmap设为null了。
    //getAsyncLoadImageTask代码如下：
    public static AsyImageLoader getAsyncLoadImageTask(ImageView imageview){
        if (imageview != null) {
            Drawable drawable = imageview.getDrawable();
            if (drawable instanceof LoadedDrawable) {
                LoadedDrawable loadedDrawable = (LoadedDrawable)drawable;
                return loadedDrawable.getLoadImageTask();
            }
        }
        return null;
    }


}
