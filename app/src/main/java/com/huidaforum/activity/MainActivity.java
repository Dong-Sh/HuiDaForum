package com.huidaforum.activity;

import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huidaforum.R;
import com.huidaforum.fragment.CommunityFragment;
import com.huidaforum.fragment.HomeFragment;
import com.huidaforum.fragment.MineFragment;
import com.huidaforum.fragment.NewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rb_main_home)
    RadioButton rbMainHome;
    @BindView(R.id.rb_main_community)
    RadioButton rbMainCommunity;
    @BindView(R.id.iv_main_release)
    ImageView ivMainRelease;
    @BindView(R.id.rb_main_news)
    RadioButton rbMainNews;
    @BindView(R.id.rb_main_mine)
    RadioButton rbMainMine;
    @BindView(R.id.rg_main_footer)
    RadioGroup rgMainFooter;
    private HomeFragment hf;
    private CommunityFragment cf;
    private NewsFragment nf;
    private MineFragment mf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        rgMainFooter.setOnCheckedChangeListener(this);
        rbMainHome.setChecked(true);
        ivMainRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "aaaa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        Drawable drawable = getResources().getDrawable(R.drawable.rb_home_selector);
        drawable.setBounds(0,0,50,48);
        rbMainHome.setCompoundDrawables(null,drawable,null,null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.rb_community_selector);
        drawable1.setBounds(0,0,50,48);
        rbMainCommunity.setCompoundDrawables(null,drawable1,null,null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.rb_news_selector);
        drawable2.setBounds(0,0,50,48);
        rbMainNews.setCompoundDrawables(null,drawable2,null,null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.rb_mine_selector);
        drawable3.setBounds(0,0,50,48);
        rbMainMine.setCompoundDrawables(null,drawable3,null,null);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        hideAllFragment(ft);
         switch (checkedId){
             case  R.id.rb_main_home:
                 if (hf==null){
                     hf=new HomeFragment();
                     ft.add(R.id.fl_main,hf);
                 }else ft.show(hf);
                 break;
             case R.id.rb_main_community:
                 if (cf==null){
                     cf=new CommunityFragment();
                     ft.add(R.id.fl_main,cf);
                 }else ft.show(cf);
                 break;
             case R.id.rb_main_news:
                 if (nf==null){
                     nf=new NewsFragment();
                     ft.add(R.id.fl_main,nf);
                 }else ft.show(nf);
                 break;
             case R.id.rb_main_mine:
                 if (mf==null){
                    mf=new MineFragment();
                     ft.add(R.id.fl_main,mf);
                 }else ft.show(mf);
                 break;
         }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (hf!=null)ft.hide(hf);
        if(cf!=null)ft.hide(cf);
        if(nf!=null)ft.hide(nf);
        if(mf!=null)ft.hide(mf);
    }
}
