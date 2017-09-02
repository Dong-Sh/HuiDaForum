package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.activity.MineSettingActivity;
import com.huidaforum.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页中我的页面
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.ib_mine_setting)
    ImageButton ibMineSetting;
    @BindView(R.id.iv_mine_pic)
    ImageView ivMinePic;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.rl_mine_top)
    RelativeLayout rlMineTop;
    @BindView(R.id.iv_mine_focus)
    ImageView ivMineFocus;
    @BindView(R.id.iv_mine_collect)
    ImageView ivMineCollect;
    @BindView(R.id.iv_mine_comment)
    ImageView ivMineComment;
    @BindView(R.id.iv_mine_publish)
    ImageView ivMinePublish;
    @BindView(R.id.iv_mine_money)
    ImageView ivMineMoney;
    @BindView(R.id.iv_mine_draft)
    ImageView ivMineDraft;
    @BindView(R.id.iv_mine_share)
    ImageView ivMineShare;
    Unbinder unbinder;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ibMineSetting.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.ib_mine_setting:
                intent = new Intent(mActivity, MineSettingActivity.class);
                break;

        }
        startActivity(intent);
    }
}
