package com.huidaforum.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.huidaforum.R;

import butterknife.ButterKnife;

/**
 * Created by gui on 2017/7/26.
 */
public abstract class BaseActivity extends Activity implements View.OnClickListener  {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
        registComBtn();
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
