package com.huidaforum.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineCommentBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/9/3.
 * 我的评论页面
 */

public class MineCommentActivity extends BaseActivity {
    @BindView(R.id.rv_mine_comment)
    RecyclerView rvMineComment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_comment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        OkGo.<String>post(WebAddress.commentyMe)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        BaseBean<List<MineCommentBean>> mineCommentBean = gson.fromJson(s,
                                new TypeToken<BaseBean<List<MineCommentBean>>>() {
                                }.getType());
                        if (mineCommentBean.isSuccess()) {
                            List<MineCommentBean> data = mineCommentBean.getData();
                            parseData(data);
                        }
                    }
                });
    }

    private void parseData(List<MineCommentBean> data) {
        rvMineComment.setLayoutManager(new LinearLayoutManager(this));
        mineCommentAdapter mineCommentAdapter = new mineCommentAdapter(R.layout.mine_comment_item,data);
        rvMineComment.setAdapter(mineCommentAdapter);
    }
    //获取数据
    public class mineCommentAdapter extends BaseQuickAdapter<MineCommentBean,BaseViewHolder> {
        public mineCommentAdapter(@LayoutRes int layoutResId, List<MineCommentBean> data) {
            super(layoutResId,data);
        }
        @Override
        protected void convert(BaseViewHolder helper, MineCommentBean item) {
            helper.setText(R.id.tv_comment_name,item.getNickName());
            helper.setText(R.id.tv_comment_time,item.getCreateTime());
            helper.setText(R.id.tv_comment_ownertext,item.getOwnerText());
            /*if(TextUtils.isEmpty(item.photoFlvPath)||TextUtils.equals(null,item.photoFlvPath)){
                helper.getView(R.id.iv_collect_pic).setVisibility(View.INVISIBLE);
            }else{
                Picasso.with(mContext).load(item.photoFlvPath).fit().into((ImageView) helper.getView(R.id.iv_collect_pic));
            }*/
        }
    }
    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
