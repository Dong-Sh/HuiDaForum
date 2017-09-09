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
import com.huidaforum.activity.MineCollectActivity;
import com.huidaforum.activity.MineCommentActivity;
import com.huidaforum.activity.MineFocusActivity;
import com.huidaforum.activity.MineMoneyActivity;
import com.huidaforum.activity.MinePersonalActivity;
import com.huidaforum.activity.MinePublishActivity;
import com.huidaforum.activity.MineSettingActivity;
import com.huidaforum.activity.MineShareActivity;
import com.huidaforum.activity.RegisterActivity;
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
    @BindView(R.id.rl_mine_focus)
    RelativeLayout rlMineFocus;
    @BindView(R.id.rl_mine_collect)
    RelativeLayout rlMineCollect;
    @BindView(R.id.rl_mine_comment)
    RelativeLayout rlMineComment;
    @BindView(R.id.rl_mine_publish)
    RelativeLayout rlMinePublish;
    @BindView(R.id.rl_mine_money)
    RelativeLayout rlMineMoney;
    @BindView(R.id.rl_mine_draft)
    RelativeLayout rlMineDraft;
    @BindView(R.id.rl_mine_share)
    RelativeLayout rlMineShare;
    Unbinder unbinder1;
    Unbinder unbinder2;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    protected void initData() {

    }

    //"我的"页面中控件单击事件
    @Override
    protected void initListener() {
        ibMineSetting.setOnClickListener(this);
        rlMineFocus.setOnClickListener(this);
        rlMineCollect.setOnClickListener(this);
        rlMineComment.setOnClickListener(this);
        rlMinePublish.setOnClickListener(this);
        rlMineMoney.setOnClickListener(this);
        rlMineDraft.setOnClickListener(this);
        rlMineTop.setOnClickListener(this);
        rlMineShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.ib_mine_setting:
                intent = new Intent(mActivity, MineSettingActivity.class);
                break;
            case R.id.rl_mine_focus:
                intent = new Intent(mActivity, MineFocusActivity.class);
                break;
            case R.id.rl_mine_collect:
                intent = new Intent(mActivity, MineCollectActivity.class);
                break;
            case R.id.rl_mine_comment:
                intent = new Intent(mActivity, MineCommentActivity.class);
                break;
            case R.id.rl_mine_publish:
                intent = new Intent(mActivity, MinePublishActivity.class);
                break;
            case R.id.rl_mine_money:
                intent = new Intent(mActivity, MineMoneyActivity.class);
                break;
            case R.id.rl_mine_draft:
                intent = new Intent(mActivity, RegisterActivity.class);
                break;
            case R.id.rl_mine_top:
                intent = new Intent(mActivity, MinePersonalActivity.class);
                break;
            case R.id.rl_mine_share:
                intent = new Intent(mActivity, MineShareActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder2 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder2.unbind();
    }
}
