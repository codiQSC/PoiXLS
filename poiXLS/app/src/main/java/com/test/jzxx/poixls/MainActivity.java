package com.test.jzxx.poixls;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.TextView;

import com.test.jzxx.poixls.bean.CellBean;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<CellBean> list=new ArrayList<CellBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout=findViewById(R.id.mdata);


        Intent intent = this.getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            try {

                Uri uri = intent.getData();
                String path = uri.getPath();
                File file = new File(path);
                DataFormatter formatter = new DataFormatter();
//                FileInputStream fileInputStream=new FileInputStream(file);
                Workbook wb=WorkbookFactory.create(file);
                Sheet sheetAt = wb.getSheetAt(0);
                int lastRowNum = sheetAt.getLastRowNum();
                int i=0;
                int j=0;






                for(Row row:sheetAt){

                    for(Cell cell:row){



                        int columnIndex = cell.getColumnIndex();
                        int rowIndex = cell.getRowIndex();
                        String text = formatter.formatCellValue(cell);
                        TextView textView=new TextView(this);
                        GridLayout.Spec rowSpec=GridLayout.spec(i);
                        GridLayout.Spec columnSpec=GridLayout.spec(j);
                        GridLayout.LayoutParams params=new GridLayout.LayoutParams(rowSpec,columnSpec);
                        params.columnSpec=GridLayout.spec(j,columnIndex);
                        params.rowSpec=GridLayout.spec(i,rowIndex);
                        params.width=50*rowIndex;
                        params.height=100*columnIndex;
                        textView.setText(text);
                        textView.setTextSize(10);
                        //设置居中
                        textView.setGravity(Gravity.CENTER);
                        gridLayout.addView(textView,params);
                        ++j;
                    }

                    ++i;
                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }


}
