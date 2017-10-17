package com.huidaforum.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huidaforum.MyApplication;
import com.huidaforum.R;
import com.huidaforum.activity.SchoolActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.SchoolContentBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by lenovo on 2017/9/22.
 */

public class MethodUtil {

    private static ThreeDrawable threeDrawable;

    public static <T> BaseBean getBaseBean(String json) {
        Gson gson = new Gson();
        BaseBean<T> baseBean = gson.fromJson(json, BaseBean.class);
        return baseBean;
    }

    public static void sendMainBroadcast(Context context) {
        Intent intent = new Intent(StaticValue.EXIT_ACTION);
        intent.putExtra(StaticValue.EXIT, StaticValue.EXIT);
        context.sendBroadcast(intent);
    }

    private static void initThreeDrawable(Context context) {
        if (context instanceof Activity) {
            threeDrawable = ((MyApplication) (((Activity) context).getApplication())).threeDrawable;
        } else
            threeDrawable = ((MyApplication) context.getApplicationContext()).threeDrawable;
    }

    public static void setTextDrawableLeft(Context context, TextView textView, int name, String flag) {
        Drawable yes = null;
        Drawable no = null;
        initThreeDrawable(context);
        switch (name) {
            case StaticValue.ZAN:
                yes = threeDrawable.getZan_yes();
                no = threeDrawable.getZan_no();
                break;
            case StaticValue.SHOWCANG:
                yes = threeDrawable.getShoucang_yes();
                no = threeDrawable.getShoucang_no();
                break;
            case StaticValue.PINGLUN:
                yes = threeDrawable.getPinglun_yes();
                no = threeDrawable.getPinglun_no();
                break;
        }

        if (flag.equals("yes"))
            textView.setCompoundDrawables(yes, null, null, null);
        else {
            textView.setCompoundDrawables(no, null, null, null);
        }
    }

    public static void zanAndshoucang(final Context context, final TextView view, final SchoolContentBean schoolContentBean) {
        initThreeDrawable(context);
        switch (view.getId()) {
            case R.id.tv_zan: {
                if (schoolContentBean.getLaud().equals("yes")) {
                    Toast.makeText(context, "你已经赞过了", Toast.LENGTH_SHORT).show();
                } else {
                    OkGo.<String>post(WebAddress.getzan)
                            .params("ownerContentId", schoolContentBean.getId())
                            .params("token", SpUtil.getString(StaticValue.TOKEN, context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    BaseBean baseBean = gson.fromJson(response.body(), BaseBean.class);
                                    if (baseBean.isSuccess()) {
                                        view.setCompoundDrawables(threeDrawable.getZan_yes(), null, null, null);
                                        schoolContentBean.setLaud("yes");
                                        int count = Integer.parseInt(((TextView) view).getText().toString()) + 1;
                                        view.setText(count + "");
                                    }
                                }
                            });
                }
                break;
            }
            case R.id.tv_pinglun: {
                Toast.makeText(context, "tv_pinglun", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_shoucang: {
                if (schoolContentBean.getShouchang().equals("yes")) {
                    OkGo.<String>post(WebAddress.shouchangdelect)
                            .params("contentCode", schoolContentBean.getId())
                            .params("token", SpUtil.getString(StaticValue.TOKEN, context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    view.setCompoundDrawables(threeDrawable.getShoucang_no(), null, null, null);
                                    Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                    schoolContentBean.setShouchang("no");
                                }
                            });

                } else {
                    OkGo.<String>post(WebAddress.getshouchang)
                            .params("contentCode", schoolContentBean.getId())
                            .params("token", SpUtil.getString(StaticValue.TOKEN, context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    view.setCompoundDrawables(threeDrawable.getShoucang_yes(), null, null, null);
                                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                                    schoolContentBean.setShouchang("yes");
                                }
                            });

                }
                break;
            }
        }
    }

}
