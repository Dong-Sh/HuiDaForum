package com.huidaforum.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.huidaforum.R;
import com.huidaforum.activity.HomePopularActivity;
import com.huidaforum.base.BaseFragment;
import com.huidaforum.bean.AllContentsBean;
import com.huidaforum.bean.Bean;
import com.huidaforum.bean.InvitationBean;
import com.huidaforum.utils.StatusBarUtil;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 主页中首页页面
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    @BindView(R.id.rlv_home)
    RecyclerView rlvHome;
    private ArrayList<Bean> been;
    private Button bt_popular;
    private Button bt_infomation;
    private Button bt_selection;
    private List<AllContentsBean.DataBean> data;


    @Override
    public View initView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initData() {
        final View view = View.inflate(mActivity, R.layout.top_home, null);
        bt_popular = (Button) view.findViewById(R.id.bt_popular);
        bt_infomation = (Button) view.findViewById(R.id.bt_infomation);
        bt_selection = (Button) view.findViewById(R.id.bt_selection);

        been = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean("我是测试文本" + i);
            been.add(bean);
        }
     OkGo.<String>post(WebAddress.listAllContents).
             params("devType", "phone").params("token","43df7e6c35bd47d891b491c4f2ef5389").execute(new StringCallback() {
         @Override
         public void onSuccess(Response<String> response) {
             AllContentsBean bean = new Gson().fromJson(response.body().toString(), AllContentsBean.class);
             if(bean.isSuccess()){
                 data= bean.getData();
                 MyAdapter adapter = new MyAdapter();
                 adapter.addHeaderView(view);
                 rlvHome.setAdapter(adapter);
                 rlvHome.setLayoutManager(new LinearLayoutManager(mActivity));
             }
         }
     });



    }
    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.bt_popular:
                   Intent intent = new Intent(mActivity, HomePopularActivity.class);
                   intent.putExtra("title","最新帖子");
                   intent.putExtra("url", WebAddress.selectByCountTime);
                   startActivity(intent);
                   break;
               case R.id.bt_infomation:
                   Intent intent1 = new Intent(mActivity, HomePopularActivity.class);
                   intent1.putExtra("title","热门好帖");
                   intent1.putExtra("url",WebAddress.seleteByContentHot);
                   startActivity(intent1);
                   break;
               case R.id.bt_selection:
                   Intent intent2 = new Intent(mActivity, HomePopularActivity.class);
                   intent2.putExtra("title","精品好帖");
                   intent2.putExtra("url",WebAddress.seleteByContentJingpin);
                   startActivity(intent2);
                   break;
           }
    }

    @Override
    protected void initListener() {
        bt_popular.setOnClickListener(this);
        bt_infomation.setOnClickListener(this);
        bt_selection.setOnClickListener(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    class MyAdapter extends BaseQuickAdapter<AllContentsBean.DataBean, BaseViewHolder> {

        public MyAdapter() {
            super(R.layout.item_tie, data);
        }
        @Override
        protected void convert(BaseViewHolder holder, AllContentsBean.DataBean item) {
            Log.e("aa","aaaaaaaaaaaa");
            holder.setText(R.id.tv_tie_nicheng, item.getNickName() + "")
                    .setText(R.id.tv_tie_title, item.getTitle())
                    .setText(R.id.tv_tie_data, item.getContentText() + "")
                    .addOnClickListener(R.id.tv_zan)
                    .addOnClickListener(R.id.tv_shoucang)
                    .addOnClickListener(R.id.tv_pinglun);
            //是否有图片
            if (item.getContentType().equals("picture")) {
                ImageView iv_tie = holder.getView(R.id.iv_tie);
                iv_tie.setVisibility(View.VISIBLE);
                Picasso.with(mActivity).load(item.getPhotoFlvPath()).into(iv_tie);
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
}
