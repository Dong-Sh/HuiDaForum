package com.huidaforum.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

import static com.huidaforum.R.id.iv;
import static com.huidaforum.utils.WebAddress.createTxRec;
import static com.huidaforum.utils.WebAddress.getMoneyNum;
import static java.lang.Integer.parseInt;

public class MineMoneyCashActivity extends BaseActivity {


    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.iv_alipay_check)
    ImageView ivAlipayCheck;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.iv_weixin)
    ImageView ivWeixin;
    @BindView(R.id.iv_weixin_check)
    ImageView ivWeixinCheck;
    @BindView(R.id.et_money_cash_name)
    EditText etMoneyCashName;
    @BindView(R.id.et_money_cash_zhanghao)
    EditText etMoneyCashZhanghao;
    @BindView(R.id.et_money_cash_num)
    EditText etMoneyCashNum;
    @BindView(R.id.tv_money_cash_yue)
    TextView tvMoneyCashYue;
    @BindView(R.id.tv_money_cash_all)
    TextView tvMoneyCashAll;
    @BindView(R.id.bt_money_cash)
    Button btMoneyCash;
    @BindView(R.id.rl_weixin)
    RelativeLayout rlWeixin;
    @BindView(R.id.et_money_cash_phone)
    EditText etMoneyCashPhone;
    private String name;
    private String zhanghao;
    private String num;
    private String phone;
    private MineMoneyBean mineMoneyBean;
    private String txType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_money_cash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ivAlipayCheck.setEnabled(true);
        ivWeixinCheck.setEnabled(false);
        okgoForMine();

    }


    @Override
    public void initListener() {

    }

    @OnClick({R.id.tv_money_cash_all, R.id.rl_alipay,R.id.bt_money_cash, R.id.rl_weixin})
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.rl_alipay:
                ivAlipayCheck.setEnabled(true);
                ivWeixinCheck.setEnabled(false);

                break;
            case R.id.rl_weixin:
                ivAlipayCheck.setEnabled(false);
                ivWeixinCheck.setEnabled(true);
                break;

            case R.id.bt_money_cash:

                getInformation();
                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(zhanghao)||TextUtils.isEmpty(phone)){
                    Toast.makeText(this, "姓名、账号、电话，不可空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(num)|| Float.parseFloat(num)==0){
                    Toast.makeText(this, "提现金额不可空、不能为0", Toast.LENGTH_SHORT).show();
                    return;
                }
                okgoForTiXian();

                break;
            case R.id.tv_money_cash_all:
                etMoneyCashNum.setText(mineMoneyBean.getMoneyNum()+"");
                break;
        }
    }
    private void getInformation() {
        name = etMoneyCashName.getText().toString().trim();
        zhanghao = etMoneyCashZhanghao.getText().toString().trim();
        num = etMoneyCashNum.getText().toString().trim();
        phone = etMoneyCashPhone.getText().toString().trim();
        if (ivAlipayCheck.isEnabled()){
            txType = "zfb";
        }else if (ivWeixinCheck.isEnabled()){
            txType="wx";
        }
        Log.e("txType",txType);

    }
    private void okgoForMine() {
        OkGo.<String>post(getMoneyNum).tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, MineMoneyCashActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<MineMoneyBean> baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<MineMoneyBean>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            mineMoneyBean = baseBean.getData();
                            if (mineMoneyBean != null) {
                                tvMoneyCashYue.setText("余额" + mineMoneyBean.getMoneyNum() + "");
                            }
                        }
                    }
                });
    }

    private void okgoForTiXian() {
        OkGo.<String>post(createTxRec).tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, MineMoneyCashActivity.this))
                .params("txMoney", num)//提现金额
                .params("txAccountType", txType)//提现账户类型
                .params("txAccountNumber", zhanghao)//提现账号
                .params("txConcatName", name)//提现联系人
                .params("txConcatPhone", phone)//提现联系电话
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            Toast.makeText(MineMoneyCashActivity.this, "提现申请已提交", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MineMoneyCashActivity.this, baseBean.getFieldError().getValue(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
