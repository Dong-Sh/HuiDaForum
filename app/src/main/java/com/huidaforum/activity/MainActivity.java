package com.huidaforum.activity;

import android.animation.ValueAnimator;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
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
import com.huidaforum.base.BaseActivity;
import com.huidaforum.fragment.CommunityFragment;
import com.huidaforum.fragment.HomeFragment;
import com.huidaforum.fragment.MineFragment;
import com.huidaforum.fragment.NewsFragment;
import com.huidaforum.utils.RenderScriptGaussianBlur;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StatusBarUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.huidaforum.utils.StaticValue.REQUEST_CODE_PERMISSION;
import static com.huidaforum.utils.StaticValue.REQUEST_CODE_SETTING;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "MainActivity";
    private long exitTime = 0;
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
    private boolean isshow = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
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
    private BroadcastToExit broadcastToExit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (broadcastToExit == null) {
                broadcastToExit = new BroadcastToExit();
                IntentFilter intentFilter = new IntentFilter(StaticValue.EXIT_ACTION);
                registerReceiver(broadcastToExit, intentFilter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

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
                new RotateAnimation(-45f, 0f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(350);
        animation.setFillAfter(true);
        ivMainRelease.startAnimation(animation);
        //显示popupwindow
        pushpupUpWindwo();
        //fullscreen_im.startAnimation(alphaAnimation);
        isshow = true;
    }

    private void pushpupUpWindwo() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        View inflate = getLayoutInflater().inflate(R.layout.pop_window_item_layout, null);
        window = new PopupWindow(inflate, metrics.widthPixels,
                metrics.heightPixels - rgMainFooter.getHeight());

        //设置popupwindow显示动画效果
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        //必须添加----sd
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

        window.setFocusable(true);
        window.setOutsideTouchable(true);
        window.showAsDropDown(ivMainRelease, 0, 5);
        initpopupwindow(inflate);


        //popupwindow消失
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ivMainRelease.setBackgroundResource(R.drawable.release);
                //fullscreen_im.startAnimation(alphaAnimation);
                isshow = false;
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
                setChangeBig(pText, 1500, pop_text_ll);
            }
        }, 900);
        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pPicture, 1500, pop_picture_ll);
            }
        }, 1100);
        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pCamera, 1500, pop_camera_ll);
            }
        }, 1300);

        handler.postDelayed(new Runnable() {
            public void run() {
                setChangeBig(pMovie, 1500, pop_movie_ll);
            }
        }, 1500);
        pop_text_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textpermission();
            }
        });
        pop_picture_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picturepermission();
            }
        });
        pop_camera_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camerapermission();
            }
        });
    }

    private void textpermission() {
        AndPermission.with(MainActivity.this)
                .requestCode(102)
                .permission(Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();
    }

    private void Picturepermission() {
        AndPermission.with(MainActivity.this)
                .requestCode(101)
                .permission(Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();
    }

    private void Camerapermission() {
        AndPermission.with(MainActivity.this)
                .requestCode(REQUEST_CODE_PERMISSION)
                .permission(Permission.CAMERA,Permission.MICROPHONE,Permission.STORAGE)
                .rationale(rationaleListener)
                .callback(permissionListener)
                .start();
    }

    //popupwindow 出现 图片初始化动画
    public void setChangeBig(ImageButton changeBig, int i, LinearLayout ll) {
        ll.setVisibility(View.VISIBLE);
        Animation animation = new ScaleAnimation(0, 1.2f, 0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(i);
        changeBig.setAnimation(animation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.2f, 1f, 1.2f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        changeBig.startAnimation(scaleAnimation);
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
        boolean flag = false;
        switch (checkedId) {
            case R.id.rb_main_home:
                flag = true;
                if (hf == null) {
                    hf = new HomeFragment();
                    ft.add(R.id.fl_main, hf);
                } else ft.show(hf);
                break;
            case R.id.rb_main_community:
                if (cf == null) {
                    cf = new CommunityFragment();
                    ft.add(R.id.fl_main, cf);
                } else ft.show(cf);
                break;
            case R.id.rb_main_news:
                if (nf == null) {
                    nf = new NewsFragment();
                    ft.add(R.id.fl_main, nf);
                } else ft.show(nf);
                break;
            case R.id.rb_main_mine:
                if (mf == null) {
                    mf = new MineFragment();
                    ft.add(R.id.fl_main, mf);
                } else ft.show(mf);
                break;
        }
        StatusBarUtil.setTransparentForImageView(MainActivity.this, flMain);
        if (flag)
            StatusBarUtil.setColor(MainActivity.this, Color.TRANSPARENT);
        else
            StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.red));
        ft.commit();
    }

    private void hideAllFragment(FragmentTransaction ft) {
        if (hf != null) ft.hide(hf);
        if (cf != null) ft.hide(cf);
        if (nf != null) ft.hide(nf);
        if (mf != null) ft.hide(mf);
    }

    public class BroadcastToExit extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra(StaticValue.EXIT).equals(StaticValue.EXIT)) {
                unregisterReceiver(broadcastToExit);
                broadcastToExit = null;
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unregisterReceiver(broadcastToExit);
        broadcastToExit = null;
        finish();
    }
    private PermissionListener permissionListener=new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode){
                case REQUEST_CODE_PERMISSION:
                    startActivity(new Intent(MainActivity.this,CameraViewActivity.class));
                    break;
                case 102:
                    Intent intent = new Intent(MainActivity.this, ReleaseActivity.class);
                    intent.putExtra("style",3);
                    startActivity(intent);
                    break;
                case 101: //图片
                    Matisse.from(MainActivity.this)
                            .choose(MimeType.allOf())
                            .countable(true)
                            .maxSelectable(6)
                            .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                            .thumbnailScale(0.85f) // 缩略图的比例
                            .theme(R.style.Matisse_Dracula)
                            .imageEngine(new PicassoEngine()) // 使用的图片加载引擎
                            .forResult(201);
                    break;

            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode){
                case REQUEST_CODE_PERMISSION:
                    Toast.makeText(MainActivity.this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                    break;
            }
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(MainActivity.this,REQUEST_CODE_SETTING).show();

                // 第二种：用自定义的提示语。
//             AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                     .setTitle("权限申请失败")
//                     .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                     .setPositiveButton("好，去设置")
//                     .show();

//            第三种：自定义dialog样式。
//            SettingService settingHandle = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingHandle.execute();
//            你的dialog点击了取消调用：
//            settingHandle.cancel();
            }
        }

    };
    private RationaleListener rationaleListener =new RationaleListener() {
        @Override
        public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
            AndPermission.rationaleDialog(MainActivity.this,rationale).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 201:
                    List<Uri> list = Matisse.obtainResult(data);
                    ArrayList<String> strings = new ArrayList<>();
                    if (list.size() > 0 && !list.isEmpty() && list != null) {
                        Intent intent = new Intent(MainActivity.this, ReleaseActivity.class);
                        for (int i = 0; i <list.size(); i++) {
                            String path = getRealFilePath(MainActivity.this, list.get(i));
                            strings.add(path);
                        }
                        intent.putExtra("style",4);
                        intent.putStringArrayListExtra("list",strings);
                        startActivity(intent);
                    }
                    break;
            }
        }
    }
    public  String getRealFilePath(Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
        {


            if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
            {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }
            else
            {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
