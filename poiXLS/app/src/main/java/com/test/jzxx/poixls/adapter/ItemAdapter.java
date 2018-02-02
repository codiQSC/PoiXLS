package com.test.jzxx.poixls.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import com.test.jzxx.poixls.R;
import com.test.jzxx.poixls.bean.CellBean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2 0002.
 */

public class ItemAdapter extends BaseAdapter {

    private List<CellBean> list;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public ItemAdapter(List<CellBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
         this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.itemactivity, null);
        TextView textView=(TextView) convertView.findViewById(R.id.text);
        CellBean cellBean = list.get(position);





        return null;
    }
}
