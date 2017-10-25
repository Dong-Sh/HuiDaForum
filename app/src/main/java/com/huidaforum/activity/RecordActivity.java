package com.huidaforum.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.base.RecordBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.huidaforum.view.CircleTransform;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

import static com.huidaforum.utils.WebAddress.queryMoneyRecList;

public class RecordActivity extends BaseActivity {

    @BindView(R.id.rv_record)
    RecyclerView rvRecord;
    private List<RecordBean> recordBeen;
    private RecordAdapter recordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        rvRecord.setLayoutManager(new LinearLayoutManager(RecordActivity.this));

        OkGo.<String>post(queryMoneyRecList).tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, RecordActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BaseBean<List<RecordBean>> baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean<List<RecordBean>>>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            recordBeen = baseBean.getData();
                            if (recordBeen == null) return;
                            if (recordAdapter==null){
                                recordAdapter = new RecordAdapter();
                                rvRecord.setAdapter(recordAdapter);
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
    class RecordAdapter extends BaseQuickAdapter<RecordBean,BaseViewHolder>{

        public RecordAdapter() {
            super(R.layout.record_item, recordBeen);
        }

        @Override
        protected void convert(BaseViewHolder helper, RecordBean item) {
            helper.setText(R.id.tv_record_name,item.getNickName())
                    .setText(R.id.tv_record_money,item.getMoneyDeal()+"")
                    .setText(R.id.tv_record_time,item.getCreateTime());
            Picasso.with(RecordActivity.this)
                    .load(item.getHeadPhoto())
                    .fit()
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new CircleTransform())
                    .into((ImageView) helper.getView(R.id.iv_record_pic));
            switch (item.getMoneyType()){
                case "DS":
                    helper.setText(R.id.tv_record_type,"打赏");
                    break;
                case "BDS":
                    helper.setText(R.id.tv_record_type,"被打赏");
                    break;
                case "TX":
                    helper.setText(R.id.tv_record_type,"提现");
                    break;
            }
        }
    }
}
