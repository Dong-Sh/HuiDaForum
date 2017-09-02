package com.huidaforum.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.huidaforum.R;
import com.huidaforum.base.BaseFragment;

/**
 * 主页中社区页面
 */

public class CommunityFragment extends BaseFragment {
    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_community, null);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
