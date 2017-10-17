package com.huidaforum.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.huidaforum.R;

/**
 * Created by lenovo on 2017/10/14.
 */

public class ThreeDrawable {

    private Context context;
    private Drawable shoucang_no;
    private Drawable shoucang_yes;
    private Drawable zan_no;
    private Drawable zan_yes;
    private Drawable pinglun_no;
    private Drawable pinglun_yes;

    public ThreeDrawable(Context context) {
        this.context = context;
        initDrawable();
    }

    private void initDrawable() {
        shoucang_no = getDrawable(R.drawable.collection_nor);
        shoucang_yes = getDrawable(R.drawable.collection_pre);
        zan_no = getDrawable(R.drawable.fabulous_nor);
        zan_yes = getDrawable(R.drawable.fabulous_pre);
        pinglun_no = getDrawable(R.drawable.comment_nor);
        pinglun_yes = getDrawable(R.drawable.comment_pre);

        int width = 45;
        int height = 45;
        shoucang_no.setBounds(0,0,width,height);
        shoucang_yes.setBounds(0,0,width,height);
        zan_no.setBounds(0,0,width,height);
        zan_yes.setBounds(0,0,width,height);
        pinglun_no.setBounds(0,0,width,height);
        pinglun_yes.setBounds(0,0,width,height);
    }

    private Drawable getDrawable(int collection_nor) {
        return context.getResources().getDrawable(collection_nor);
    }


    public Context getContext() {
        return context;
    }

    public Drawable getShoucang_no() {
        return shoucang_no;
    }

    public Drawable getShoucang_yes() {
        return shoucang_yes;
    }

    public Drawable getZan_no() {
        return zan_no;
    }

    public Drawable getZan_yes() {
        return zan_yes;
    }

    public Drawable getPinglun_no() {
        return pinglun_no;
    }

    public Drawable getPinglun_yes() {
        return pinglun_yes;
    }
}
