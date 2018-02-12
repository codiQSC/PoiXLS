package com.test.jzxx.jiecaovideodemo.utildemo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;


import com.test.jzxx.jiecaovideodemo.bean.VideoInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class FindVideo {

    private Context mContext;

    public FindVideo(Context context) {

        this.mContext = context;
    }

    public List<VideoInfo> getList() {
        List<VideoInfo> list = null;

          if (mContext != null){
              ContentResolver contentResolver = mContext.getContentResolver();
              Cursor cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null,null, null);

            if (cursor != null) {
                list = new ArrayList<VideoInfo>();
                while (cursor.moveToNext()) {
//                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE));  //视频文件的标题内容
//                    String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM));
//                    String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST));
//                    String displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
//                    String mimeType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE));
                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));  //
//                    long duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
//                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                    Bitmap videoThumbnail=getVideoThumbnail(path);
                    VideoInfo videoinfo = new VideoInfo(path,title,videoThumbnail);
                    list.add(videoinfo);

                }
                cursor.close();
            }
        }
        return list;
    }

//    /**
//     * 10M=10485760 b,小于10m的 过滤掉
//     * 过滤视频文件
//     *
//     * @param videoInfos
//     * @return
//     */
//    private List<VideoInfo> filterVideo(List<VideoInfo> videoInfos) {
//        List<VideoInfo> newVideos = new ArrayList<VideoInfo>();
//        for (VideoInfo videoInfo : videoInfos) {
//            File f = new File(videoInfo.getVideoPath());
//            if (f.exists() && f.isFile() && f.length() > 10485760) {
//                newVideos.add(videoInfo);
//                Log.i("TGA", "文件大小" + f.length());
//            } else {
//                Log.i("TGA", "文件太小或者不存在");
//            }
//        }
//
//        return newVideos;
//    }


    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
//            retriever.setDataSource(filePath);
//            bitmap=matrixBitmap(retriever.getFrameAtTime(),150,100);


            bitmap = ThumbnailUtils.createVideoThumbnail(filePath, MediaStore.Video.Thumbnails.MINI_KIND);
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, 150, 100, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }


    /**
     *
     * @param bitmap 原图，原图要是一个Bitmap对象，否则转不了
     * @param width 目标宽度
     * @param height 目标高度
     * @return
     */
    protected static Bitmap matrixBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);//开始计算，按照原图和目标的尺寸比例，得到宽和高分别缩放的比例大小
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);//按照一定的比例大小，得到新的Bitmap对象
        return newbmp;
    }

        

}

