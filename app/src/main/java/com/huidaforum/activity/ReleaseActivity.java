package com.huidaforum.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.huidaforum.R;
import com.huidaforum.adapter.GridViewAdapter;
import com.huidaforum.base.BaseActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//发布页面

public class ReleaseActivity extends BaseActivity {


    @BindView(R.id.gv_photo)
    GridView gvPhoto;
    @BindView(R.id.release_send)
    Button releaseSend;
    @BindView(R.id.release_back)
    Button releaseBack;
    private ArrayList<String> mPicList = new ArrayList<>();
    private GridViewAdapter gridViewAdapter;

    @Override
    public int getLayoutId() {

        return R.layout.activity_release;
    }

    @Override
    public void initView() {
        releaseBack.setOnClickListener(this);
        releaseSend.setOnClickListener(this);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        String url = getIntent().getStringExtra("url");
        Bitmap photo = getIntent().getParcelableExtra("photo");//视频的图片
        int style = getIntent().getIntExtra("style", 0);
        switch (style) {
            case 1://拍摄的为图片 url
                mPicList.add(url);
                takePhoto();
                break;
            case 2://拍摄的视频

                break;
            case 3://文字可选图片
                takePhoto();
                break;
            case 4://图片
                for (String path : list) {
                    mPicList.add(path);
                }
                takePhoto();
                break;
        }
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
                  case R.id.release_back:
                      back();
                      break;
                  case R.id.release_send:
                      send();
                      break;
              }
    }

    private void send() {
        Toast.makeText(this, "发布", Toast.LENGTH_SHORT).show();
    }

    private void back() {
        new AlertDialog.Builder(ReleaseActivity.this)
                .setCancelable(false)
                .setMessage("是否保存至我的草稿")
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                }).setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ReleaseActivity.this, "保存", Toast.LENGTH_SHORT).show();

            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 200:
                    List<Uri> list = Matisse.obtainResult(data);
                    for (int i = 0; i < list.size(); i++) {
                        String path = getRealFilePath(ReleaseActivity.this, list.get(i));
                        mPicList.add(path);
                        gridViewAdapter.notifyDataSetChanged();
                    }
                    break;

            }
            if (requestCode == 0 && resultCode == 11) {
                ArrayList<String> list = data.getStringArrayListExtra("list");
                mPicList.clear();
                mPicList.addAll(list);
                gridViewAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
          if (keyCode==KeyEvent.KEYCODE_BACK){
               back();
          }
        return super.onKeyDown(keyCode, event);
    }

    private void takePhoto() {
        gvPhoto.setVisibility(View.VISIBLE);
        gridViewAdapter = new GridViewAdapter(ReleaseActivity.this, mPicList);
        gvPhoto.setAdapter(gridViewAdapter);
        gvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    if (mPicList.size() == 6) {
                        seeLargeImage(position);
                    } else {
                        //添加图片
                        Matisse.from(ReleaseActivity.this)
                                .choose(MimeType.allOf())
                                .countable(true)
                                .maxSelectable(6 - mPicList.size())
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f) // 缩略图的比例
                                .theme(R.style.Matisse_Dracula)
                                .imageEngine(new PicassoEngine()) // 使用的图片加载引擎
                                .forResult(200);

                    }
                } else {

                    seeLargeImage(position);

                }
            }
        });

    }

    private void seeLargeImage(int position) {
        Intent intent = new Intent(ReleaseActivity.this, PlusImageActivity.class);
        intent.putStringArrayListExtra("list", mPicList);
        intent.putExtra("position", position);
        startActivityForResult(intent, 10);
    }


    public String getRealFilePath(Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
