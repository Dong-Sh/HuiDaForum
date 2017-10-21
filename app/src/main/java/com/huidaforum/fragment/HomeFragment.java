package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.MyApplication;
import com.huidaforum.R;
import com.huidaforum.activity.HomePopularActivity;
import com.huidaforum.activity.PostingActivity;
import com.huidaforum.adapter.MyAdapter;
import com.huidaforum.base.BaseBean;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.huidaforum.utils.ThreeDrawable;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 主页中首页页面
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    @BindView(R.id.srl_home)
    SmartRefreshLayout srlHome;
    @BindView(R.id.fab_home)
    FloatingActionButton fabHome;
    Unbinder unbinder;
    private Button bt_popular;
    private Button bt_infomation;
    private Button bt_selection;
    private View view;
    private BaseBean<List<SchoolContentBean>> bean;
    private MyAdapter adapter;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        view = View.inflate(mActivity, R.layout.top_home, null);

        ImageButton ib_home = (ImageButton) view.findViewById(R.id.ib_home);
        Button bt_home = (Button) view.findViewById(R.id.bt_home);
        bt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "跳转到搜索页面", Toast.LENGTH_SHORT).show();
            }
        });
        ib_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mActivity, "跳转到搜索页面", Toast.LENGTH_SHORT).show();
            }
        });
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlvHome.smoothScrollToPosition(0);
            }
        });
        bt_popular = (Button) view.findViewById(R.id.bt_popular);
        bt_infomation = (Button) view.findViewById(R.id.bt_infomation);
        bt_selection = (Button) view.findViewById(R.id.bt_selection);

        initNetData(false);
        if (rlvHome.getAdapter() != null) {
            adapter = (MyAdapter) rlvHome.getAdapter();
        } else {
            adapter = new MyAdapter(R.layout.item_tie,null);
            adapter.addHeaderView(view);
        }
        rlvHome.setAdapter(adapter);
        rlvHome.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private void initNetData(final boolean flag) {
        OkGo.<String>post(WebAddress.listAllContents)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, mActivity))
                .execute(new StringCallback() {

                    public void onSuccess(Response<String> response) {
                        bean = new Gson().fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<List<SchoolContentBean>>>() {

                        }.getType());
                        if (bean.isSuccess()) {
                            pareDataFromNet();
                        } else {
                            Toast.makeText(mActivity, bean.getErrMsg(), Toast.LENGTH_SHORT).show();
                        }

                        if (flag) {
                            srlHome.finishRefresh();
                        }

                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                        pareDataFromNet();
                    }

                });
    }

    public void pareDataFromNet() {
        adapter.setNewData(bean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_popular:
                Intent intent = new Intent(mActivity, HomePopularActivity.class);
                intent.putExtra("title", "最新帖子");
                intent.putExtra("url", WebAddress.selectByCountTime);
                startActivity(intent);
                break;
            case R.id.bt_infomation:
                Intent intent1 = new Intent(mActivity, HomePopularActivity.class);
                intent1.putExtra("title", "热门好帖");
                intent1.putExtra("url", WebAddress.seleteByContentHot);
                startActivity(intent1);
                break;
            case R.id.bt_selection:
                Intent intent2 = new Intent(mActivity, HomePopularActivity.class);
                intent2.putExtra("title", "精品好帖");
                intent2.putExtra("url", WebAddress.seleteByContentJingpin);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void initListener() {
        bt_popular.setOnClickListener(this);
        bt_infomation.setOnClickListener(this);
        bt_selection.setOnClickListener(this);
        srlHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initNetData(true);
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
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
