package com.huidaforum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.UserBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StatusBarUtil;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/9/9.
 * 登录界面
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.bt_login_register)
    TextView bt_login_register;
    @BindView(R.id.et_login_username)
    EditText etLoginUsername;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login_forget)
    TextView btLoginForget;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        boolean flag = getIntent().getBooleanExtra("flag", false);
        if(flag){
            etLoginUsername.setText(SpUtil.getString(StaticValue.UserName,this));
            etLoginPassword.setText(SpUtil.getString(StaticValue.Password,this));
        }
    }

    @Override
    public void initListener() {
        bt_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//注册
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        btLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//登录
                OkGo.<String>post(WebAddress.login)
                        .params("devType","phone")
                        .params("userCode",etLoginUsername.getText().toString())
                        .params("password",etLoginPassword.getText().toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                BaseBean<UserBean> beanBaseBean = gson.fromJson(response.body(),new TypeToken<BaseBean<UserBean>>(){}.getType());

                                if(beanBaseBean.isSuccess()){
                                    UserBean userBean = beanBaseBean.getData();

                                    SpUtil.putString(StaticValue.TOKEN,userBean.getToken(),LoginActivity.this);
                                    SpUtil.putString(StaticValue.UserName,etLoginUsername.getText().toString(),LoginActivity.this);
                                    SpUtil.putString(StaticValue.Password,etLoginPassword.getText().toString(),LoginActivity.this);
                                    SpUtil.putString(StaticValue.aliveDay,userBean.getAliveDays(),LoginActivity.this);
                                    SpUtil.putString(StaticValue.HeadPhoto,userBean.getHeadPhoto(),LoginActivity.this);
                                    SpUtil.putString(StaticValue.nickName,userBean.getNickName(),LoginActivity.this);


                                    MethodUtil.sendMainBroadcast(LoginActivity.this);

                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this, beanBaseBean.getFieldError().getValue(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                            }
                        });
            }
        });
    }

    public void processClick(View v) {

    }

    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            MethodUtil.sendMainBroadcast(this);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
