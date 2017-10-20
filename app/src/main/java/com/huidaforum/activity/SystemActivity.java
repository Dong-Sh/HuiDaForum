package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.SystemBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.huidaforum.view.CircleTransform;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

import static com.huidaforum.R.id.iv_system_pic;


public class SystemActivity extends BaseActivity {
    @BindView(R.id.rv_system)
    RecyclerView rv_System;
    @BindView(R.id.sr_system)
    SmartRefreshLayout sr_system;
    private List<SystemBean> systemBeanList;
    private SystemAdapter systemAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_system;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //下拉刷新头样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SystemActivity.this);
        rv_System.setLayoutManager(linearLayoutManager);
        sr_system.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        getOkGo();//调用从网络获取系统消息方法
    }

    @Override
    public void initListener() {

        //下拉刷新监听
        sr_system.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getOkGo();
            }
        });
        //上拉加载
//        sr_system.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore(2000);
//            }
//        });
    }

    @Override
    public void processClick(View v) {

    }

    public void getOkGo() {
        //从网络获取系统消息
        OkGo.<String>post(WebAddress.systemMsg)
                .tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<SystemBean>> baseBean = gson.fromJson(response.body(),
                                new TypeToken<BaseBean<List<SystemBean>>>() {
                                }.getType());
                        if (baseBean.isSuccess()) {
                            systemBeanList = baseBean.getData();
                            if (systemAdapter == null) {
                                systemAdapter = new SystemAdapter();
                                rv_System.setAdapter(systemAdapter);
                                return;
                            }
                            systemAdapter.notifyDataSetChanged();
                        }
                    }

                });
    }

    //系统消息Adapter
    public class SystemAdapter extends BaseQuickAdapter<SystemBean, BaseViewHolder> {
        public SystemAdapter() {
            super(R.layout.system_item, systemBeanList);
        }


        protected void convert(BaseViewHolder helper, SystemBean item) {
            helper.setText(R.id.tv_system_time, item.getCreateTime())
                    .setText(R.id.tv_system_title, item.getTitle())
                    .setText(R.id.tv_system_content, item.getContent());
            Picasso.with(SystemActivity.this)
                    .load(item.getHeadPhoto())
                    .transform(new CircleTransform())
                    .fit()
                    .into((ImageView) helper.getView(R.id.iv_system_pic));
        }
    }


}
