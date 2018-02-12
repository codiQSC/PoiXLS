package com.test.jzxx.jiecaovideodemo.bean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class LoadedImage {
  private Bitmap Bitmap;
  private String title;
  private long Duration;

    public android.graphics.Bitmap getBitmap() {
        return Bitmap;
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return Duration;
    }

    public void setBitmap(android.graphics.Bitmap bitmap) {
        Bitmap = bitmap;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(long duration) {
        Duration = duration;
    }
}
