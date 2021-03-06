package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.CommentBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
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

import static com.huidaforum.R.id.rv_comment;
import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;
import static java.lang.Boolean.TRUE;

//评论我的
public class CommentActivity extends BaseActivity {

    @BindView(rv_comment)
    RecyclerView rv_Comment;
    @BindView(R.id.sr_comment)
    SmartRefreshLayout sr_Comment;
    private List<CommentBean> commentBeanList;
    private CommentAdapter commentdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //下拉刷新头样式
        sr_Comment.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        getOkGo();
    }

    @Override
    public void initListener() {
        //下拉刷新监听
        sr_Comment.setOnRefreshListener(new OnRefreshListener() {
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

    public void getOkGo() {//从服务区获取谁评论我的
        OkGo.<String>post(WebAddress.commentyMe)
                .tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<CommentBean>> baseBean = gson.fromJson(response.body()
                                , new TypeToken<BaseBean<List<CommentBean>>>() {
                                }.getType());
                        if (baseBean.isSuccess()) {
                            commentBeanList = baseBean.getData();
                            rv_Comment.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
                            if (commentdAdapter == null) {
                                commentdAdapter = new CommentAdapter();
                                //item点击事件
                                commentdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("id",commentBeanList.get(position).getOwnerContentId());
                                        startActivity(PostingActivity.class,bundle);
                                    }
                                });
                                rv_Comment.setAdapter(commentdAdapter);
                                return;
                            }
                            commentdAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    //评论我的Adapter
    public class CommentAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {

        public CommentAdapter() {
            super(R.layout.comment_item, commentBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, CommentBean item) {//填充item数据

           // Picasso.with(CommentActivity.this).load(item.getHeadPhoto()).fit().memoryPolicy(NO_CACHE, NO_STORE)
                  //  .placeholder(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.iv_comment_pic));

            Picasso.with(CommentActivity.this).load(item.getHeadPhoto()).fit()
                    .into((ImageView) helper.getView(R.id.iv_comment_pic));
            helper.setText(R.id.tv_comment_time,item.getCreateTime())
                    .setText(R.id.tv_comment_name,item.getNickName())
                    .setText(R.id.tv_comment_content,item.getOwnerText());
            if (TextUtils.isEmpty(item.getPhotoFlvPath())){
                helper.setVisible(R.id.tv_comment_title,true);
                helper.setVisible(R.id.photoFlvPath,false);
                helper.setText(R.id.tv_comment_title,item.getTitle());
            }else {
                helper.setVisible(R.id.tv_comment_title,false);
                helper.setVisible(R.id.photoFlvPath,true);
                Picasso.with(CommentActivity.this)
                        .load(item.getHeadPhoto())
                        .fit()
                        .placeholder(R.mipmap.ic_launcher)
                        .transform(new CircleTransform())
                        .into((ImageView) helper.getView(R.id.photoFlvPath));
            }

        }
    }
}
