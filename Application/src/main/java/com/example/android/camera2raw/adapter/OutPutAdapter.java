package com.example.android.camera2raw.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Size;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OutPutAdapter extends BaseAdapter {
    MyItemClickListener mItemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    Context mContext;
    List<Size> mSizeList;
    public OutPutAdapter(Context context, List<Size> sizeList) {
        if(sizeList == null){
            mSizeList = new ArrayList<>();
        }
        else{
            mSizeList = sizeList;
        }

        mContext = context;
    }

    public void setSizeList(List<Size> sizeList) {
        mSizeList = sizeList;
    }

    @Override
    public int getCount() {
        return mSizeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSizeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tv;
        if(convertView!=null){
            tv = (TextView) convertView;
        }
        else{
            tv = (TextView) LayoutInflater.from(mContext).inflate(android.R.layout.simple_dropdown_item_1line,null);
        }
        tv.setPadding(0,10,0,10);
        tv.setTextColor(Color.parseColor("#EEffffff"));
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.parseColor("#00ffffff"));
        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onclick(position);
            }
        });*/
        int width = mSizeList.get(position).getWidth();
        int height = mSizeList.get(position).getHeight();
        int ratio_number = gcd(width,height);
        int width_ratio = width/ratio_number;
        int height_ratio = height/ratio_number;
        tv.setText(width+" × "+height+"("+width_ratio+":"+height_ratio+")");
        return tv;

    }
    public interface MyItemClickListener {
        void onclick(int position);
    }
    private int gcd(int a,int b) {
        int gcd = 0,c;
        if(a > b)
        {
            while(b != 0)
            {
                a = a % b;
                if(a < b)
                {
                    c = a;
                    a = b;
                    b = c;
                }
                gcd = a;
            }
        }
        if(a == b)
            gcd = a;
        else
        {
            c = a;a = b;b = c;
            while(b != 0)
            {
                a = a % b;
                if(a < b){c = a;a = b;b = c;}
                gcd = a;
            }
        }
        return gcd;
    }
}

