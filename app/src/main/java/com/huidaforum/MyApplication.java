package com.huidaforum;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;

import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by gui on 2017/9/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkGo();
    }
    private void initOkGo() {
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //配置log打印
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);
            //配置cookie  配置到sp中
            builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
            HttpParams params = new HttpParams();
            params.put("devType", "phone");
            OkGo.getInstance()
                    .init(this)
                    .addCommonParams(params)
                    .setOkHttpClient(builder.build())
                    .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    .setRetryCount(3);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
