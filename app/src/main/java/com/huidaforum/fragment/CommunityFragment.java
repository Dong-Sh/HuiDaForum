package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huidaforum.R;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.Bean;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页中社区页面
 */

public class CommunityFragment extends BaseFragment {
    @BindView(R.id.rpv_community)
    RollPagerView rpvCommunity;
    @BindView(R.id.rlv_community)
    RecyclerView rlvCommunity;
    Unbinder unbinder;
    private ArrayList<Bean> beanList;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_community, null);
        return view;
    }

    @Override
    protected void initData() {
         initGridView();


    }

    private void initGridView() {
        beanList = new ArrayList<>();
        Bean 燕山大学 = new Bean("燕山大学", R.mipmap.school_yanshan);
        Bean 科技师范 = new Bean("科技师范", R.mipmap.school_keshi);
        Bean 东北大学 = new Bean("东北大学", R.mipmap.school_dongbei);
        Bean 职业技术学院 = new Bean("职业技术学院", R.mipmap.school_zhiye);
        Bean 东北石油 = new Bean("东北石油", R.mipmap.school_dongyou);
        Bean 外国语职业学院 = new Bean("外国语职业学院", R.mipmap.school_waiguoyu);
        Bean 环境工程学院 = new Bean("环境工程学院", R.mipmap.school_huanjing);
        Bean 华北煤炭 = new Bean("华北煤炭", R.mipmap.school_huabei);
        Bean 建材职业技术学院 = new Bean("建材职业技术学院", R.mipmap.school_jiancai);
        beanList.add(燕山大学);
        beanList.add(科技师范);
        beanList.add(东北大学);
        beanList.add(职业技术学院);
        beanList.add(东北石油);
        beanList.add(外国语职业学院);
        beanList.add(环境工程学院);
        beanList.add(华北煤炭);
        beanList.add(建材职业技术学院);
        rlvCommunity.setLayoutManager(new GridLayoutManager(mActivity,3));
        Myadapter adapter = new Myadapter();
        rlvCommunity.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "第"+position+"个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class  Myadapter extends BaseQuickAdapter<Bean,BaseViewHolder>{

      public Myadapter() {
          super(R.layout.item_community, beanList);
      }

      @Override
      protected void convert(BaseViewHolder holder, Bean item) {
          holder.setText(R.id.tv,item.getName());
          ImageView iv = holder.getView(R.id.iv);
          iv.setImageResource(item.getTupian());
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
