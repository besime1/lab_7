package com.example.lab_7;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    Context context;
    String[] list;

    public Adapter(Context context) {
        this.context = context;
        list = new String[0];
    }

    public void setData(String[] list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(16, 16, 16, 16);
        } else {
            textView = (TextView) view;
        }
        textView.setTextSize(20);
        textView.setText(list[i].split(",")[0]);
        return textView;
    }
}
