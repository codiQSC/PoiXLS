package com.test.jzxx.imagedemo.util;

import android.graphics.drawable.ColorDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class LoadedDrawable extends ColorDrawable {
    private final  WeakReference<AsyImageLoader> loadImageTaskReference;

    public LoadedDrawable(AsyImageLoader asyImageLoader){

        loadImageTaskReference=new WeakReference<AsyImageLoader>(asyImageLoader);
    }


    public AsyImageLoader getLoadImageTask(){

        return loadImageTaskReference.get();
    }

}
