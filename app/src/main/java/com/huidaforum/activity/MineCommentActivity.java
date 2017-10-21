package com.huidaforum.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.MyApplication;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineCommentBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.ThreeDrawable;
import com.huidaforum.utils.WebAddress;
import com.huidaforum.view.CircleTransform;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.ll_comment_empty)
    LinearLayout llCommentEmpty;
    private ThreeDrawable threeDrawable;
    private MineCommentActivity.mineCommentAdapter mineCommentAdapter;
    private List<MineCommentBean> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_comment;
    }

    @Override
    public void initView() {
        threeDrawable = ((MyApplication) MineCommentActivity.this.getApplication()).threeDrawable;

    }

    @Override
    public void initData() {
        OkGo.<String>post(WebAddress.answerPage)
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
                            data = mineCommentBean.getData();
                            parseData(data);
                        }
                    }
                });
    }

    private void parseData(final List<MineCommentBean> data) {
        if (data.size() != 0) {
            rvMineComment.setVisibility(View.VISIBLE);
            llCommentEmpty.setVisibility(View.INVISIBLE);
            rvMineComment.setLayoutManager(new LinearLayoutManager(this));
            mineCommentAdapter = new mineCommentAdapter(R.layout.mine_comment_item, data);
            rvMineComment.setAdapter(mineCommentAdapter);
            mineCommentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    MineCommentBean bean = data.get(position);
                    switch (view.getId()){
                        case R.id.tv_zan:
                        case R.id.tv_shoucang:
                            MethodUtil.dzandsc(MineCommentActivity.this,(TextView)view,bean);
                            break;
                        case R.id.rl_comment_zhuanfa:
                            Intent intent = new Intent(MineCommentActivity.this, ForwardContentActivity.class);
                            intent.putExtra("id",bean.getId());
                            startActivity(intent);
                            break;
                    }

                }
            });
        } else {
            rvMineComment.setVisibility(View.INVISIBLE);
            llCommentEmpty.setVisibility(View.VISIBLE);
        }
    }

    //获取数据
    public class mineCommentAdapter extends BaseQuickAdapter<MineCommentBean, BaseViewHolder> {
        public mineCommentAdapter(@LayoutRes int layoutResId, List<MineCommentBean> data) {
            super(layoutResId, data);
        }

        private void setTextDrawableLeft(TextView textView, Drawable no, Drawable yes, String flag) {
            if (flag.equals("yes"))
                textView.setCompoundDrawables(yes, null, null, null);
            else {
                textView.setCompoundDrawables(no, null, null, null);
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, final MineCommentBean item) {
            helper.setText(R.id.tv_mine_name, item.getNickName().toString());
            helper.setText(R.id.tv_comment_time, item.getCreateTime());
            helper.setText(R.id.tv_comment_ownertext, item.getOwnerText());
            helper.setText(R.id.tv_comment_title, item.getTitle());
            helper.addOnClickListener(R.id.tv_zan);
            helper.addOnClickListener(R.id.tv_shoucang);
            helper.addOnClickListener(R.id.rl_comment_zhuanfa);
            TextView tv_dz = helper.getView(R.id.tv_zan);
            TextView tv_shoucang = helper.getView(R.id.tv_shoucang);
            if(!TextUtils.isEmpty(item.getHeadPhoto())){
                Picasso.with(mContext).load(item.getHeadPhoto()).fit().
                        transform(new CircleTransform()).error(R.mipmap.ic_launcher).
                        placeholder(R.mipmap.ic_launcher).
                        into((ImageView) helper.getView(R.id.iv_mine_pic));
            }else{
                Picasso.with(mContext).load(R.mipmap.ic_launcher).fit().transform(new CircleTransform()).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.iv_mine_pic));
            }
            setTextDrawableLeft(tv_dz, threeDrawable.getZan_no(), threeDrawable.getZan_yes(), item.getLaud());
            setTextDrawableLeft(tv_shoucang, threeDrawable.getShoucang_no(), threeDrawable.getShoucang_yes(), item.getShouchang());
            /*if(TextUtils.isEmpty(item.photoFlvPath)||TextUtils.equals(null,item.photoFlvPath)){
                helper.getView(R.id.iv_collect_pic).setVisibility(View.INVISIBLE);
            }else{
                Picasso.with(mContext).load(item.photoFlvPath).fit().into((ImageView) helper.getView(R.id.iv_collect_pic));
            }*/
            /*//是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = helper.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(MineCommentActivity.this).load(item.getPhotoFlvPath()).into(iv_tie);
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
