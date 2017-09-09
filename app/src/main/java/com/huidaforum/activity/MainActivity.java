package com.huidaforum.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.huidaforum.R;
import com.huidaforum.fragment.CommunityFragment;
import com.huidaforum.fragment.HomeFragment;
import com.huidaforum.fragment.MineFragment;
import com.huidaforum.fragment.NewsFragment;
import com.huidaforum.utils.RenderScriptGaussianBlur;
import com.huidaforum.utils.StatusBarUtil;

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
    private PopupWindow window;
    private HomeFragment hf;
    private CommunityFragment cf;
    private NewsFragment nf;
    private MineFragment mf;
    private RenderScriptGaussianBlur blur;
    private View v;
    private boolean isshow = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private ImageView fullscreen_im;
    private ImageButton pMovie;
    private ImageButton pCamera;
    private ImageButton pPicture;
    private ImageButton pText;
    private LinearLayout pop_text_ll;
    private LinearLayout pop_picture_ll;
    private LinearLayout pop_camera_ll;
    private LinearLayout pop_movie_ll;
    private ImageButton icon_button;
    private ImageView iv_main_release_top;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullscreen_im = (ImageView) findViewById(R.id.fullscreen_fm);
        ButterKnife.bind(this);
        initView();

        v = getWindow().getDecorView();
        blur = new RenderScriptGaussianBlur(MainActivity.this);

        rgMainFooter.setOnCheckedChangeListener(this);
        rbMainHome.setChecked(true);
        ivMainRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启渐变动画
                if (!isshow) {
                    //旋转动画
                    setIvMainReleaseRotate();
                }
//                Intent intent = new Intent(MainActivity.this, ReleaseActivity.class);
//                startActivity(intent);
            }
        });
    }

    //按钮旋转动画
    private void setIvMainReleaseRotate() {
        ivMainRelease.setBackgroundResource(R.drawable.put_bottom);
        RotateAnimation animation =
                new RotateAnimation(0f, 135f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        ivMainRelease.startAnimation(animation);
        //显示popupwindow
        pushpupUpWindwo();
        fullscreen_im.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        fullscreen_im.startAnimation(alphaAnimation);
        isshow = true;
    }

    private void pushpupUpWindwo() {
        View inflate = getLayoutInflater().inflate(R.layout.pop_window_item_layout, null);
        window = new PopupWindow(inflate, getWindowManager().getDefaultDisplay().getWidth(),
                (int) (getWindowManager().getDefaultDisplay().getHeight()*0.5));
        //设置popupwindow显示动画效果
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        //必须添加----
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        //截图
        v.setDrawingCacheEnabled(true);
        v.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        Bitmap bitmap = v.getDrawingCache();
        //高斯模糊
        Bitmap gaussianBlur = blur.gaussianBlur(25, bitmap);
        Drawable drawable = new BitmapDrawable(gaussianBlur);
        fullscreen_im.setBackground(drawable);


        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(ivMainRelease, 0, 5);
        initpopupwindow(inflate);


        //popupwindow消失
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ivMainRelease.setBackgroundResource(R.drawable.release);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.0f);
                alphaAnimation.setDuration(2000);
                alphaAnimation.setFillAfter(true);
                fullscreen_im.startAnimation(alphaAnimation);
                fullscreen_im.setVisibility(View.GONE);
                isshow=false;
            }
        });

    }
       //popupwindow内部点击事件
    private void initpopupwindow(View inflate) {
        pText = (ImageButton) inflate.findViewById(R.id.pop_text);
        pPicture = (ImageButton) inflate.findViewById(R.id.pop_picture);
        pCamera = (ImageButton) inflate.findViewById(R.id.pop_camera);
        pMovie = (ImageButton) inflate.findViewById(R.id.pop_movie);
        pop_text_ll = (LinearLayout) inflate.findViewById(R.id.pop_text_ll);
        pop_picture_ll = (LinearLayout) inflate.findViewById(R.id.pop_picture_ll);
        pop_camera_ll = (LinearLayout) inflate.findViewById(R.id.pop_camera_ll);
        pop_movie_ll = (LinearLayout) inflate.findViewById(R.id.pop_movie_ll);

        //逐个显示动画
        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pText,1500, pop_text_ll);
            }
        }, 350);

        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pPicture,1500, pop_picture_ll);
            }
        }, 550);
        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pCamera,1500, pop_camera_ll);
            }
        }, 750);

        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pMovie,1500, pop_movie_ll);
            }
        }, 950);

    }
//popupwindow 出现 图片初始化动画
    public void setChangeBig(ImageButton changeBig, int i,LinearLayout ll) {
        ll.setVisibility(View.VISIBLE);
        Animation animation = new ScaleAnimation(0, 1.2f, 0f, 1.2f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(i);
        changeBig.setAnimation(animation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.2f, 1f, 1.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        changeBig.setAnimation(scaleAnimation);
    }

    private void initView() {
        Drawable drawable = getResources().getDrawable(R.drawable.rb_home_selector);
        drawable.setBounds(0, 0, 50, 48);
        rbMainHome.setCompoundDrawables(null, drawable, null, null);
        Drawable drawable1 = getResources().getDrawable(R.drawable.rb_community_selector);
        drawable1.setBounds(0, 0, 50, 48);
        rbMainCommunity.setCompoundDrawables(null, drawable1, null, null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.rb_news_selector);
        drawable2.setBounds(0, 0, 50, 48);
        rbMainNews.setCompoundDrawables(null, drawable2, null, null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.rb_mine_selector);
        drawable3.setBounds(0, 0, 50, 48);
        rbMainMine.setCompoundDrawables(null, drawable3, null, null);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        hideAllFragment(ft);
        switch (checkedId) {
            case R.id.rb_main_home:
                if (hf == null) {
                    hf = new HomeFragment();
                    ft.add(R.id.fl_main, hf);
                } else ft.show(hf);
                StatusBarUtil.setTransparentForImageView(MainActivity.this, flMain);
                break;
            case R.id.rb_main_community:
                if (cf == null) {
                    cf = new CommunityFragment();
                    ft.add(R.id.fl_main, cf);
                } else ft.show(cf);
                StatusBarUtil.setTransparentForImageView(MainActivity.this, flMain);

                break;
            case R.id.rb_main_news:
                if (nf == null) {
                    nf = new NewsFragment();
                    ft.add(R.id.fl_main, nf);
                } else ft.show(nf);
                StatusBarUtil.setTransparentForImageView(MainActivity.this, flMain);
                StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.red));
                break;
            case R.id.rb_main_mine:
                if (mf == null) {
                    mf = new MineFragment();
                    ft.add(R.id.fl_main, mf);
                } else ft.show(mf);
                StatusBarUtil.setTransparentForImageView(MainActivity.this, flMain);
                StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.red));
                break;
        }
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (hf != null) ft.hide(hf);
        if (cf != null) ft.hide(cf);
        if (nf != null) ft.hide(nf);
        if (mf != null) ft.hide(mf);
    }
}
