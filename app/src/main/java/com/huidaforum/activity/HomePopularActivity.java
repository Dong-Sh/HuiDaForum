package com.huidaforum.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.huidaforum.R;
import com.huidaforum.bean.InvitationBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 首页中最新帖子的界面
 */

public class HomePopularActivity extends AppCompatActivity {
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.tv_home)
    TextView tvHome;
    private String url;
    private RecyclerView rlv_tie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    public int getLayoutId() {

        return R.layout.activity_popular;
    }

    public void initView() {
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlv_tie = (RecyclerView) findViewById(R.id.rlv_tie);

        TextView tv_home = (TextView) findViewById(R.id.tv_home);
        String title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        tv_home.setText(title);
    }

    public void initData() {
        OkGo.<String>post(url).params("devType", "phone").params("token", "bdc27a90659e40c5b128855c22340b4e").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                InvitationBean bean = new Gson().fromJson(response.body().toString(), InvitationBean.class);
                if (bean.isSuccess()) {
                    List<InvitationBean.DataBean> data = bean.getData();
                    rlv_tie.setLayoutManager(new LinearLayoutManager(HomePopularActivity.this));
                    MyAdapter adapter = new MyAdapter(R.layout.item_tie, data);
                    rlv_tie.setAdapter(adapter);
                    adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                    //每个条目的点击事件
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Toast.makeText(HomePopularActivity.this, "点击了当前"+position+"条目", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //点击子控件的事件
                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                             switch (view.getId()){
                                 case R.id.tv_zan:
                             Toast.makeText(HomePopularActivity.this, "单击第"+position+"个赞", Toast.LENGTH_SHORT).show();
                                     break;
                                 case R.id.tv_shoucang:
                                     Toast.makeText(HomePopularActivity.this, "单击第"+position+"个收藏", Toast.LENGTH_SHORT).show();
                                     break;
                                 case R.id.tv_pinglun:
                                     Toast.makeText(HomePopularActivity.this, "单击第"+position+"个评论", Toast.LENGTH_SHORT).show();
                                     break;
                             }
                        }
                    });
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
                jps.setUp(item.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
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
}
