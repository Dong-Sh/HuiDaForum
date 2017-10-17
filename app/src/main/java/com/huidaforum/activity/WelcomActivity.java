package com.huidaforum.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.UserBean;
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
 * Created by xiaojiu on 2017/9/10.
 * 欢迎界面
 */

public class WelcomActivity extends BaseActivity {

    private static final String TAG = "WelcomActivity";
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    @BindView(R.id.rl_welcom)
    RelativeLayout rlWelcom;

    private int flag = 0;
    private boolean aflag = false;
    private String value;
    private boolean loginFlag = true;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: "+flag);
            if(!loginFlag){
                return;
            }
            if (aflag) {
                if(flag==0){
                    return;
                }
                loginFlag = false;
                switch (flag) {
                    case 1:
                        startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                        finish();
                        break;
                    case 2: {
                        Toast.makeText(WelcomActivity.this, value, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WelcomActivity.this, LoginActivity.class));
                        finish();
                        break;
                    }
                    case 3: {
                        Toast.makeText(WelcomActivity.this, "网络不行啦，请重新登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(WelcomActivity.this, LoginActivity.class);
                        intent.putExtra("flag", true);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    case 4:
                        startActivity(new Intent(WelcomActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
                finish();
            }

        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        //获取窗体对象
        final Window window = getWindow();
        //属性动画，0.2~1的变化
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.2f, 1f);
        valueAnimator.setDuration(40);
        valueAnimator.setDuration(1000);
        //差值器  线性，线性均匀改变
        valueAnimator.setInterpolator(new LinearInterpolator());
        //动画过程的监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前的参数值
                WindowManager.LayoutParams params = window.getAttributes();
                //获取当前运动点的值
                params.alpha = (float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                aflag = false;
                Log.d(TAG, "onAnimationStart: ");
                if (!TextUtils.isEmpty(SpUtil.getString(StaticValue.TOKEN, WelcomActivity.this))) {//用户已登录
                    OkGo.<String>post(WebAddress.login)
                            .params("devType", "phone")
                            .params("userCode", SpUtil.getString(StaticValue.UserName, WelcomActivity.this))
                            .params("password", SpUtil.getString(StaticValue.Password, WelcomActivity.this))
                            .execute(new StringCallback() {
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    BaseBean<UserBean> beanBaseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<UserBean>>() {
                                    }.getType());

                                    if (beanBaseBean.isSuccess()) {
                                        UserBean userBean = beanBaseBean.getData();

                                        SpUtil.putString(StaticValue.TOKEN, userBean.getToken(), WelcomActivity.this);

                                        flag = 1;

                                    } else {
                                        value = beanBaseBean.getFieldError().getValue();

                                        flag = 2;//用户被注销
                                    }
                                    handler.sendEmptyMessage(0);
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    flag = 3;
                                    handler.sendEmptyMessage(0);
                                }
                            });
                } else {
                    flag = 4;
                    handler.sendEmptyMessage(0);
                }

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                boolean sp = SpUtil.getBoolean(StaticValue.IS_OPENMAIN, WelcomActivity.this);
                if (sp) {
                    aflag = true;
                    handler.sendEmptyMessage(0);
                } else {//第一次进入
                    startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
                    finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.start();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
    }
}
