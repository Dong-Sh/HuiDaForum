package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huidaforum.R;
import com.huidaforum.activity.HomePopularActivity;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.Bean;
import com.huidaforum.utils.StatusBarUtil;
import com.huidaforum.utils.WebAddress;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主页中首页页面
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    private ArrayList<Bean> been;
    private Button bt_popular;
    private Button bt_infomation;
    private Button bt_selection;


    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        View view = View.inflate(mActivity, R.layout.top_home, null);
        bt_popular = (Button) view.findViewById(R.id.bt_popular);
        bt_infomation = (Button) view.findViewById(R.id.bt_infomation);
        bt_selection = (Button) view.findViewById(R.id.bt_selection);

        been = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean("我是测试文本" + i);
            been.add(bean);
        }



        rlvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        MyAdapter adapter = new MyAdapter();
        adapter.addHeaderView(view);
        rlvHome.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.bt_popular:
                   Intent intent = new Intent(mActivity, HomePopularActivity.class);
                   intent.putExtra("title","最新帖子");
                   intent.putExtra("url", WebAddress.selectByCountTime);
                   startActivity(intent);
                   break;
               case R.id.bt_infomation:
                   Intent intent1 = new Intent(mActivity, HomePopularActivity.class);
                   intent1.putExtra("title","热门好帖");
                   intent1.putExtra("url",WebAddress.seleteByContentHot);
                   startActivity(intent1);
                   break;
               case R.id.bt_selection:
                   Intent intent2 = new Intent(mActivity, HomePopularActivity.class);
                   intent2.putExtra("title","精品好帖");
                   intent2.putExtra("url",WebAddress.seleteByContentJingpin);
                   startActivity(intent2);
                   break;
           }
    }

    @Override
    protected void initListener() {
        bt_popular.setOnClickListener(this);
        bt_infomation.setOnClickListener(this);
        bt_selection.setOnClickListener(this);
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
}
