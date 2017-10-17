package com.huidaforum.utils;

/**
 * Created by zhang on 2017/10/17.
 */

public class StringUtil {
    //将所有的 / 或者// 替换成\
    public static String getReviseResponseBody(String responseBody){
        String s = responseBody.replaceAll("\\\\", "/");
        return s;
    }
}
