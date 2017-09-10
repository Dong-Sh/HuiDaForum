package com.huidaforum.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/9/10.
 * 欢迎界面
 */

public class WelcomActivity extends BaseActivity {

    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;
    @BindView(R.id.rl_welcom)
    RelativeLayout rlWelcom;
    public static final String IS_OPENMAIN = "is_openmain";

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
        valueAnimator.setDuration(4000);
        //差值器  线性，线性均匀改变
        valueAnimator.setInterpolator(new LinearInterpolator());
        //动画过程的监听
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取当前的参数值
                WindowManager.LayoutParams params = window.getAttributes();
                //获取当前运动点的值
                params.alpha= (float) animation.getAnimatedValue();
                window.setAttributes(params);
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                boolean sp = SpUtil.getBoolean(IS_OPENMAIN, WelcomActivity.this);
                if(sp){
                    Intent intent = new Intent(WelcomActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(WelcomActivity.this, GuideActivity.class);
                    startActivity(intent);
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
        StatusBarUtil.setTransparentForImageView(this,null);
    }
}
