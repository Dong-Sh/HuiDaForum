package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.bean.BaseBean;
import com.huidaforum.bean.CommentBean;
import com.huidaforum.bean.ReplyBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

import static com.huidaforum.R.id.rv_comment;
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
            helper.setText(R.id.tv_comment_time,item.getCreateTime())
                    .setText(R.id.tv_comment_name,item.getNickName())
                    .setText(R.id.tv_comment_content,item.getOwnerText());
            if (TextUtils.isEmpty(item.getPhotoFlvPath())){
                helper.setText(R.id.tv_comment_title,item.getTitle());
            }else {

            }

        }
    }
}
