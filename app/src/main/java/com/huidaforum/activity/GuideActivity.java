package com.huidaforum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StatusBarUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.tv_guide)
    TextView tvGuide;

    private ArrayList<ImageView> imageList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    public void initView() {
        initData();
        //存储已经跳转的mainactivity的标记
        SpUtil.putBoolean(StaticValue.IS_OPENMAIN, true, GuideActivity.this);
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
        //设置viewpager的页面变化的监听
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //页面滚动
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当页面被选中时调用
            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    //当前选中的是最后一个条目,button可见
                    tvGuide.setVisibility(View.VISIBLE);
                    //button的点击事件
                    tvGuide.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到主界面
                            Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    //其他条目，button不可见
                    tvGuide.setVisibility(View.INVISIBLE);
                }

            }

            //页面状态改变时
            @Override
            public void onPageScrollStateChanged(int state) {

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
        StatusBarUtil.setTransparentForImageView(this, null);
    }
}

