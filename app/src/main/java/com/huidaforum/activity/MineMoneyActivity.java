package com.huidaforum.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineMoneyBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.huidaforum.utils.WebAddress.createTxRec;
import static com.huidaforum.utils.WebAddress.getMoneyNum;

/**
 * Created by xiaojiu on 2017/9/3.
 * 我的钱包页面
 */

public class MineMoneyActivity extends BaseActivity {
    @BindView(R.id.mine_money_num)
    TextView mineMoneyNum;
    @BindView(R.id.tv_money_tixian)
    TextView tvMoneyTixian;
    @BindView(R.id.tv_money_record)
    TextView tvMoneyRecord;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_money;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        OkGo.<String>post(getMoneyNum).tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, MineMoneyActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<MineMoneyBean> baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<MineMoneyBean>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            MineMoneyBean mineMoneyBean = baseBean.getData();
                            if (mineMoneyBean != null) {
                                mineMoneyNum.setText(mineMoneyBean.getMoneyNum() + "");
                                if (mineMoneyBean.getMoneyNum() >= 50) {
                                    tvMoneyTixian.setClickable(true);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void initListener() {
    }

    @Override
    public void processClick(View v) {

    }


    @OnClick({ R.id.tv_money_tixian, R.id.tv_money_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
              case R.id.tv_money_tixian:
                OkGo.<String>post(createTxRec).tag(this)
                        .params("devType","phone")
                        .params("token",SpUtil.getString(StaticValue.TOKEN, MineMoneyActivity.this))
                        .params("txMoney","")//提现金额
                        .params("txAccountType","")//提现账户类型
                        .params("txAccountNumber","")//提现账号
                        .params("txConcatName","")//提现联系人
                        .params("txConcatPhone","")//提现联系电话
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                BaseBean baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean>() {
                                }.getType());
                                if (baseBean.isSuccess()){

                                }else {
                                    Toast.makeText(MineMoneyActivity.this,baseBean.getFieldError().getValue(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.tv_money_record:
                startActivity(RecordActivity.class,null);
                break;
        }
    }
}
