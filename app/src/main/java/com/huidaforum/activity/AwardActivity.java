package com.huidaforum.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.bean.AwardBean;
import com.huidaforum.bean.BaseBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

//我打赏过谁
public class AwardActivity extends BaseActivity {

    @BindView(R.id.rv_award)
    RecyclerView rv_Award;
    @BindView(R.id.sr_award)
    SmartRefreshLayout sr_Award;
    private List<AwardBean> awardBeanList;
    private AwardAdapter awardAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_award;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //下拉刷新头样式
        sr_Award.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        getOkGo();
    }

    @Override
    public void initListener() {
        //下拉刷新监听
        sr_Award.setOnRefreshListener(new OnRefreshListener() {
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
    public void getOkGo(){//从服务区获取我的打赏GSON数据
        OkGo.<String>post(WebAddress.selectAward)
                .tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<AwardBean>> baseBean = gson.fromJson(response.body()
                                , new TypeToken<BaseBean<List<AwardBean>>>() {}.getType());
                        if (baseBean.isSuccess()){
                            awardBeanList = baseBean.getData();
                            rv_Award.setLayoutManager(new LinearLayoutManager(AwardActivity.this));
                            if (awardAdapter==null){
                                awardAdapter = new AwardAdapter();
                                rv_Award.setAdapter(awardAdapter);
                                return;
                            }
                            awardAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    //我的打赏Adapter
    public class AwardAdapter extends BaseQuickAdapter<AwardBean,BaseViewHolder>{

        public AwardAdapter() {
            super(R.layout.award_item, awardBeanList);
        }

        @Override
        protected void convert(BaseViewHolder helper, AwardBean item) {//填充item数据
            //TextView数据
            helper.setText(R.id.tv_reward_name,item.getNickName())
                    .setText(R.id.tv_reward_money,item.getAwardMoney()+"")
                    .setText(R.id.tv_award_time,item.getCreateTime());
            //图片框数据 ， 暂时无头像
         Picasso.with(AwardActivity.this).load("http://www.ghost64.com/qqtupian/qqTxImg/11/2012091910272111600.jpg").fit().memoryPolicy(NO_CACHE, NO_STORE).placeholder(R.mipmap.ic_launcher).into((ImageView) helper.getView(R.id.iv_reward_pic));

        }
    }
}
