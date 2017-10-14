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
    private Drawable guanzhu_no;
    private Drawable guanzhu_yes;
    private Drawable pinglun_no;
    private Drawable pinglun_yes;

    public ThreeDrawable(Context context) {
        this.context = context;
        initDrawable();
    }

    private void initDrawable() {
        shoucang_no = getDrawable(R.drawable.collection_nor);
        shoucang_yes = getDrawable(R.drawable.collection_nor);
        guanzhu_no = getDrawable(R.drawable.collection_nor);
        guanzhu_yes = getDrawable(R.drawable.collection_nor);
        pinglun_no = getDrawable(R.drawable.collection_nor);
        pinglun_yes = getDrawable(R.drawable.collection_nor);
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

    public Drawable getGuanzhu_no() {
        return guanzhu_no;
    }

    public Drawable getGuanzhu_yes() {
        return guanzhu_yes;
    }

    public Drawable getPinglun_no() {
        return pinglun_no;
    }

    public Drawable getPinglun_yes() {
        return pinglun_yes;
    }
}
