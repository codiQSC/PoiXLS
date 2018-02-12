package com.test.jzxx.pdfdemo;

import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.source.DocumentSource;


import java.io.File;

public class MainActivity extends AppCompatActivity implements OnPageChangeListener,OnLoadCompleteListener,OnDrawListener {

    private PDFView pdfView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent=this.getIntent();
        String action = intent.getAction();
        pdfView =  findViewById(R.id.pdfView);
        if(intent.ACTION_VIEW.equals(action)){

            Uri uri = intent.getData();
            String path = uri.getPath();
            Log.e("path",path);
            File file=new File(path);
            displayFromFile(file);
//            displayFromUri(uri);

        }


        //从assets目录读取pdf
//        displayFromAssets("00100000.pdf");

        //从文件中读取pdf
//        displayFromFile( new File( "fileName"));

    }


    private void displayFromAssets(String assetFileName ) {

        pdfView.fromAsset(assetFileName)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
//                 .pages()  //把 5 过滤掉
                .load();
    }

    private  void displayFromUri(Uri uri){

        pdfView.fromUri(uri)
                .defaultPage(1)
                .enableSwipe(true)
                .onPageChange(this)
                .enableAnnotationRendering(false)
//                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();

    }


    private void displayFromFile(File file) {

        pdfView.fromFile(file)
//                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .enableSwipe(true)
                .onPageChange(this)
                .enableAnnotationRendering(false)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }

    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText( MainActivity.this , "page= " + page +
                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载完成回调
     * @param nbPages  总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText( MainActivity.this ,  "加载完成" + nbPages  , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
        // Toast.makeText( MainActivity.this ,  "pageWidth= " + pageWidth + "
        // pageHeight= " + pageHeight + " displayedPage="  + displayedPage , Toast.LENGTH_SHORT).show();
    }


}
