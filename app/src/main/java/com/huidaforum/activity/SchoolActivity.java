package com.huidaforum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.adapter.MyAdapter;
import com.huidaforum.base.BaseBackActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.bean.listPicBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.huidaforum.view.FullyLinearLayoutManager;
import com.huidaforum.view.MyScrollView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2017/10/13.
 */

public class SchoolActivity extends BaseBackActivity {
    private static final String TAG = "SchoolActivity";

    @BindView(R.id.rlv_community)
    RecyclerView rlvCommunity;

    private BaseBean<List<SchoolContentBean>> baseBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initNetData();
    }

    private void initNetData() {
        OkGo.<String>post(WebAddress.bySchoolNameFindContent)
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .params("id", getIntent().getStringExtra("id"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<List<SchoolContentBean>>>() {
                        }.getType());
                        setRecyclerViewData();
                    }
                });
    }

    private void setRecyclerViewData() {

        rlvCommunity.setLayoutManager(new FullyLinearLayoutManager(this));
        MyAdapter schoolRecylerViewAdapter = new MyAdapter(R.layout.item_tie, baseBean.getData());
        rpvSetAdapter();
        rlvCommunity.setAdapter(schoolRecylerViewAdapter);

        Log.d(TAG, "setRecyclerViewData: 加载成功");
    }

    private void rpvSetAdapter() {
        Log.d(TAG, "onSuccessPcic: ");
        OkGo.<String>post(WebAddress.listPicForSchool)
                .params("model", getIntent().getStringExtra("id"))
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Log.d(TAG, "onSuccessPcic: " + response.body());
                        BaseBean<List<listPicBean>> baseBean = gson.fromJson(response.body(), new TypeToken<BaseBean<List<listPicBean>>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            List<listPicBean> data = baseBean.getData();
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        super.onCacheSuccess(response);
                    }
                });

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

    class MyRollPageViewAdapter extends LoopPagerAdapter {

        private List<listPicBean> data;

        public MyRollPageViewAdapter(RollPagerView viewPager, List<listPicBean> data) {
            super(viewPager);
            this.data = data;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            Picasso.with(SchoolActivity.this).load(data.get(position).getDetailPhoto()).fit().into(imageView);
            return imageView;
        }

        @Override
        public int getRealCount() {
            return data.size();
        }
    }

}
