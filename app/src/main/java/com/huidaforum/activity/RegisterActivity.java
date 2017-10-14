package com.huidaforum.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.TokenBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;

/**
 * Created by xiaojiu on 2017/9/9.
 * 注册页面
 */

public class RegisterActivity extends BaseActivity {


    private static final String TAG = "RegisterActivity";
    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.bt_register_getcode)
    Button btRegisterGetcode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==0){
                btRegisterGetcode.setText("重新获取验证码");
            }else {
                btRegisterGetcode.setText(msg.what+"");
                handler.sendEmptyMessageDelayed(msg.what-1,1000);
            }
        }
    };

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

        btRegisterGetcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkGo.<String>post(WebAddress.sendRegistCode)
                        .params("phoneNum",etRegisterUsername.getText().toString().trim())
                        .execute(new StringCallback() {
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();

                                BaseBean beanBaseBean = gson.fromJson(response.body(),BaseBean.class);

                                if(beanBaseBean.isSuccess()){
                                    handler.sendEmptyMessage(180);
                                }else {
                                    Toast.makeText(RegisterActivity.this, beanBaseBean.getFieldError().getValue(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                Log.d(TAG, "onError: "+response.body());
                            }
                        });
            }
        });

        btLoginLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (TextUtils.isEmpty(etRegisterUsername.getText())) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etRegisterCode.getText())) {
                    Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etRegisterPassword.getText())) {
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etRegisterPasswordAgain.getText())) {
                    Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!etRegisterPassword.getText().toString().equals(etRegisterPasswordAgain.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkGo.<String>post(WebAddress.register)
                        .params("phoneNum", etRegisterUsername.getText().toString())
                        .params("phoneValidCode", etRegisterCode.getText().toString())
                        .params("devType", "phone")
                        .params("password", etRegisterPassword.getText().toString())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                                Gson gson = new Gson();

                                BaseBean<TokenBean> beanBaseBean = gson.fromJson(response.body(),new TypeToken<BaseBean<TokenBean>>(){}.getType());//泛型使用GSON解析

                                if(beanBaseBean.isSuccess()){

                                    TokenBean data = beanBaseBean.getData();

                                    SpUtil.putString(StaticValue.TOKEN,data.getValue(),RegisterActivity.this);
                                    SpUtil.putString(StaticValue.UserName,etRegisterUsername.getText().toString(),RegisterActivity.this);
                                    SpUtil.putString(StaticValue.Password,etRegisterPassword.getText().toString(),RegisterActivity.this);

                                    MethodUtil.sendMainBroadcast(RegisterActivity.this);

                                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                                    finish();
                                }
                                else{
                                    Log.d(TAG, "onSuccess is Error: "+beanBaseBean.getFieldError().getValue());
                                    Toast.makeText(RegisterActivity.this, beanBaseBean.getFieldError().getValue(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void processClick(View v) {

    }

}
