package com.huidaforum.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.MyApplication;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBackActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.SchoolBean;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.ThreeDrawable;
import com.huidaforum.utils.WebAddress;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by lenovo on 2017/10/13.
 */

public class SchoolActivity extends BaseBackActivity {
    private static final String TAG = "SchoolActivity";
    RollPagerView rpvCommunity;
    @BindView(R.id.rlv_community)
    RecyclerView rlvCommunity;
    private BaseBean<List<SchoolContentBean>> baseBean;
    private ThreeDrawable threeDrawable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community;
    }

    @Override
    public void initView() {
        threeDrawable = ((MyApplication) SchoolActivity.this.getApplication()).threeDrawable;
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

                        Log.d(TAG, "onSuccess: " + baseBean.getData());

                        setRecyclerViewData();
                    }
                });
    }

    private void setRecyclerViewData() {
        rlvCommunity.setLayoutManager(new LinearLayoutManager(this));
        SchoolRecylerViewAdapter schoolRecylerViewAdapter = new SchoolRecylerViewAdapter();
        rpvCommunity = new RollPagerView(this);
        rpvCommunity.setPlayDelay(1000);
        int[] imgs = {
                R.drawable.collection_nor,
                R.drawable.collection_pre
        };
        rpvCommunity.setAdapter(new MyRollPageViewAdapter(this, imgs));
        rpvCommunity.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
        schoolRecylerViewAdapter.addHeaderView(rpvCommunity);
        schoolRecylerViewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SchoolContentBean schoolContentBean = baseBean.getData().get(i);
                MethodUtil.zanAndshoucang(SchoolActivity.this,(TextView)view,schoolContentBean,threeDrawable);
            }
        });
        rlvCommunity.setAdapter(schoolRecylerViewAdapter);
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

    private class SchoolRecylerViewAdapter extends BaseQuickAdapter<SchoolContentBean, BaseViewHolder> {

        public SchoolRecylerViewAdapter() {
            super(R.layout.item_tie, baseBean.getData());
        }

        private void setTextDrawableLeft(TextView textView, Drawable no, Drawable yes, String flag) {
            if (flag.equals("yes"))
                textView.setCompoundDrawables(yes, null, null, null);
            else {
                textView.setCompoundDrawables(no, null, null, null);
            }
        }

        protected void convert(BaseViewHolder holder, SchoolContentBean item) {
            holder.setText(R.id.tv_tie_nicheng, item.getNickName() + "")
                    .setText(R.id.tv_tie_title, item.getTitle())
                    .setText(R.id.tv_tie_data, item.getContentText() + "")
                    .setText(R.id.tv_zan,item.getZanCount()+"")
                    .addOnClickListener(R.id.tv_zan)
                    .addOnClickListener(R.id.tv_shoucang)
                    .addOnClickListener(R.id.tv_pinglun);

            TextView tv_zan = holder.getView(R.id.tv_zan);
            TextView tv_pinglun = holder.getView(R.id.tv_pinglun);
            TextView tv_shoucang = holder.getView(R.id.tv_shoucang);
            setTextDrawableLeft(tv_zan, threeDrawable.getZan_no(), threeDrawable.getZan_yes(), item.getLaud());
            setTextDrawableLeft(tv_pinglun, threeDrawable.getPinglun_no(), threeDrawable.getPinglun_yes(), item.getAnswer());
            setTextDrawableLeft(tv_shoucang, threeDrawable.getShoucang_no(), threeDrawable.getShoucang_yes(), item.getShouchang());
            //是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(SchoolActivity.this).load(item.getPhotoFlvPath()).into(iv_tie);
            } else {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.GONE);
            }
            //是否为视频
            if (item.getContentType().equals("flv")) {
                JZVideoPlayerStandard jps = holder.getView(R.id.jps);
                jps.setVisibility(View.VISIBLE);
                jps.setUp(item.getPhotoFlvPath(), JZVideoPlayer.SCREEN_LAYOUT_LIST, "");
            } else {
                JZVideoPlayerStandard jps = holder.getView(R.id.jps);
                jps.setVisibility(View.GONE);
            }
        }
    }

    class MyRollPageViewAdapter extends StaticPagerAdapter {

        private Context context;
        private int[] imgs;

        public MyRollPageViewAdapter(Context context, int[] imgs) {
            this.context = context;
            this.imgs = imgs;
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imgs[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return imageView;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }

    class MyAlertDialog extends AlertDialog{

        protected MyAlertDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
//            setContentView(R.layout.);
        }
    }
}
