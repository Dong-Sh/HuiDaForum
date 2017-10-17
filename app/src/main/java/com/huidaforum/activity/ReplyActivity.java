package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.ReplyBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.huidaforum.utils.WebAddress;
import com.huidaforum.view.CircleTransform;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

import static com.huidaforum.R.id.headphoto;
import static com.huidaforum.R.id.iv_tie;
import static com.huidaforum.R.id.rv_reply;
//回复我的
public class ReplyActivity extends BaseActivity {

    @BindView(rv_reply)
    RecyclerView rv_Reply;
    @BindView(R.id.sr_reply)
    SmartRefreshLayout sr_Reply;
    private List<ReplyBean> replyBeanList;
    private ReplyAdapter replydAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reply;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //下拉刷新头样式
        sr_Reply.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        getOkGo();
    }

    @Override
    public void initListener() {


        //下拉刷新监听
        sr_Reply.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
                getOkGo();
            }
        });
        //上拉加载
//        sr_Award.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore(2000);
//            }
//        });
    }

    @Override
    public void processClick(View v) {

    }

    public void getOkGo() {//从服务区获取谁回复我的
        OkGo.<String>post(WebAddress.replyMe)
                .tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<ReplyBean>> baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body())
                                , new TypeToken<BaseBean<List<ReplyBean>>>() {
                                }.getType());
                        if (baseBean.isSuccess()) {
                            replyBeanList = baseBean.getData();
                            rv_Reply.setLayoutManager(new LinearLayoutManager(ReplyActivity.this));
                            if (replydAdapter == null) {
                                replydAdapter = new ReplyAdapter();

                                //item点击事件
                                replydAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id",replyBeanList.get(position).getOwnerContentId());
                                        startActivity(PostingActivity.class,bundle);
                                    }
                                });

                                rv_Reply.setAdapter(replydAdapter);
                                return;
                            }
                            replydAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //谁回复的我Adapter
    public class ReplyAdapter extends BaseQuickAdapter<ReplyBean, BaseViewHolder> {

        public ReplyAdapter() {
            super(R.layout.reply_item, replyBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, ReplyBean item) {//填充item数据
            //图片框数据 ， 暂时无头像

            Picasso.with(ReplyActivity.this).load(item.getHeadPhoto()).fit().transform(new CircleTransform()).placeholder(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.iv_reply_pic));
            helper.setText(R.id.tv_reply_name,item.getNickName())
                    .setText(R.id.tv_reply_time,item.getCreateTime())
                    .setText(R.id.tv_reply_content,item.getOwnerText())
                    .setText(R.id.tv_reply_posting,item.getTitle());


        }

    }
}
