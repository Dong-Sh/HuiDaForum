package com.huidaforum.activity;

import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineDraftBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.huidaforum.view.SwipeItemLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaojiu on 2017/9/3.
 * 我的草稿页面
 */

public class MineDraftActivity extends BaseActivity {
    @BindView(R.id.tv_draft_edit)
    TextView tvDraftEdit;
    @BindView(R.id.ll_draft_empty)
    LinearLayout llDraftEmpty;
    @BindView(R.id.rv_mine_draft)
    RecyclerView rvMineDraft    ;
    @BindView(R.id.sw_draft_content)
    SwipeRefreshLayout swDraftContent;
    @BindView(R.id.fr_draft)
    FrameLayout frDraft;
    private boolean editFlag = false;
    private DraptAdapter draptAdapter;
    private BaseBean<List<MineDraftBean>> mineDraftBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_draft;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        getData();
    }
    //获取数据
    private void getData() {
        OkGo.<String>post(WebAddress.seleteByContentState)
                .params("devType","phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN,this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        mineDraftBean = gson.fromJson(s,
                                new TypeToken<BaseBean<List<MineDraftBean>>>(){}.getType());
                        if(mineDraftBean.isSuccess()){
                            parseData(mineDraftBean.getData());
                        }
                    }
                });
    }
    //解析数据
    private void parseData(List<MineDraftBean> data) {
        //如果没有数据 就显示暂无草稿
        if(data.size()==0){
            llDraftEmpty.setVisibility(View.VISIBLE);
            frDraft.setVisibility(View.INVISIBLE);
            return;
        }
        llDraftEmpty.setVisibility(View.INVISIBLE);
        frDraft.setVisibility(View.VISIBLE);
        rvMineDraft.setLayoutManager(new LinearLayoutManager(this));
        rvMineDraft.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));
        draptAdapter = new DraptAdapter(R.layout.item_draft, data);
        rvMineDraft.setAdapter(draptAdapter);
        rvMineDraft.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //下拉刷新
        swDraftContent.setColorSchemeColors(Color.RED);

        swDraftContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swDraftContent.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swDraftContent.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        draptAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               switch (view.getId()){
                   case R.id.iv_item_draft_dele:
                   case R.id.tv_item_draft_dele:
                       //删除
                       deleData(mineDraftBean.getData().get(position));
                       mineDraftBean.getData().remove(position);
                       if(mineDraftBean.getData().size()==0){
                           llDraftEmpty.setVisibility(View.VISIBLE);
                           frDraft.setVisibility(View.INVISIBLE);
                           return;
                       }
                       adapter.notifyDataSetChanged();
                       break;
                   case R.id.tv_item_draft_resend:
                       break;
               }
            }
        });
    }

    @Override
    public void initListener() {
        tvDraftEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFlag = !editFlag;
                tvDraftEdit.setText(editFlag?"完成":"编辑");
                if(draptAdapter!=null && mineDraftBean.getData().size()!=0)
                    draptAdapter.notifyDataSetChanged();
            }
        });
    }
    //删除条目的方法
    private void deleData(MineDraftBean mineDraftBean) {
        OkGo.<String>post(WebAddress.deleteContentByUser)
                .params("devType","phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN,this))
                .params("id",mineDraftBean.getId())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        BaseBean baseBean = gson.fromJson(s,
                                new TypeToken<BaseBean<Object>>() {
                                }.getType());
                        if(baseBean.isSuccess()){
                            Toast.makeText(MineDraftActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MineDraftActivity.this, "删除失败，请刷新后重试", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Toast.makeText(MineDraftActivity.this, "网络出现问题，请刷新重试", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
        }
    }

    class DraptAdapter extends BaseQuickAdapter<MineDraftBean, BaseViewHolder> {

        public DraptAdapter(@LayoutRes int layoutResId, @Nullable List<MineDraftBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MineDraftBean item) {
            if(editFlag){
                helper.getView(R.id.iv_item_draft_dele).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.iv_item_draft_dele);
            }else{
                helper.getView(R.id.iv_item_draft_dele).setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_item_draft_time,item.getCreateTime());
            helper.setText(R.id.tv_item_draft_content,item.getContentTextShort());
            if(TextUtils.isEmpty(item.getVideoPictureThumbnail())){
                helper.getView(R.id.iv_item_draft_pic).setVisibility(View.GONE);
            }else{
                helper.getView(R.id.iv_item_draft_pic).setVisibility(View.VISIBLE);
                Picasso.with(MineDraftActivity.this).load(item.getVideoPictureThumbnail())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .fit()
                        .into((ImageView) helper.getView(R.id.iv_item_draft_pic));
                helper.getView(R.id.tv_item_draft_resend).setBackgroundResource(R.drawable.resend_shape);
                helper.addOnClickListener(R.id.tv_item_draft_resend) ;
                helper.addOnClickListener(R.id.tv_item_draft_dele);
            }
        }
    }
}
