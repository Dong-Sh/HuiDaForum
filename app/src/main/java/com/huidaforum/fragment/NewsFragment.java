package com.huidaforum.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.huidaforum.R;
import com.huidaforum.activity.CommentActivity;
import com.huidaforum.activity.ReplyActivity;
import com.huidaforum.activity.AwardActivity;
import com.huidaforum.activity.SystemActivity;
import com.huidaforum.base.BaseFragment;

/**
 * 主页中消息页面
 */

public class NewsFragment extends BaseFragment {

    private RelativeLayout ll_news_huifu;
    private RelativeLayout ll_news_dashang;
    private RelativeLayout ll_news_xitong;
    private RelativeLayout ll_news_pinglun;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_news, null);
        ll_news_huifu = (RelativeLayout) view.findViewById(R.id.ll_news_huifu);
        ll_news_dashang = (RelativeLayout) view.findViewById(R.id.ll_news_dashang);
        ll_news_xitong = (RelativeLayout) view.findViewById(R.id.ll_news_xitong);
        ll_news_pinglun = (RelativeLayout) view.findViewById(R.id.ll_news_pinglun);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        ll_news_huifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, ReplyActivity.class);
                startActivity(intent);
            }
        });
        ll_news_dashang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, AwardActivity.class);
                startActivity(intent);
            }
        });
        ll_news_xitong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, SystemActivity.class);
                startActivity(intent);
            }
        });
        ll_news_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, CommentActivity.class);
                startActivity(intent);
            }
        });
    }
}
