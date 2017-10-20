package com.huidaforum.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/10/20.
 * 转发页面
 */

public class ForwardContentActivity extends BaseActivity {
    @BindView(R.id.et_forward_talk)
    EditText etForwardTalk;
    @BindView(R.id.ll_forward_biaoqing)
    LinearLayout llForwardBiaoqing;
    @BindView(R.id.tv_forward_send)
    TextView tvForwardSend;
    @BindView(R.id.iv_forward_pic)
    ImageView ivForwardPic;
    @BindView(R.id.tv_forward_nickname)
    TextView tvForwardNickname;
    @BindView(R.id.tv_forward_time)
    TextView tvForwardTime;
    @BindView(R.id.tv_forward_content)
    TextView tvForwardContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forward_content;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
