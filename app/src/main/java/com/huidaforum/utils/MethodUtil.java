package com.huidaforum.utils;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.huidaforum.base.BaseBean;

/**
 * Created by lenovo on 2017/9/22.
 */

public class MethodUtil {

    public static <T>BaseBean getBaseBean(String json){
        Gson gson = new Gson();
        BaseBean<T> baseBean = gson.fromJson(json,BaseBean.class);
        return baseBean;
    }

    public static void sendMainBroadcast(Context context) {
        Intent intent = new Intent(StaticValue.EXIT_ACTION);
        intent.putExtra(StaticValue.EXIT,StaticValue.EXIT);
        context.sendBroadcast(intent);
    }
}
