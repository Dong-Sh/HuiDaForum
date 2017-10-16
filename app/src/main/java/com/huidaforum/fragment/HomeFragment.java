package com.huidaforum.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.huidaforum.base.BaseBean;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.FitStateUI;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.ThreeDrawable;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
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
    private ThreeDrawable threeDrawable;

    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        threeDrawable = ((MyApplication) mActivity.getApplication()).threeDrawable;
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

        OkGo.<String>post(WebAddress.listAllContents)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, mActivity))
                .execute(new StringCallback() {

                    public void onSuccess(Response<String> response) {
                        bean = new Gson().fromJson(response.body().toString(), new TypeToken<BaseBean<List<SchoolContentBean>>>() {

                        }.getType());
                        if (bean.isSuccess()) {
                            pareDataFromNet();
                        }else {
                            Toast.makeText(mActivity, bean.getErrMsg(), Toast.LENGTH_SHORT).show();
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
        MyAdapter adapter = new MyAdapter();
        adapter.addHeaderView(view);
        rlvHome.setAdapter(adapter);
        rlvHome.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mActivity, "点击了当前" + position + "条目", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_zan:
                        Toast.makeText(mActivity, "单击第" + position + "个赞", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_shoucang:
                        Toast.makeText(mActivity, "单击第" + position + "个收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_pinglun:
                        Toast.makeText(mActivity, "单击第" + position + "个评论", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
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

    class MyAdapter extends BaseQuickAdapter<SchoolContentBean, BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.item_tie, bean.getData());
        }

        @Override
        protected void convert(BaseViewHolder holder, SchoolContentBean item) {
            holder.setText(R.id.tv_tie_nicheng, item.getNickName())
                    .setText(R.id.tv_tie_title, item.getTitle())
                    .setText(R.id.tv_tie_data, item.getContentText() + "")
                    .setText(R.id.tv_zan,item.getZanCount()+"")
                    .addOnClickListener(R.id.tv_zan)
                    .addOnClickListener(R.id.tv_shoucang)
                    .addOnClickListener(R.id.tv_pinglun);

            TextView tv_zan = holder.getView(R.id.tv_zan);
            TextView tv_shoucang = holder.getView(R.id.tv_shoucang);
            TextView tv_pinglun = holder.getView(R.id.tv_pinglun);
            setTextDrawableLeft(tv_zan, threeDrawable.getZan_no(), threeDrawable.getZan_yes(), item.getLaud());
            setTextDrawableLeft(tv_pinglun, threeDrawable.getPinglun_no(), threeDrawable.getPinglun_yes(), item.getAnswer());
            setTextDrawableLeft(tv_shoucang, threeDrawable.getShoucang_no(), threeDrawable.getShoucang_yes(), item.getShouchang());
            //是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(mActivity).load(item.getPhotoFlvPath()).into(iv_tie);
            } else {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.GONE);
            }
            //是否为视频
            if (item.getContentType().equals("flv")) {
                JZVideoPlayerStandard jps = holder.getView(R.id.jps);
                jps.setVisibility(View.VISIBLE);
                jps.setUp(item.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "");
            } else {
                JZVideoPlayerStandard jps = holder.getView(R.id.jps);
                jps.setVisibility(View.GONE);
            }
        }
        private void setTextDrawableLeft(TextView textView, Drawable no, Drawable yes, String flag) {
            if (flag.equals("yes"))
                textView.setCompoundDrawables(yes, null, null, null);
            else {
                textView.setCompoundDrawables(no, null, null, null);
            }
        }
    }
}
