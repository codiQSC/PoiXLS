package com.test.jzxx.poixls.bean;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class CellBean {
    private  int rowSpan;
    private int columnSpan;
    private String textValue;


    public CellBean(int rowSpan, int columnSpan, String textValue) {
        this.rowSpan = rowSpan;
        this.columnSpan = columnSpan;
        this.textValue = textValue;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }

    public void setColumnSpan(int columnSpan) {
        this.columnSpan = columnSpan;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public String getTextValue() {
        return textValue;
    }
}
