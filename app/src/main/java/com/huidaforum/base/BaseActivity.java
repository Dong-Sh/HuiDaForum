package com.huidaforum.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;


import com.huidaforum.R;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import butterknife.BindColor;
import butterknife.ButterKnife;

/**
 * Created by gui on 2017/7/26.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener  {



    protected void onCreate(Bundle savedInstanceState) {
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





}
