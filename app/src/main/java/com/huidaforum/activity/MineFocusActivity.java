package com.huidaforum.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.fragment.MineFocusPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiaojiu on 2017/9/2.
 * 我的关注页面
 */

public class MineFocusActivity extends BaseActivity {
    @BindView(R.id.tv_mine_myfocus)
    TextView tvMineMyfocus;
    @BindView(R.id.tv_mine_newstate)
    TextView tvMineNewstate;
    @BindView(R.id.vp_mine_focus)
    ViewPager vpMineFocus;
    private List<MineFocusPager> pagerList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_focus;
    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {
        pagerList = new ArrayList<>();
        pagerList.add(new MineFocusPager(this,0));
        pagerList.add(new MineFocusPager(this,1));
        vpMineFocus.setAdapter(new mineFocusAdapter());
        pagerList.get(0).initData();
        vpMineFocus.setCurrentItem(0);
    }

    class mineFocusAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return pagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View rootView = pagerList.get(position).rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    @Override
    public void initListener() {
        tvMineNewstate.setOnClickListener(this);
        tvMineMyfocus.setOnClickListener(this);
        vpMineFocus.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchPage(position);
                pagerList.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //我的关注和最新状态的切换
    private void switchPage(int position) {
        int red = getResources().getColor(R.color.red);
        int gary = getResources().getColor(R.color.gary_textcolor);
        if(position==0){
            tvMineMyfocus.setTextColor(red);
            tvMineNewstate.setTextColor(gary);
        }else{
            tvMineMyfocus.setTextColor(gary);
            tvMineNewstate.setTextColor(red);
        }
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
            case R.id.tv_mine_myfocus:
                vpMineFocus.setCurrentItem(0);
                break;
            case R.id.tv_mine_newstate:
                vpMineFocus.setCurrentItem(1);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
