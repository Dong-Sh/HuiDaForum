package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.jude.rollviewpager.RollPagerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/10/13.
 */

public class SchoolActivity extends BaseActivity {
    private static final String TAG = "SchoolActivity";
    @BindView(R.id.rpv_community)
    RollPagerView rpvCommunity;
    @BindView(R.id.rlv_community)
    RecyclerView rlvCommunity;
    private BaseBean<List<SchoolContentBean>> baseBean;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_community;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initNetData();
    }

    private void initNetData() {
        OkGo.<String>post(WebAddress.bySchoolNameFindContent)
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .params("id", getIntent().getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<List<SchoolContentBean>>>() {
                        }.getType());

                        Log.d(TAG, "onSuccess: " + baseBean.getData());
                    }
                });
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
}
