package com.huidaforum.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gui on 2017/10/17.
 */

public class FullscreenActivity extends BaseActivity {
    @BindView(R.id.vv)
    VideoView vv;
    private String url;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             vv.start();
            sendEmptyMessage(0);
        }
    };
    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_fullscreen;

    }

    @Override
    public void initView() {
        url = getIntent().getStringExtra("url");
          if(url==null){
               return;
          }

    }

    @Override
    protected void onStart() {
        super.onStart();
        vv.setVideoPath(url);
        vv.start();
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                 handler.sendEmptyMessage(0);

            }
        });
    }

    @Override
    public void initData() {
       vv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FullscreenActivity.this.finish();
           }
       });
        vv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                 finish();
                return true;
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            vv.pause();
                finish();

        }
        return super.onKeyDown(keyCode, event);
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
}
