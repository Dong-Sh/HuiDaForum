package com.huidaforum.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.adapter.GridViewAdapter;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.StringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.huidaforum.R.id.release_send;
import static com.huidaforum.utils.WebAddress.saveContent;
import static com.huidaforum.utils.WebAddress.saveContentDraft;
//发布页面

public class ReleaseActivity extends BaseActivity {


    @BindView(R.id.gv_photo)
    GridView gvPhoto;
    @BindView(release_send)
    Button releaseSend;
    @BindView(R.id.release_back)
    Button releaseBack;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.ib_bofang)
    ImageButton ibBofang;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    private ArrayList<String> mPicList = new ArrayList<>();
    private GridViewAdapter gridViewAdapter;
    private ArrayList<File> files = null;
    private String url;
    private boolean isback = true;//是否可以退出  TRUE=可以退出  FALSE=不可以退出

    @Override
    public int getLayoutId() {

        return R.layout.activity_release;
    }

    @Override
    public void initView() {
        releaseBack.setOnClickListener(this);
        releaseSend.setOnClickListener(this);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        url = getIntent().getStringExtra("url");
        String photo = getIntent().getStringExtra("photo");//视频的图片
        int style = getIntent().getIntExtra("style", 0);
        switch (style) {
            case 1://拍摄的为图片 urlz
                mPicList.add(url);
                takePhoto();
                break;
            case 2://拍摄的视频
                ibBofang.setVisibility(View.VISIBLE);
                Glide.with(ReleaseActivity.this).load(photo).into(ibBofang);
                ibBofang.setImageResource(R.mipmap.bofang);
                ibBofang.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ibBofang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ReleaseActivity.this, FullscreenActivity.class);
                        intent.putExtra("url", url);
                        startActivity(intent);
                    }
                });
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
            case 5:

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
        switch (v.getId()) {
            case R.id.release_back:
                back();
                break;
            case release_send:
                send();
                break;
        }
    }

    private void send() {
        String title = etTitle.getText().toString().trim();
        String text = etText.getText().toString().trim();
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(text)) {
            Toast.makeText(this, "标题或正文不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else {
            isback = false;
            tvRelease.setVisibility(View.VISIBLE);
            releaseSend.setVisibility(View.GONE);
        }
        urlToFile();
        OkGo.<String>post(saveContent).tag(this)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .params("contentState", "publish")
                .params("title", title)
                .params("contentText", text)
                .addFileParams("file", files)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {//网络连接成功
                        Gson gson = new Gson();
                        BaseBean baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean>() {
                        }.getType());
                        if (baseBean.isSuccess()) {
                            Toast.makeText(ReleaseActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ReleaseActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
                            releaseSend.setEnabled(true);
                            isback = true;
                            tvRelease.setText("正在发布中");
                            tvRelease.setVisibility(View.GONE);
                            releaseSend.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    private void urlToFile() {
        files = new ArrayList<>();
        if (mPicList.size() == 0) {
            if (!TextUtils.isEmpty(url)) {
                files.add(new File(url));
            }
        } else {
            for (String picUrl : mPicList) {
                files.add(new File(picUrl));
            }
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isback) {
            Toast.makeText(this, "正在发布文章或保存至草稿箱无法退出", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void back() {

        if (!isback) {
            Toast.makeText(this, "正在发布文章或保存至草稿箱无法退出", Toast.LENGTH_SHORT).show();
            return;
        }
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
                String title = etTitle.getText().toString().trim();
                String text = etText.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(text)) {
                    Toast.makeText(ReleaseActivity.this, "标题或正文不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    isback = false;
                    tvRelease.setText("正在保存中");
                    tvRelease.setVisibility(View.VISIBLE);
                    releaseSend.setVisibility(View.GONE);
                }
                urlToFile();
                OkGo.<String>post(saveContentDraft).tag(this)
                        .params("devType", "phone")
                        .params("token", SpUtil.getString(StaticValue.TOKEN, ReleaseActivity.this))
                        .params("contentState", "draft")
                        .params("title", title)
                        .params("contentText", text)
                        .addFileParams("file", files)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {//网络连接成功
                                Gson gson = new Gson();
                                BaseBean baseBean = gson.fromJson(StringUtil.getReviseResponseBody(response.body()), new TypeToken<BaseBean>() {
                                }.getType());
                                if (baseBean.isSuccess()) {
                                    Toast.makeText(ReleaseActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ReleaseActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                                    isback = true;
                                    tvRelease.setText("正在发布中");
                                    tvRelease.setVisibility(View.GONE);
                                    releaseSend.setVisibility(View.VISIBLE);
                                }
                            }
                        });

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

        }
        if (requestCode == 10 && resultCode == 11) {
            ArrayList<String> list = data.getStringArrayListExtra("list");
            mPicList.clear();
            mPicList.addAll(list);
            gridViewAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void takePhoto() {
        gvPhoto.setVisibility(View.VISIBLE);
        if (gridViewAdapter == null) {
            gridViewAdapter = new GridViewAdapter(ReleaseActivity.this, mPicList);
            gvPhoto.setAdapter(gridViewAdapter);
        }

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
