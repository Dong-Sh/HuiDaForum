package com.huidaforum.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        BaseBean<List<MinePublishBean>> minePublishBean = gson.fromJson(s,
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
            rvMinePublish.setLayoutManager(new LinearLayoutManager(this));
            MinePublishAdapter minePublishAdapter = new MinePublishAdapter(R.layout.mine_publish_item, data);
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
            }//Todo Adapter的填充

          /*  helper.setText(R.id.tv_publish_item_title, item.getTitle().toString());
            helper.setText(R.id.tv_comment_time, item.getCreateTime());
            helper.setText(R.id.tv_comment_ownertext, item.getOwnerText());
            helper.setText(R.id.tv_comment_title, item.getTitle());*/
        }

        private void setTextDrawableLeft(TextView textView, Drawable no, Drawable yes, String flag) {
            if (flag.equals("yes"))
                textView.setCompoundDrawables(yes, null, null, null);
            else {
                textView.setCompoundDrawables(no, null, null, null);
            }
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }
}
