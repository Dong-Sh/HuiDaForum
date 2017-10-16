package com.huidaforum.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ClickListener;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;

import java.io.File;

/**
 * Created by gui on 2017/10/15.
 */

public class CameraViewActivity extends BaseActivity {

    private JCameraView jcv;

    public int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return R.layout.activity_camera;
    }


    public void initView() {
        jcv = (JCameraView) findViewById(R.id.jcv);
        jcv.setSaveVideoPath(Environment.getExternalStorageDirectory().getPath()+File.separator + "huida");
        jcv.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        jcv.setTip("轻触拍照  按住摄像");
        jcv.setMediaQuality(JCameraView.MEDIA_QUALITY_HIGH);
        jcv.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                //拍照返回的照片
                String path = FileUtil.saveBitmap("huida", bitmap);
                Intent intent = new Intent(CameraViewActivity.this, ReleaseActivity.class);
                intent.putExtra("url",path);
                intent.putExtra("style",1);//1为图片
                startActivity(intent);
                finish();
            }

            @Override
            public void recordSuccess(String url, Bitmap firstFrame) {
                //拍摄返回的url
                String path = FileUtil.saveBitmap("huida", firstFrame);

                //跳转到发表页面  finish
                Intent intent = new Intent(CameraViewActivity.this, ReleaseActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("style",2);//2为视频
                intent.putExtra("photo",path);
                startActivity(intent);
                finish();
            }
        });
        jcv.setLeftClickListener(new ClickListener() {
            @Override
            public void onClick() {
                CameraViewActivity.this.finish();
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void processClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        jcv.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jcv.onResume();
    }
}
