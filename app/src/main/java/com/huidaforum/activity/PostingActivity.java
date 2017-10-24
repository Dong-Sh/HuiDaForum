package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
import com.huidaforum.bean.PostingCommentBean;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;


public class PostingActivity extends BaseActivity {
    @BindView(R.id.ib_posting_share)
    ImageButton ibPostingShare;
    @BindView(R.id.ll_posting_iorv)
    LinearLayout llPostingIorv;
    @BindView(R.id.tv_posting_title)
    TextView tvPostingTitle;
    @BindView(R.id.rl_posting_title)
    RelativeLayout rlPostingTitle;
    @BindView(R.id.tv_posting_content)
    TextView tvPostingContent;
    @BindView(R.id.tv_zan)
    TextView tvZan;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.tv_pinglun)
    TextView tvPinglun;
    @BindView(R.id.ll_jiaohu)
    LinearLayout llJiaohu;
    @BindView(R.id.ib_post_reward)
    ImageButton ibPostReward;
    @BindView(R.id.ll_post)
    LinearLayout llPost;
    @BindView(R.id.rl_pinglun)
    RelativeLayout rlPinglun;
    @BindView(R.id.rv_posting)
    RecyclerView rvPosting;

    //帖子详情


    private List<PostingCommentBean> postingBeanList;
    private PostingAdapter postingAdapter;
    private BaseBean<SchoolContentBean> baseBean;


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
                .params("id", id)
                .execute(new StringCallback() {
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<SchoolContentBean>>() {
                        }.getType());
                        if (baseBean.isSuccess() && baseBean.getData() != null) {
                            SchoolContentBean postingBean = baseBean.getData();
                            if (postingBean.getContentPics() != null && postingBean.getContentPics().size() != 0) {
                                List<SchoolContentBean.ContentPicsBean> contentPics = postingBean.getContentPics();
                                for (int i = 0; i < contentPics.size(); i++) {
                                    ImageView imageView = new ImageView(PostingActivity.this);
                                    imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,500));
                                    Picasso.with(PostingActivity.this).load(StringUtil.getReviseResponseBody(contentPics.get(i)
                                            .getPhotoFlvPath()))
                                            .centerCrop().into(imageView);
                                    llPostingIorv.addView(imageView);
                                }
                            }
                            if (postingBean.getContentType() != null && postingBean.getContentType().equals("flv")) {
                                JZVideoPlayerStandard jzVideoPlayerStandard = new JZVideoPlayerStandard(PostingActivity.this);
                                jzVideoPlayerStandard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
                                jzVideoPlayerStandard.setUp(postingBean.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "");
                                llPostingIorv.addView(jzVideoPlayerStandard);
                            }

                            tvPostingTitle.setText(postingBean.getTitle());
                            tvPostingContent.setText(postingBean.getContentText());
                            MethodUtil.setTextDrawableLeft(PostingActivity.this, tvZan, StaticValue.ZAN, postingBean.getLaud());
                            MethodUtil.setTextDrawableLeft(PostingActivity.this, tvShoucang, StaticValue.SHOWCANG, postingBean.getShouchang());
                            MethodUtil.setTextDrawableLeft(PostingActivity.this, tvPinglun, StaticValue.PINGLUN, "no");

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
                        BaseBean<List<PostingCommentBean>> baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<List<PostingCommentBean>>>() {
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
        tvZan.setOnClickListener(this);
        tvPinglun.setOnClickListener(this);
        tvShoucang.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        MethodUtil.zanAndshoucang(this,(TextView)v,baseBean.getData());
    }

    public class PostingAdapter extends BaseQuickAdapter<PostingCommentBean, BaseViewHolder> {

        public PostingAdapter() {
            super(R.layout.recent_pinglun_item, postingBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, PostingCommentBean item) {//填充item数据
            TextView name = null;
            TextView content = null;
            View view = null;

            Picasso.with(PostingActivity.this).load(item.getHeadPhoto()).fit()
                    .into((ImageView) helper.getView(R.id.headphoto));
            helper.setText(R.id.nick_name, item.getNickName())
                    .setText(R.id.pinglun_content, item.getOwnerText());

            List<PostingCommentBean.ChildrenBean> children = item.getChildren();
            LinearLayout llPingLun = helper.getView(R.id.ll_pinglun);

            for (PostingCommentBean.ChildrenBean c : children) {
                if (view == null) {
                    view = View.inflate(PostingActivity.this, R.layout.pinglun_children_item, null);
                    name = (TextView) view.findViewById(R.id.children_name);
                    content = (TextView) view.findViewById(R.id.children_content);
                }
                name.setText(c.getNickName());
                content.setText(":" + c.getOwnerText());
                llPingLun.addView(view);
            }


        }

    }
}
