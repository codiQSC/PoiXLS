package com.test.jzxx.imagedemo.util;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.test.jzxx.imagedemo.MainActivity;
import com.test.jzxx.imagedemo.bean.ImageInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class AsyImageLoader extends AsyncTask<Integer,Void,List<ImageInfo>> {
    public String url = null;
    private WeakReference<ImageView> imageViewWeakReference;
    private List<ImageInfo> mlist;
    private ImageView imageView;
    private Integer param;


    public AsyImageLoader(ImageView imageView, List<ImageInfo> mlist){

        this.mlist=mlist;
        this.imageView=imageView;
        imageViewWeakReference=new WeakReference<ImageView>(imageView);

    }

    @Override
    protected List<ImageInfo> doInBackground(Integer... params) {
        this.param=params[0];
        this.url = mlist.get(params[0]).getImagePath();
        Bitmap bitmap = GetImageThumbnail.getImageThumbnail(url, 250, 250);
        mlist.get(params[0]).setImageBitmap(bitmap);
        MainActivity.gridviewBitmapCaches.put(mlist.get(params[0]).getImagePath(),mlist.get(params[0]).getImageBitmap());
        return mlist;

    }

    @Override
    protected void onPostExecute(List<ImageInfo> imageInfos) {
        if(isCancelled()){
            imageInfos.get(param).setImageBitmap(null);
        }
        if(imageViewWeakReference!=null){
                ImageView imageView=imageViewWeakReference.get();
                AsyImageLoader asyImageLoader=ImageAdapter.getAsyncLoadImageTask(imageView);
                if (this==asyImageLoader){

                    imageView.setImageBitmap(imageInfos.get(param).getImageBitmap());
                }
        }



        super.onPostExecute(imageInfos);
    }








}
