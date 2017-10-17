package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.activity.SchoolActivity;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.Bean;
import com.huidaforum.bean.SchoolBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.jude.rollviewpager.RollPagerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页中社区页面
 */

public class CommunityFragment extends BaseFragment {
    private static final String TAG = "CommunityFragment";
    @BindView(R.id.rpv_community)
    RollPagerView rpvCommunity;
    @BindView(R.id.rlv_community)
    RecyclerView rlvCommunity;
    Unbinder unbinder;
    private BaseBean<List<SchoolBean>> baseBean;

    @Override
    public View initView() {

        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_community, null);
        return view;
    }

    @Override
    protected void initData() {
        initNetData();
    }

    private void initNetData() {
        OkGo.<String>post(WebAddress.listSchoolNames)
                .params("token", SpUtil.getString(StaticValue.TOKEN, mActivity))
                .execute(new StringCallback() {
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<List<SchoolBean>>>() {
                        }.getType());
                        Log.d(TAG, "onSuccess: " + baseBean.getData());

                        initGridView();
                    }
                });
    }

    private void initGridView() {
        rlvCommunity.setLayoutManager(new GridLayoutManager(mActivity, 3));
        Myadapter adapter = new Myadapter();
        rlvCommunity.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (baseBean != null)
                    if (baseBean.isSuccess()) {

                        Intent intent = new Intent(mActivity, SchoolActivity.class);
                        intent.putExtra("id", baseBean.getData().get(position).getId());
                        startActivity(intent);
                    } else {
                        Toast.makeText(mActivity, "网络有问题，请重试", Toast.LENGTH_SHORT).show();
                        initNetData();
                    }
                else {
                    Toast.makeText(mActivity, "网络有问题，请重试", Toast.LENGTH_SHORT).show();
                    initNetData();
                }

            }
        });
    }

    class Myadapter extends BaseQuickAdapter<SchoolBean, BaseViewHolder> {

        public Myadapter() {
            super(R.layout.item_community, baseBean.getData());
        }

        @Override
        protected void convert(BaseViewHolder holder, SchoolBean item) {
            holder.setText(R.id.tv, item.getSchoolName());
            ImageView iv = holder.getView(R.id.iv);
            Picasso.with(mActivity).load(item.getSchoolPhoto()).fit().into(iv);
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
