package com.test.jzxx.poidemo;

import android.content.Intent;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.hwpf.usermodel.Range;
import org.w3c.dom.Document;



import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.WatchEvent;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity {
    private String docPath;
    private String docName;
    private String savePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=this.getIntent();
        String action = intent.getAction();
        if(intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            String path = uri.getPath();

            docPath = path.substring(0,path.lastIndexOf("/")+1);
            savePath = docPath;
            docName = path.substring(path.lastIndexOf("/")+1);
            Log.e("doc+save",savePath);

            String name = docName.substring(0, docName.indexOf("."));



            try {
                if (!(new File(savePath + name).exists()))
                    new File(savePath + name).mkdirs();
                convert2Html(docPath + docName, savePath + name + ".html");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //WebView加载显示本地html文件
            WebView webView = (WebView) this.findViewById(R.id.poiview);
            WebSettings webSettings = webView.getSettings();
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            webSettings.setSupportZoom(true);

            webSettings.setBuiltInZoomControls(true);

            webView.loadUrl("file://"+savePath + name + ".html");


        }
    }

    /**
     * word文档转成html格式
     * */
    public void convert2Html(String fileName, String outPutFile)
            throws TransformerException, IOException,
            ParserConfigurationException {

        HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();


        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);



        //设置图片路径，与下面的pic.writeImageContent的路径要一致
        wordToHtmlConverter.setPicturesManager(new PicturesManager()
        {
            public String savePicture(byte[] content,
                                      PictureType pictureType, String suggestedName,
                                      float widthInches, float heightInches )
            {
                String name = docName.substring(0,docName.indexOf("."));
                return name+"/"+suggestedName;
            }
        } );

        //从wrod文件中获取图片，指定路径保存起来，这个路径是要与wordToHtmlConverter.setPicturesManager的路径一致的
        //这样wordToHtmlConverter才能找到正确的图片
        List<Picture> pics=wordDocument.getPicturesTable().getAllPictures();
        if(pics!=null){
            for(int i=0;i<pics.size();i++){
                Picture pic = (Picture)pics.get(i);
                String picName = pic.suggestFullFileName();
//                if(picName.substring(picName.lastIndexOf(".")+1).equals("emf")){
//                    picName=picName.substring(0,picName.lastIndexOf("."))+".jpg";
//                }
                try {
                    String name = docName.substring(0,docName.indexOf("."));
                    pic.writeImageContent(new FileOutputStream(savePath+ name + "/"
                            + picName));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        //设置需要转换的word文件（设置Document）
        wordToHtmlConverter.processDocument(wordDocument);

//        wordToHtmlConverter.processDocumentPart(wordDocument,wordDocument.getRange());


        Document htmlDocument = wordToHtmlConverter.getDocument();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        DOMSource domSource = new DOMSource(htmlDocument);

        StreamResult streamResult = new StreamResult(out);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer serializer = tf.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

    //把Source转换成Result,转换的内容在上面已经设置好，也就是把htmlDocument的Source转换成ByteArrayOutputStream的Result
        serializer.transform(domSource, streamResult);
        out.close();
        Log.e("out",out.toString());
        Log.e("domSource",domSource.toString());
        Log.e("streamResult",streamResult.toString());
    //保存html文件
    writeFile(new String(out.toByteArray()), outPutFile);
}

    /**
     * 将html文件保存到sd卡
     * */
    public void writeFile(String content, String path) {
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }



            fos = new FileOutputStream(file);
            bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
            Log.e("bw",bw.toString());
            bw.write(content);


        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fos != null)
                    fos.close();
            } catch (IOException ie) {
            }
        }
    }


}
