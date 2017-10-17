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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.MyApplication;
import com.huidaforum.R;
import com.huidaforum.base.BaseBackActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.InvitationBean;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.MethodUtil;
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
    private ThreeDrawable threeDrawable;
    private BaseBean<List<SchoolContentBean>> bean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_popular;
    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab_popular = (FloatingActionButton) findViewById(R.id.fab_popular);
        popular_srl = (SmartRefreshLayout) findViewById(R.id.popular_srl);
        rlv_tie = (RecyclerView) findViewById(R.id.rlv_tie);
        threeDrawable = ((MyApplication) HomePopularActivity.this.getApplication()).threeDrawable;
        String title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
//          actionBar.setHomeAsUpIndicator(R.mipmap.left);
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
        OkGo.<String>get(url).params("devType", "phone").params("token", SpUtil.getString(StaticValue.TOKEN, HomePopularActivity.this)).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                bean = new Gson().fromJson(response.body().toString(), new TypeToken<BaseBean<List<SchoolContentBean>>>() {
                }.getType());
                if (bean.isSuccess()) {
                    pareDataFormNet();
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                bean = new Gson().fromJson(response.body().toString(), new TypeToken<BaseBean<List<SchoolContentBean>>>() {
                }.getType());
                if (bean.isSuccess()) {
                    pareDataFormNet();
                }
            }
        });
    }

    public void pareDataFormNet() {

        rlv_tie.setLayoutManager(new LinearLayoutManager(HomePopularActivity.this));
        MyAdapter adapter = new MyAdapter();
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
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                SchoolContentBean schoolContentBean = bean.getData().get(position);
                MethodUtil.zanAndshoucang(HomePopularActivity.this,(TextView)view,schoolContentBean);
            }
        });
    }

    public void initListener() {


    }

    @Override
    public void processClick(View v) {

    }


    class MyAdapter extends BaseQuickAdapter<SchoolContentBean, BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.item_tie, bean.getData());
        }

        @Override
        protected void convert(BaseViewHolder holder, SchoolContentBean item) {
            holder.setText(R.id.tv_tie_nicheng, item.getNickName() + "")
                    .setText(R.id.tv_tie_title, item.getTitle())
                    .setText(R.id.tv_tie_data, item.getContentTextShort() + "")
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

        private void setTextDrawableLeft(TextView textView, Drawable no, Drawable yes, String flag) {
            if (flag.equals("yes"))
                textView.setCompoundDrawables(yes, null, null, null);
            else {
                textView.setCompoundDrawables(no, null, null, null);
            }
        }
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
