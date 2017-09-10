package com.huidaforum.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StatusBarUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;

import static com.huidaforum.activity.WelcomActivity.IS_OPENMAIN;
import static com.squareup.picasso.MemoryPolicy.NO_CACHE;
import static com.squareup.picasso.MemoryPolicy.NO_STORE;

/**
 * Created by xiaojiu on 2017/9/10.
 * 引导页面
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.activity_guide)
    RelativeLayout activityGuide;
    private ArrayList<ImageView> imageList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    public void initView() {
        initData();
        //存储已经跳转的mainactivity的标记
        SpUtil.putBoolean(IS_OPENMAIN, true, GuideActivity.this);
    }

    public void initData() {
        //准备数据
        int[] images = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};
        imageList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            Picasso.with(this).load(images[i]).fit().memoryPolicy(NO_CACHE, NO_STORE).into(iv);
            imageList.add(iv);
        }
        //设置viewpager的动画
        vpGuide.setPageTransformer(true, new AnimTransformer());

        //初始化viewpager数据，通过适配器完成
        vpGuide.setAdapter(new GuideAdapter());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }


    class GuideAdapter extends PagerAdapter {
        //条目数量
        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //创建条目方法
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //将条目的view对象添加到容器中
            ImageView iv = imageList.get(position);
            container.addView(iv);
            //将条目的view返回
            return iv;
        }

        //销毁条目方法
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this,null);
    }
}

