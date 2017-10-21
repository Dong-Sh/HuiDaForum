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
import com.huidaforum.adapter.MyAdapter;
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
        MyAdapter adapter = new MyAdapter(R.layout.item_tie,bean.getData());
        rlv_tie.setAdapter(adapter);
    }

    public void initListener() {


    }

    @Override
    public void processClick(View v) {

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
