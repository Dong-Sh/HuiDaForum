package com.huidaforum.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.base.BaseBean;
import com.huidaforum.bean.MinePersonalBean;
import com.huidaforum.utils.SpUtil;
import com.huidaforum.utils.StaticValue;
import com.huidaforum.utils.WebAddress;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by xiaojiu on 2017/9/9.
 * 个人详细信息
 */

public class MinePersonalActivity extends BaseActivity {
    private static final int CAMERA_OK = 100;
    private static final int EXETR_OK = 101;
    @BindView(R.id.rl_mine_personal_name)
    RelativeLayout rlMinePersonalName;
    @BindView(R.id.rl_mine_personal_sex)
    RelativeLayout rlMinePersonalSex;
    @BindView(R.id.rl_mine_personal_birthday)
    RelativeLayout rlMinePersonalBirthday;
    @BindView(R.id.rl_mine_personal_email)
    RelativeLayout rlMinePersonalEmail;
    @BindView(R.id.rl_mine_personal_school)
    RelativeLayout rlMinePersonalSchool;
    @BindView(R.id.iv_mine_personal_pic)
    CircleImageView ivMinePersonalPic;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.iv_mine_personal_name)
    ImageView ivMinePersonalName;
    @BindView(R.id.tv_personal_name)
    TextView tvPersonalName;
    @BindView(R.id.tv_personal_nikename)
    TextView tvPersonalNikename;
    @BindView(R.id.iv_mine_personal_sex)
    ImageView ivMinePersonalSex;
    @BindView(R.id.tv_personal_sex)
    TextView tvPersonalSex;
    @BindView(R.id.tv_person_sex)
    TextView tvPersonSex;
    @BindView(R.id.iv_mine_personal_birth)
    ImageView ivMinePersonalBirth;
    @BindView(R.id.tv_personal_birth)
    TextView tvPersonalBirth;
    @BindView(R.id.tv_person_birthday)
    TextView tvPersonBirthday;
    @BindView(R.id.iv_mine_personal_email)
    ImageView ivMinePersonalEmail;
    @BindView(R.id.tv_personal_email)
    TextView tvPersonalEmail;
    @BindView(R.id.tv_person_email)
    TextView tvPersonEmail;
    @BindView(R.id.iv_mine_personal_school)
    ImageView ivMinePersonalSchool;
    @BindView(R.id.tv_personal_school)
    TextView tvPersonalSchool;
    @BindView(R.id.tv_person_schoolname)
    TextView tvPersonSchoolname;
    private AlertDialog.Builder dialog;
    private String s;
    private int sexFlag = 1;
    private static final int CHOOSE_BIG_PICTURE = 0;
    private static final int CHOOSE_COOP = 3;
    private Bitmap photo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_personal;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        OkGo.<String>post(WebAddress.listYwUserDetailInfo)
                .params("devType", "phone")
                .params("token", SpUtil.getString(StaticValue.TOKEN, this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        String body = response.body();
                        String s = body.replaceAll("\\\\", "//");
                        BaseBean<List<MinePersonalBean>> minePersonalBean = gson.fromJson(s,
                                new TypeToken<BaseBean<List<MinePersonalBean>>>() {
                                }.getType());
                        if (minePersonalBean.isSuccess()) {
                            List<MinePersonalBean> data = minePersonalBean.getData();
                            parseData(data);
                        }
                    }
                });
    }

    private void parseData(List<MinePersonalBean> data) {
        MinePersonalBean minePersonalData = data.get(0);
        if (!TextUtils.isEmpty(minePersonalData.getNickName())) {
            tvPersonalNikename.setText(minePersonalData.getNickName());
        }else {
            tvPersonalNikename.setText("请设置昵称");
        }
        if (!TextUtils.isEmpty(minePersonalData.getSex())) {
            tvPersonSex.setText(minePersonalData.getSex());
            if (minePersonalData.getSex().equals("男")){
                sexFlag = 0;
            }else{
                sexFlag = 1;
            }
        }else {
            tvPersonSex.setText("请设置性别");
        }
        if (!TextUtils.isEmpty(minePersonalData.getBirthday())) {
            tvPersonBirthday.setText(minePersonalData.getBirthday());
        }else {
            tvPersonBirthday.setText("请设置出生日期");
        }
        if (!minePersonalData.getEmailRenzheng().equals("-1")) {
            tvPersonEmail.setText(minePersonalData.getEmailRenzheng());
        }else {
            tvPersonEmail.setText("请设置邮箱");
        }
        if (!TextUtils.isEmpty(minePersonalData.getSchool())) {
            tvPersonSchoolname.setText(minePersonalData.getSchool());
        }else {
            tvPersonSchoolname.setText("请设置院校");
        }
    }

    @Override
    public void initListener() {
        ivMinePersonalPic.setOnClickListener(this);
        rlMinePersonalName.setOnClickListener(this);
        rlMinePersonalSex.setOnClickListener(this);
        rlMinePersonalBirthday.setOnClickListener(this);
        rlMinePersonalEmail.setOnClickListener(this);
        rlMinePersonalSchool.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mine_personal_pic:
                UpdateHeadPhoto();
                break;
            case R.id.rl_mine_personal_name:
                showUpdateNameDialog();
                break;
            case R.id.rl_mine_personal_sex:
                showUpdateSexDialog();
                break;
            case R.id.rl_mine_personal_birthday:
                showUpdateBirthDialog();
                break;
            case R.id.rl_mine_personal_email:
                showUpdateEmailDialog();
                break;
            case R.id.rl_mine_personal_school:
                showUpdateSchoolDialog();
                break;
        }
    }

    private void UpdateHeadPhoto() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("设置头像");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.headphoto_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        TextView tv_takephoto = (TextView) view.findViewById(R.id.tv_takephoto);
        TextView tv_gallery = (TextView) view.findViewById(R.id.tv_gallery);
        tv_takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //Andorid 6.0动态获取权限
                if(Build.VERSION.SDK_INT>22){
                    if(ContextCompat.checkSelfPermission(MinePersonalActivity.this,
                            android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(MinePersonalActivity.this,
                                    Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED
                            ||ContextCompat.checkSelfPermission(MinePersonalActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(MinePersonalActivity.this,
                                new String[]{android.Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},CAMERA_OK);
                    }else{
                        //调用系统相机
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        //下面这句指定调用相机拍照后的照片存储的路径
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                                .fromFile(new File(WebAddress.IconPath+File.separator+"temp.jpg")));
                        startActivityForResult(intent, 2);
                    }
                }else{
                    //调用系统相机
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(WebAddress.IconPath+File.separator+"temp.jpg")));
                    startActivityForResult(intent, 2);
                }

            }
        });
        tv_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //调用系统相册
                if(Build.VERSION.SDK_INT>22) {
                    if (ContextCompat.checkSelfPermission(MinePersonalActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                            || ContextCompat.checkSelfPermission(MinePersonalActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MinePersonalActivity.this,
                                new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, EXETR_OK);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");// 相片类型
                        startActivityForResult(intent, CHOOSE_BIG_PICTURE);
                    }
                }else{
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent, CHOOSE_BIG_PICTURE);
                }
            }
        });
        alertDialog.show();
    }

    private void showUpdateSchoolDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请输入学校名称");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.schoolname_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final EditText schoolName = (EditText) view.findViewById(R.id.et_schoolname);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String school = schoolName.getText().toString().trim();
                TextView tv_person_schoolname = (TextView) findViewById(R.id.tv_person_schoolname);
                if (!school.toString().isEmpty()) {
                    tv_person_schoolname.setText(school);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }


    private void showUpdateBirthDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请选择出生日期");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.date_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final AlertDialog alertDialog = dialog.create();
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.dp_date);
        TextView tv_picker_finish = (TextView) view.findViewById(R.id.tv_picker_finish);
        final TextView birth = (TextView) findViewById(R.id.tv_person_birthday);
        tv_picker_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                String date = year + "-" + (month >= 9 ? month + 1 : "0" + (month + 1)) + "-" + (day > 9 ? day : "0" + day);
                //Toast.makeText(MinePersonalActivity.this, "birth" + date, Toast.LENGTH_SHORT).show();
                if (!date.toString().isEmpty()) {
                    birth.setText(date);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void showUpdateEmailDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请输入邮箱");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.email_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final EditText email = (EditText) view.findViewById(R.id.et_email);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String personEmail = email.getText().toString().trim();
                TextView tv_personal_nikename = (TextView) findViewById(R.id.tv_person_email);
                if (!personEmail.toString().isEmpty()) {
                    tv_personal_nikename.setText(personEmail);
                }

            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }

    private void showUpdateSexDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请选择性别");
        final String[] sex = {"男", "女"};
        Log.e("jdr",""+sexFlag);
        dialog.setSingleChoiceItems(sex, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                s = sex[which];
                sexFlag = which;
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(MinePersonalActivity.this, "性别为：" + s, Toast.LENGTH_SHORT).show();
                TextView sex = (TextView) findViewById(R.id.tv_person_sex);
                if (!s.toString().isEmpty()) {
                    sex.setText(s);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

    private void showUpdateNameDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请输入昵称");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.nikename_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final EditText username = (EditText) view.findViewById(R.id.et_username);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = username.getText().toString().trim();
                TextView tv_personal_nikename = (TextView) findViewById(R.id.tv_personal_nikename);
                if (!name.toString().isEmpty()) {
                    tv_personal_nikename.setText(name);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CHOOSE_COOP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_BIG_PICTURE:
                if (data!=null)
                startPhotoZoom(data.getData());
                break;
            case CHOOSE_COOP:
                if(data != null){
                    setPicToView(data);
                }
                break;
            case 2:
                if(resultCode==RESULT_OK){
                    File temp = new File(WebAddress.IconPath + File.separator + "temp.jpg");
                    startPhotoZoom(Uri.fromFile(temp));
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_OK:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                            .fromFile(new File(WebAddress.IconPath+File.separator+"temp.jpg")));
                    startActivityForResult(intent, 2);
                }else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                    Toast.makeText(this,"请手动打开相机权限",Toast.LENGTH_SHORT).show();
                }
                break;
            case EXETR_OK:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent1, CHOOSE_BIG_PICTURE);
                }else {
                    Toast.makeText(this,"请手动打开内存卡读取权限",Toast.LENGTH_SHORT).show();
                }

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            ivMinePersonalPic.setImageBitmap(photo);
            File file = new File(WebAddress.IconPath);
            if (!file.exists()){
                file.mkdirs();
            }
            String picname = new DateFormat().format("yyyyMMdd_hhmmss",
                    Calendar.getInstance(Locale.CHINA))
                    + ".jpg";
            String fileName = file.getPath()+File.separator + picname;//在指定目录下创建文件picname

            FileOutputStream b = null;
            try {
                b = new FileOutputStream(fileName);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
