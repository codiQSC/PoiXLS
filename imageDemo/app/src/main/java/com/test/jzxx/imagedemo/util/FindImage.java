package com.test.jzxx.imagedemo.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.test.jzxx.imagedemo.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class FindImage  {
//    private AsyImageLoader asyImageLoader=new AsyImageLoader();
    private Context mContext;
    public FindImage(Context mContext){
        this.mContext=mContext;

    }


    public List<ImageInfo>  getImageList(){
        Cursor cursor=null;
        List<ImageInfo> imageList = new ArrayList<ImageInfo>();
            if(mContext!=null) {

                ContentResolver contentResolver = mContext.getContentResolver();
                cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);

/*
                asc 按升序排列
                    desc 按降序排列
                projection 是定义返回的数据，selection 通常的sql 语句，例如  selection=MediaStore.Images.ImageColumns.MIME_TYPE+"=? " 那么 selectionArgs=new String[]{"jpg"};
                String[] projection = new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DISPLAY_NAME};
                contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, MediaStore.Images.ImageColumns.DATE_MODIFIED + "  desc");
*/


                if (cursor != null) {

                    while (cursor.moveToNext()) {

                        String imageName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                        String imageId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));

//                        Bitmap imageBitmap=GetImageThumbnail.getImageThumbnail(path,250,250);

                        ImageInfo imageInfo = new ImageInfo(imageName, path);


                        imageList.add(imageInfo);

                    }

                    cursor.close();
                }

            }

            return imageList;

    }

    public void  getImage(final ImageView imageView,final String path){

//        Cursor cursor=null;
////        List<ImageInfo> imageList = new ArrayList<ImageInfo>();
//        if(mContext!=null) {
//
////            ContentResolver contentResolver = mContext.getContentResolver();
////            cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
//
//
//
//            if (cursor != null) {
//
//                while (cursor.moveToNext()) {
//                   //如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
////                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                    Drawable cacheImage = asyImageLoader.loadDrawable(path,new AsyImageLoader.ImageCallback() {
//                        //请参见实现：如果第一次加载url时下面方法会执行
//                        public void imageLoaded(Drawable imageDrawable) {
//                            imageView.setImageDrawable(imageDrawable);
//                        }
//                    });
//
//                    if(cacheImage!=null){
//                        imageView.setImageDrawable(cacheImage);
//                    }
//
//
//                }
//
//                cursor.close();
//            }
//
//        }

//        Drawable cacheImage = asyImageLoader.loadDrawable(path,new AsyImageLoader.ImageCallback() {
//            //请参见实现：如果第一次加载url时下面方法会执行
//            public void imageLoaded(Drawable imageDrawable) {
//                imageView.setImageDrawable(imageDrawable);
//            }
//        });
//
//        if(cacheImage!=null){
//            imageView.setImageDrawable(cacheImage);
//        }


    }




}
