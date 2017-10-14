package com.huidaforum.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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

    public static void zanAndshoucang(final Context context, final TextView view, SchoolContentBean schoolContentBean, final ThreeDrawable threeDrawable) {

        switch (view.getId()) {
            case R.id.tv_zan: {
                Toast.makeText(context, "tv_zan", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_pinglun: {
                Toast.makeText(context, "tv_pinglun", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.tv_shoucang: {
                if (schoolContentBean.getShouchang().equals("yes")) {
                    OkGo.<String>post(WebAddress.shouchangdelect)
                            .params("contentCode", schoolContentBean.getContentCode())
                            .params("token", SpUtil.getString(StaticValue.TOKEN, context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    view.setCompoundDrawables(threeDrawable.getShoucang_no(), null, null, null);
                                    Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                    schoolContentBean.setShouchang("no");
                } else {
                    OkGo.<String>post(WebAddress.getshouchang)
                            .params("contentCode", schoolContentBean.getContentCode())
                            .params("token", SpUtil.getString(StaticValue.TOKEN, context))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    view.setCompoundDrawables(threeDrawable.getShoucang_yes(), null, null, null);
                                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                    schoolContentBean.setShouchang("yes");
                }
                break;
            }
        }
    }

}
