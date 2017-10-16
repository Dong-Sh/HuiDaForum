package com.huidaforum.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huidaforum.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gui on 2017/10/16.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public GridViewAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 6) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_gridview, parent,false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.iv_pic);
        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
          //Picasso.with(mContext).load(picUrl).into(iv);
            Glide.with(mContext).load(picUrl).into(iv);
        } else {
            iv.setImageResource(R.mipmap.release_add);//最后一个显示加号图片
        }
        return convertView;
    }

}
