package com.huidaforum.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MinePublishBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
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
 * Created by xiaojiu on 2017/9/3.
 * 我的发表页面
 */

public class MinePublishActivity extends BaseActivity {
    @BindView(R.id.tv_publish_edit)
    TextView tvPublishEdit;
    @BindView(R.id.ll_publish_empty)
    LinearLayout llPublishEmpty;
    @BindView(R.id.rv_mine_publish)
    RecyclerView rvMinePublish;
    private boolean editFlag = false;
    private MinePublishAdapter minePublishAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_publish;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        OkGo.<String>post(WebAddress.getContentsById)
                .params("token", SpUtil.getString(StaticValue.TOKEN,MinePublishActivity.this))
                .params("devType","phone")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        BaseBean<List<MinePublishBean>> minePublishBean = gson.fromJson(body,
                                new TypeToken<BaseBean<List<MinePublishBean>>>() {
                                }.getType());
                        if (minePublishBean.isSuccess()) {
                            List<MinePublishBean> data = minePublishBean.getData();
                            parseData(data);

                        }
                    }
                });

    }

    private void parseData(List<MinePublishBean> data) {
        if (data.size() != 0) {
            rvMinePublish.setVisibility(View.VISIBLE);
            llPublishEmpty.setVisibility(View.INVISIBLE);
            Log.e("jdr",data.size()+"");
            rvMinePublish.setLayoutManager(new LinearLayoutManager(this));
            minePublishAdapter = new MinePublishAdapter(R.layout.mine_publish_item, data);
            rvMinePublish.setAdapter(minePublishAdapter);
        } else {
            rvMinePublish.setVisibility(View.INVISIBLE);
            llPublishEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //获取数据
    public class MinePublishAdapter extends BaseQuickAdapter<MinePublishBean, BaseViewHolder> {
        public MinePublishAdapter(@LayoutRes int layoutResId, List<MinePublishBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MinePublishBean item) {
            if (editFlag){
                helper.getView(R.id.publish_item_delete).setVisibility(View.VISIBLE);
                helper.getView(R.id.publish_item_delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                    }
                });
            }else{
                helper.getView(R.id.publish_item_delete).setVisibility(View.INVISIBLE);
            }

            helper.setText(R.id.tv_publish_item_title, item.getTitle().toString());
            helper.setText(R.id.tv_publish_item_time, item.getCreateTime());
            helper.setText(R.id.tv_publish_item_hot, item.getLookCount()+"阅读");

            //是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = helper.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(MinePublishActivity.this).load(item.getPhotoFlvPath()).into(iv_tie);
            } else {
                ImageView iv_tie = helper.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.GONE);
            }
            //是否为视频
            if (item.getContentType().equals("flv")) {
                JZVideoPlayerStandard jps = helper.getView(R.id.jps);
                jps.setVisibility(View.VISIBLE);
                jps.setUp(item.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "");
            } else {
                JZVideoPlayerStandard jps = helper.getView(R.id.jps);
                jps.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void initListener() {
        tvPublishEdit.setOnClickListener(this);

    }

    @Override
    public void processClick(View v) {
        if (v == tvPublishEdit){
            editFlag = !editFlag;
            if (minePublishAdapter!=null)
            minePublishAdapter.notifyDataSetChanged();
        }
    }
}
