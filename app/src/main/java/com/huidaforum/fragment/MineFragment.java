package com.huidaforum.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.activity.MineCollectActivity;
import com.huidaforum.activity.MineFocusActivity;
import com.huidaforum.activity.MineSettingActivity;
import com.huidaforum.base.BaseFragment;

import butterknife.BindView;
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
        rlMineFocus.setOnClickListener(this);
        rlMineCollect.setOnClickListener(this);
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
        }
        startActivity(intent);
    }

}
