package com.huidaforum.activity;

import android.view.View;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.utils.StatusBarUtil;

/**
 * Created by xiaojiu on 2017/9/9.
 * 注册页面
 */

public class RegisterActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this,null);
    }
}
