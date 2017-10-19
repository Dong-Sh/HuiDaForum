package com.huidaforum.adapter;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huidaforum.R;
import com.huidaforum.bean.SchoolContentBean;
import com.huidaforum.utils.MethodUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lenovo on 2017/10/19.
 */

public class MyAdapter extends BaseQuickAdapter<SchoolContentBean, BaseViewHolder> {

    public MyAdapter(Integer layout, List<SchoolContentBean> beanList) {
        super(layout, beanList);
    }

    @Override
    protected void convert(BaseViewHolder holder, SchoolContentBean item) {
        holder.setText(R.id.tv_tie_nicheng, item.getNickName())
                .setText(R.id.tv_tie_title, item.getTitle())
                .setText(R.id.tv_tie_data, item.getContentText() + "")
                .setText(R.id.tv_zan, item.getZanCount() + "")
                .setText(R.id.tv_guanzhu, item.getGhuanzhu().equals("yes") ? "已关注" : "关注")
                .addOnClickListener(R.id.tv_zan)
                .addOnClickListener(R.id.tv_shoucang)
                .addOnClickListener(R.id.tv_pinglun)
                .addOnClickListener(R.id.tv_guanzhu);
        CircleImageView civ_photo = holder.getView(R.id.civ_photo);
        Picasso.with(mContext).load(StringUtil.getReviseResponseBody(item.getHeadPhoto())).fit().into(civ_photo);
        TextView tv_zan = holder.getView(R.id.tv_zan);
        tv_zan.setText(item.getZanCount() + "");


        ((TextView) holder.getView(R.id.tv_guanzhu)).setBackgroundResource(R.drawable.guanzhu_shape);

        MethodUtil.setTextDrawableLeft(mContext, tv_zan, StaticValue.ZAN, item.getLaud());
        MethodUtil.setTextDrawableLeft(mContext, (TextView) holder.getView(R.id.tv_shoucang), StaticValue.SHOWCANG, item.getShouchang());
        MethodUtil.setTextDrawableLeft(mContext, (TextView) holder.getView(R.id.tv_pinglun), StaticValue.PINGLUN, "no");
        Log.d(TAG, "convert: " + item.getShouchang());
        //是否有图片
        if (item.getContentType().equals("picture")) {
            ImageView iv_tie = holder.getView(R.id.iv_tie);
            iv_tie.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(item.getPhotoFlvPath()).into(iv_tie);
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
