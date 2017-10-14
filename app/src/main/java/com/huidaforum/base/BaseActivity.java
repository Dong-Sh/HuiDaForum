package com.huidaforum.base;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;


import com.huidaforum.R;
import com.huidaforum.utils.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Created by gui on 2017/7/26.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,SlidingPaneLayout.PanelSlideListener {



    protected void onCreate(Bundle savedInstanceState) {
        initSlideBackClose();
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setStatusBar();
        initView();
        initData();
        initListener();
        registComBtn();

        //注册广播

    }
    private void initSlideBackClose() {
        if (isSupportSwipeBack()) {
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            // 通过反射改变mOverhangSize的值为0，
            // 这个mOverhangSize值为菜单到右边屏幕的最短距离，
            // 默认是32dp，现在给它改成0
            try {
                Field overhangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                overhangSize.setAccessible(true);
                overhangSize.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);

            // 左侧的透明视图
            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);

            ViewGroup decorView = (ViewGroup) getWindow().getDecorView();


            // 右侧的内容视图
            ViewGroup decorChild = (ViewGroup) decorView.getChildAt(0);
            decorChild.setBackgroundColor(getResources()
                    .getColor(android.R.color.white));
            decorView.removeView(decorChild);
            decorView.addView(slidingPaneLayout);

            // 为 SlidingPaneLayout 添加内容视图
            slidingPaneLayout.addView(decorChild, 1);
        }
    }

    private boolean isSupportSwipeBack() {
        return true;
    }


    @BindColor(R.color.red)
    int red;
    public void setStatusBar() {
        StatusBarUtil.setColor(this,red);

        Class clazz = getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);

            extraFlagField.invoke(getWindow(),darkModeFlag,darkModeFlag);//状态栏透明且黑色字体

        }catch (Exception e){

        }
    }

    private  void registComBtn(){
        View view = findViewById(R.id.back);
        if(view!=null){
            view.setOnClickListener(this);
        }
    };
    public void startActivity(Class<?> targetActivity, Bundle bundle){
        Intent intent = new Intent(this, targetActivity);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                processClick(v);
                break;
        }
    }
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initData();
    public abstract void initListener();
    public abstract void processClick(View v);


    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
    }

    @Override
    public void onPanelClosed(View panel) {

    }


}
