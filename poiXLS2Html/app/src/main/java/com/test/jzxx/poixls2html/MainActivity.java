package com.test.jzxx.poixls2html;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.usermodel.Picture;
import org.w3c.dom.Document;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class MainActivity extends AppCompatActivity {

    private String docName;
    private String docPath;
//    private String savePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=this.getIntent();
        String action = intent.getAction();
        if(Intent.ACTION_VIEW.equals(action)){
            Uri uri = intent.getData();
            String path = uri.getPath();
            docName=path.substring(path.lastIndexOf("/")+1);
            docPath=path.substring(0,path.lastIndexOf("/")+1);

            String name=docName.substring(0,docName.lastIndexOf("."));

            try {

                if (!(new File(docPath + name).exists()))
                    new File(docPath + name).mkdirs();
                exceltoHtml(docPath+docName, docPath + name + ".html");
            }catch (Exception e){
                e.printStackTrace();

            }

            WebView webView=(WebView)findViewById(R.id.poiview);
            WebSettings settings = webView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);


            webView.loadUrl("file://"+docPath+name+".html");


        }


    }

    private void exceltoHtml(String path, String outputFile)  throws Exception{



        HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(path));
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        ExcelToHtmlConverter excelToHtmlConverter=new ExcelToHtmlConverter(document);



        List allPictures = workbook.getAllPictures();
            if (allPictures!=null){
                for (int i=0;i<allPictures.size();i++){
                    Picture pic = (Picture)allPictures.get(i);

                    String name=docName.substring(docName.lastIndexOf("."));

                    pic.writeImageContent(new FileOutputStream(docPath+name+"/"+pic.suggestFullFileName()));


                }

            }

        excelToHtmlConverter.processWorkbook(workbook);

        DOMSource domSource=new DOMSource(excelToHtmlConverter.getDocument());

        ByteArrayOutputStream out=new ByteArrayOutputStream();
        StreamResult streamResult=new StreamResult(out);

        TransformerFactory tf=TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.setOutputProperty(OutputKeys.METHOD,"html");

        transformer.transform(domSource,streamResult);
        out.close();

        writeFile(new String(out.toByteArray()),outputFile);


    }

    private void writeFile(String content, String htmlpath) {
        FileOutputStream fileOutputStream=null;
        BufferedWriter bw=null;

        try {
            File file = new File(htmlpath);
            if(!file.exists()){
                file.createNewFile();
            }


            fileOutputStream = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream,"utf-8"));
            bw.write(content);


        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException ie) {
            }
        }



    }
}
