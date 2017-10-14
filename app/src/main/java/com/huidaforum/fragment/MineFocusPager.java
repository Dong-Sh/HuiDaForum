package com.huidaforum.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MineFocusBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaojiu on 2017/10/14.
 * 我的关注
 */

public class MineFocusPager {
    public int flag;
    public Activity mActivity;
    public View rootView;
    public RecyclerView rv_basepager;
    private List<MineFocusBean> data;
    private FocusAdapter focusAdapter;

    public MineFocusPager(Activity activity, int flag) {
        this.mActivity = activity;
        this.flag = flag;
        initView();
    }

    private void initView() {
        rootView = View.inflate(mActivity, R.layout.basepager_layout, null);
        rv_basepager = (RecyclerView) rootView.findViewById(R.id.rv_basepager);
    }

    public void initData() {
        if (flag == 0) {
            getFocusData();
        } else {
            getUpdateData();
        }
        if (data != null) {
            parseData();
        }
    }
    //解析数据
    private void parseData() {
        rv_basepager.setLayoutManager(new LinearLayoutManager(mActivity));
        focusAdapter = new FocusAdapter(R.layout.mine_focus_item, data);
        rv_basepager.setAdapter(focusAdapter);
    }
    //获取最新状态
    private void getUpdateData() {
        data = null;
    }
    //获取我的关注
    private void getFocusData() {
        data = null;
        OkGo.<String>post(WebAddress.mineFocus)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, mActivity))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        BaseBean<List<MineFocusBean>> mineFocusBean = gson.fromJson(s,
                                new TypeToken<BaseBean<List<MineFocusBean>>>() {
                                }.getType());
                        if (mineFocusBean.isSuccess()) {
                            data = mineFocusBean.getData();
                            parseData();
                        }
                    }
                });
    }
    class FocusAdapter extends BaseQuickAdapter<MineFocusBean,BaseViewHolder>{

        public FocusAdapter(@LayoutRes int layoutResId, @Nullable List<MineFocusBean> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(final BaseViewHolder helper, final MineFocusBean item) {
            helper.setText(R.id.tv_mine_name,item.getNickName());
            helper.setText(R.id.tv_mine_content,item.getOffical());
            Picasso.with(mActivity).load(item.getHeadPhoto()).placeholder(R.mipmap.ic_launcher).fit().into((ImageView) helper.getView(R.id.iv_mine_pic));
            helper.getView(R.id.ll_mine_isfocus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(mActivity);
                    final int adapterPosition = helper.getAdapterPosition();
                    dialog.setTitle("提示");
                    dialog.setMessage("确定不再关注此人？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //取消关注的方法
                            deleteFocus(item,adapterPosition);
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
            });
        }
    }
    //取消关注
    private void deleteFocus(MineFocusBean item, final int position) {
        OkGo.<String>post(WebAddress.deleteMineFocus)
                .params("devType", "phone")
                .params("guanzhuUserId",item.getGuanzhuUserId())
                .params("token", SpUtil.getString(StaticValue.TOKEN, mActivity))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        BaseBean<Object> baseBean = gson.fromJson(body,
                                new TypeToken<BaseBean<Object>>() {
                                }.getType());
                        if(baseBean.isSuccess()){
                            if (data!=null)
                                data.remove(position);
                            if(focusAdapter!=null)
                                focusAdapter.notifyDataSetChanged();
                            Toast.makeText(mActivity, "取消关注成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
