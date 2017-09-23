package com.huidaforum.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.bean.BaseBean;

/**
 * Created by lenovo on 2017/9/22.
 */

public class MethodUtil {

    public static <T>BaseBean getBaseBean(String json){
        Gson gson = new Gson();
        BaseBean<T> baseBean = gson.fromJson(json,BaseBean.class);
        return baseBean;
    }
}
