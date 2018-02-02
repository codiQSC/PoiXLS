package com.test.jzxx.imagedemo.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ImageInfo implements Serializable{

        private String imageTitle;
        private String imagePath;
        private String imageId;
        private Bitmap imageBitmap;

    public ImageInfo(String imageTitle, String imagePath) {
        this.imageTitle = imageTitle;
        this.imagePath = imagePath;
    }

    public ImageInfo(String imageTitle, String imagePath, Bitmap imageBitmap) {
        this.imageTitle = imageTitle;
        this.imagePath = imagePath;
        this.imageBitmap = imageBitmap;
    }

    public ImageInfo(String imageTitle, String imagePath, String imageFaea) {
        this.imageTitle = imageTitle;
        this.imagePath = imagePath;
        this.imageId = imageFaea;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getImageId() {
        return imageId;
    }
}
