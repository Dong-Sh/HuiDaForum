package com.huidaforum.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineCollectBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/9/2.
 * 我的收藏页面
 */

public class MineCollectActivity extends BaseActivity {
    @BindView(R.id.rv_mine_collect)
    RecyclerView rvMineCollect;
    private static final int COLLECT_NET_KEY = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case COLLECT_NET_KEY:
                    List<MineCollectBean> data = (List<MineCollectBean>) msg.obj;
                    //填充布局
                    parseData(data);
                    break;
            }
        }
    };

    private void parseData(List<MineCollectBean> data) {
        rvMineCollect.setLayoutManager(new LinearLayoutManager(this));
        mineCollectAdapter mineCollectAdapter = new mineCollectAdapter(R.layout.mine_collect_item,data);
        rvMineCollect.setAdapter(mineCollectAdapter);
    }
    //获取数据
    public class mineCollectAdapter extends BaseQuickAdapter<MineCollectBean,BaseViewHolder>{
        public mineCollectAdapter(@LayoutRes int layoutResId,List<MineCollectBean> data) {
            super(layoutResId,data);
        }
        @Override
        protected void convert(BaseViewHolder helper, MineCollectBean item) {
            helper.setText(R.id.tv_collect_title,item.title);
            helper.setText(R.id.tv_collect_time,item.createTime);
            helper.setText(R.id.tv_collect_name,item.nickName);
            if(TextUtils.isEmpty(item.photoFlvPath)||TextUtils.equals(null,item.photoFlvPath)){
                helper.getView(R.id.iv_collect_pic).setVisibility(View.INVISIBLE);
            }else{
                Picasso.with(mContext).load(item.photoFlvPath).fit().into((ImageView) helper.getView(R.id.iv_collect_pic));
            }
        }
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_collect;
    }

    @Override
    public void initView() {

    }
    //解析gson串
    @Override
    public void initData() {
        OkGo.<String>post(WebAddress.shoucanglistPages)
                .params("devType","phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN,this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        Log.e("jdr","我的收藏"+s);
                        BaseBean<List<MineCollectBean>> mineCollectBean = gson.fromJson(s,
                                            new TypeToken<BaseBean<List<MineCollectBean>>>(){}.getType());
                        if(mineCollectBean.isSuccess()) {
                            List<MineCollectBean> data = mineCollectBean.getData();
                            Message obtain = Message.obtain();
                            obtain.what = COLLECT_NET_KEY;
                            obtain.obj = data;
                            handler.sendMessage(obtain);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
