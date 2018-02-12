package com.test.jzxx.jiecaovideodemo.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class VideoInfo  implements Parcelable{
    private String videoPath;
    private String videoName;
    private Bitmap videoThumbnail;
    private int title;


    public VideoInfo(String videoPath, String videoName, Bitmap videoThumbnail) {
        this.videoPath = videoPath;
        this.videoName = videoName;
        this.videoThumbnail = videoThumbnail;
    }

    protected VideoInfo(Parcel in) {
        videoPath = in.readString();
        videoName = in.readString();
        videoThumbnail = in.readParcelable(Bitmap.class.getClassLoader());
        title = in.readInt();
    }

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel in) {
            return new VideoInfo(in);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };

    public String getVideoPath() {
        return videoPath;
    }

    public String getVideoName() {
        return videoName;
    }

    public Bitmap getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public void setVideoThumbnail(Bitmap videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(videoPath);
        dest.writeString(videoName);
        dest.writeParcelable(videoThumbnail, flags);
        dest.writeInt(title);
    }
}
