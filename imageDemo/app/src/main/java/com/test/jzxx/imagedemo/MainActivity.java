package com.test.jzxx.imagedemo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.GridView;

import com.test.jzxx.imagedemo.bean.ImageInfo;
import com.test.jzxx.imagedemo.util.FindImage;
import com.test.jzxx.imagedemo.util.FindImage2;
import com.test.jzxx.imagedemo.util.ImageAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ImageAdapter mAdapter;
    private List<ImageInfo> list;
    //图片缓存用来保存GridView中每个Item的图片，以便释放
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FindImage findImage=new FindImage(this);
//        list= FindImage2.getAllPictures(this);
          FindImage findImage=new FindImage(this);
          list= findImage.getImageList();

        gridView=(GridView) findViewById(R.id.gridview);
        mAdapter=new ImageAdapter(list,this);
        gridView.setAdapter(mAdapter);
//        gridView.setOnScrollListener(this);


    }
//
//    //释放图片的函数
//    private void recycleBitmapCaches(int fromPosition,int toPosition){
//        Bitmap delBitmap = null;
//        for(int del=fromPosition;del<toPosition;del++){
//            delBitmap = gridviewBitmapCaches.get(list.get(del).getImagePath());
//            if(delBitmap != null){
//                //如果非空则表示有缓存的bitmap，需要清理
//                //从缓存中移除该del->bitmap的映射
//                gridviewBitmapCaches.remove(list.get(del).getImagePath());
//                delBitmap.recycle();
//                delBitmap = null;
//            }
//        }
//    }
//
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem,
//                         int visibleItemCount, int totalItemCount) {
//        // TODO Auto-generated method stub
//        //注释：firstVisibleItem为第一个可见的Item的position，从0开始，随着拖动会改变
//        //visibleItemCount为当前页面总共可见的Item的项数
//        //totalItemCount为当前总共已经出现的Item的项数
//        recycleBitmapCaches(0,firstVisibleItem);
//        recycleBitmapCaches(firstVisibleItem+visibleItemCount, totalItemCount);
//
//    }
//
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        // TODO Auto-generated method stub
//    }

}
