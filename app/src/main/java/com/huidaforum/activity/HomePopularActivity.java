package com.huidaforum.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.huidaforum.R;
import com.huidaforum.base.BaseBackActivity;
import com.huidaforum.bean.InvitationBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 首页中最新帖子的界面
 */

public class HomePopularActivity extends BaseBackActivity {
    private String url;
    private RecyclerView rlv_tie;
    private Toolbar toolbar;
    private FloatingActionButton fab_popular;
    private SmartRefreshLayout popular_srl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab_popular = (FloatingActionButton) findViewById(R.id.fab_popular);
        popular_srl = (SmartRefreshLayout) findViewById(R.id.popular_srl);
        rlv_tie = (RecyclerView) findViewById(R.id.rlv_tie);
        initView();
        initData();
    }
    public void initView() {
        String title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
//          actionBar.setHomeAsUpIndicator(R.drawable.home);
        }
        fab_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePopularActivity.this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
                rlv_tie.smoothScrollToPosition(0);
            }
        });


    }

    public void initData() {
        OkGo.<String>post(url).params("devType", "phone").params("token", SpUtil.getString(StaticValue.TOKEN, HomePopularActivity.this)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                InvitationBean bean = new Gson().fromJson(response.body().toString(), InvitationBean.class);
                if (bean.isSuccess()) {
                    pareDataFormNet(bean);
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                InvitationBean bean = new Gson().fromJson(response.body().toString(), InvitationBean.class);
                if (bean.isSuccess()) {
                    pareDataFormNet(bean);
                }
            }
        });
    }

    public void pareDataFormNet(InvitationBean bean) {

        List<InvitationBean.DataBean> data = bean.getData();
        rlv_tie.setLayoutManager(new LinearLayoutManager(HomePopularActivity.this));
        MyAdapter adapter = new MyAdapter(R.layout.item_tie, data);
        rlv_tie.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //每个条目的点击事件
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HomePopularActivity.this, "点击了当前" + position + "条目", Toast.LENGTH_SHORT).show();
            }
        });
        //点击子控件的事件
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_zan:
                        Toast.makeText(HomePopularActivity.this, "单击第" + position + "个赞", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_shoucang:
                        Toast.makeText(HomePopularActivity.this, "单击第" + position + "个收藏", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_pinglun:
                        Toast.makeText(HomePopularActivity.this, "单击第" + position + "个评论", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void initListener() {


    }


    class MyAdapter extends BaseQuickAdapter<InvitationBean.DataBean, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<InvitationBean.DataBean> data) {
            super(R.layout.item_tie, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, InvitationBean.DataBean item) {
            holder.setText(R.id.tv_tie_nicheng, item.getNickName() + "")
                    .setText(R.id.tv_tie_title, item.getTitle())
                    .setText(R.id.tv_tie_data, item.getContentText() + "")
                    .addOnClickListener(R.id.tv_zan)
                    .addOnClickListener(R.id.tv_shoucang)
                    .addOnClickListener(R.id.tv_pinglun);
            initchildView(holder);
            //是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(HomePopularActivity.this).load(item.getPhotoFlvPath()).into(iv_tie);
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
    }

    private void initchildView(BaseViewHolder holder) {
        TextView tv_zan = holder.getView(R.id.tv_zan);
        TextView tv_shoucang = holder.getView(R.id.tv_shoucang);
        TextView tv_pinglun = holder.getView(R.id.tv_pinglun);
        Drawable drawable = getResources().getDrawable(R.drawable.fabulous_nor);
        drawable.setBounds(0, 0, 60, 60);
        tv_zan.setCompoundDrawables(drawable, null, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.collection_nor);
        drawable1.setBounds(0, 0, 60, 60);
        tv_shoucang.setCompoundDrawables(drawable1, null, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.comment_nor);
        drawable2.setBounds(0, 0, 60, 60);
        tv_pinglun.setCompoundDrawables(drawable2, null, null, null);
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
