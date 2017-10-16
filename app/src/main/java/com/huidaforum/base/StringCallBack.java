package com.huidaforum.base;

import com.google.gson.Gson;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by lenovo on 2017/10/16.
 */

public abstract class StringCallBack extends StringCallback {
    @Override
    public void onSuccess(Response<String> response) {
        Gson gson = new Gson();
        BaseBean baseBean = gson.fromJson(response.body(), BaseBean.class);
        if (baseBean.isSuccess()) {
            if (baseBean.getData() != null)
                onHDSuccess(response.body());
        } else {

        }
    }

    public abstract void onHDSuccess(String response);

}
