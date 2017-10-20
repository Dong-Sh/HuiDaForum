package com.huidaforum.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.huidaforum.R;
import com.huidaforum.adapter.ViewPagerAdapter;
import com.huidaforum.base.BaseActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gui on 2017/10/16.
 */

public class PlusImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    @BindView(R.id.back_iv)
    ImageView backIv;
    @BindView(R.id.position_tv)
    TextView positionTv;
    @BindView(R.id.delete_iv)
    ImageView deleteIv;
    @BindView(R.id.rpv)
    RollPagerView rpv;
    private int mPosition;
    private ArrayList<String> mList;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_plus;

    }

    @Override
    public void initView() {
        mPosition = getIntent().getIntExtra("position", 0);
        mList = getIntent().getStringArrayListExtra("list");
        rpv.setHintView(new ColorPointHintView(this, Color.parseColor("#FFFFFFFF"),Color.parseColor("#FF969696")));
        viewPager = rpv.getViewPager();
        viewPager.addOnPageChangeListener(this);
        adapter = new ViewPagerAdapter(PlusImageActivity.this, mList);
        viewPager.setAdapter(adapter);
        positionTv.setText(mPosition+1+"/"+mList.size());
        viewPager.setCurrentItem(mPosition);
        backIv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {
              switch (v.getId()){
                  case R.id.back_iv:
                      back();
                      break;
                  case R.id.delete_iv:
                      delete();
                      break;
              }
    }

    private void delete() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PlusImageActivity.this);
        builder.setCancelable(false).setMessage("要删除这张图片吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                      mList.remove(mPosition);
                if (mList.size()!=0){
                    positionTv.setText(mPosition+1+"/"+mList.size());

                }else {
                    positionTv.setText(mPosition+"/"+mList.size());

                }
                      viewPager.setCurrentItem(mPosition);
                      adapter.notifyDataSetChanged();

            }
        }).show();
    }

    private void back() {
        Intent intent = getIntent();
        intent.putStringArrayListExtra("list",mList);
        setResult(11,intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        positionTv.setText(position + 1 + "/" + mList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            back();
        }
        return super.onKeyDown(keyCode, event);
    }
}
