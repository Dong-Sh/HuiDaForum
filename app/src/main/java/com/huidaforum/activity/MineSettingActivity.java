package com.huidaforum.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;

import butterknife.BindView;

/**
 * Created by xiaojiu on 2017/9/2.
 * 我的——>设置页面
 */

public class MineSettingActivity extends BaseActivity {

    @BindView(R.id.bt_mine_setting_exit)
    Button bt_mine_setting_exit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_setting;


    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        bt_mine_setting_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.putString(StaticValue.TOKEN,"",MineSettingActivity.this);
                SpUtil.putString(StaticValue.UserName,"",MineSettingActivity.this);
                SpUtil.putString(StaticValue.Password,"",MineSettingActivity.this);

                MethodUtil.sendMainBroadcast(MineSettingActivity.this);

                startActivity(new Intent(MineSettingActivity.this,LoginActivity.class));

                finish();
            }
        });
    }

    @Override
    public void processClick(View v) {

    }
}
