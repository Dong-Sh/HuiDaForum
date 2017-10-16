package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.PostingBean;
import com.huidaforum.bean.PostingCommentBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

import static com.huidaforum.R.id.tv_posting_content;


public class PostingActivity extends BaseActivity {
    //帖子详情
    @BindView(R.id.iv_posting_pic)
    ImageView ivPostingPic;
    @BindView(R.id.rl_posting_pic)
    RelativeLayout rlPostingPic;
    @BindView(R.id.tv_posting_title)
    TextView tvPostingTitle;
    @BindView(R.id.rl_posting_title)
    RelativeLayout rlPostingTitle;
    @BindView(tv_posting_content)
    TextView tvPostingContent;
    @BindView(R.id.iv_posting_fabulous)
    ImageView ivPostingFabulous;
    @BindView(R.id.iv_posting_collect)
    ImageView ivPostingCollect;
    @BindView(R.id.iv_comment_fabulous)
    ImageView ivCommentFabulous;
    @BindView(R.id.ll_jiaohu)
    LinearLayout llJiaohu;
    @BindView(R.id.ib_post_reward)
    ImageButton ibPostReward;
    @BindView(R.id.ll_post)
    LinearLayout llPost;
    @BindView(R.id.rl_pinglun)
    RelativeLayout rlPinglun;
    @BindView(R.id.ib_posting_share)
    ImageButton ibPostingShare;
    @BindView(R.id.rv_posting)
    RecyclerView rvPosting;

    @BindView(R.id.jps)
    JZVideoPlayerStandard jps;

    private List<PostingCommentBean> postingBeanList;
    private PostingAdapter postingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_posting;
    }

    @Override
    public void initView() {

    }
//帖子详情
    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        OkGo.<String>post(WebAddress.listForCountDetailInfo)
                .tag(this)
                .params("token", SpUtil.getString(StaticValue.TOKEN, PostingActivity.this))
                .params("devType", "phone")
                .params("id", id)
                .execute(new StringCallback() {
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<PostingBean> baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<PostingBean>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            PostingBean postingBean = baseBean.getData();
                            //是否为视频
                            if (postingBean.getContentType().equals("flv")) {

                                jps.setVisibility(View.VISIBLE);
                                ivPostingPic.setVisibility(View.GONE);
                                jps.setUp(postingBean.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "");

                            } else {
                                jps.setVisibility(View.GONE);
                                ivPostingPic.setVisibility(View.VISIBLE);
                                Picasso.with(PostingActivity.this).load(postingBean.getPhotoFlvPath()).fit()
                                        .into(ivPostingPic);

                            }

                            tvPostingTitle.setText(postingBean.getTitle());
                            tvPostingContent.setText(postingBean.getContentText());

                        }
                    }
                });
        OkGo.<String>post(WebAddress.listForContentAnswer)
                .tag(this)
                .params("token", SpUtil.getString(StaticValue.TOKEN, PostingActivity.this))
                .params("devType", "phone")
                .params("id", id)
                .execute(new StringCallback() {
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<PostingCommentBean>> baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<List<PostingCommentBean>>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            postingBeanList = baseBean.getData();
                            rvPosting.setLayoutManager(new LinearLayoutManager(PostingActivity.this));
                            if (postingAdapter == null) {
                                postingAdapter = new PostingAdapter();
                                rvPosting.setAdapter(postingAdapter);
                            }

                        }
                    }
                });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    public class PostingAdapter extends BaseQuickAdapter<PostingCommentBean, BaseViewHolder> {

        public PostingAdapter() {
            super(R.layout.recent_pinglun_item, postingBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, PostingCommentBean item) {//填充item数据
            Picasso.with(PostingActivity.this).load(item.getHeadPhoto()).fit()
                    .into((ImageView) helper.getView(R.id.headphoto));
            helper.setText(R.id.nick_name, item.getNickName())
                    .setText(R.id.pinglun_content, item.getOwnerText());
            List<PostingCommentBean.ChildrenBean> children = item.getChildren();
            LinearLayout llPingLun = helper.getView(R.id.ll_pinglun);
            for (PostingCommentBean.ChildrenBean c : children) {
                TextView textView = new TextView(PostingActivity.this);
                textView.setText(c.getNickName() + ":" + c.getOwnerText());
                llPingLun.addView(textView);
            }

        }

    }
}
