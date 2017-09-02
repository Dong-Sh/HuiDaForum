package com.huidaforum.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huidaforum.R;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.Bean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页中首页页面
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    private ArrayList<Bean> been;


    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        been = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean("我是测试文本" + i);
            been.add(bean);
        }

        rlvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        MyAdapter adapter = new MyAdapter();
        View view = View.inflate(mActivity, R.layout.top_home, null);
        adapter.addHeaderView(view);
        rlvHome.setAdapter(adapter);
    }
   class MyAdapter extends BaseQuickAdapter<Bean,BaseViewHolder>{

       public MyAdapter() {
           super(R.layout.item_home_rlv, been);
       }

       @Override
       protected void convert(BaseViewHolder helper, Bean item) {
           helper.setText(R.id.tv,item.getName());
       }
   }
    @Override
    protected void initListener() {

    }

}
