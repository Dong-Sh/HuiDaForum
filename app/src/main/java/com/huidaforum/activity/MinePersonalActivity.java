package com.huidaforum.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import java.util.List;

import butterknife.BindView;

/**
 * Created by xiaojiu on 2017/9/9.
 * 个人详细信息
 */

public class MinePersonalActivity extends BaseActivity {
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
    ImageView ivMinePersonalPic;
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
       /* if (!TextUtils.isEmpty(minePersonalData.getEmailRenzheng())) {
            tvPersonalNikename.setText(minePersonalData.getEmailRenzheng());
        }else {
            tvPersonalNikename.setText("请设置昵称");
        }*/
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
                //调用系统相机


            }
        });
        tv_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                //调用系统相册
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");// 相片类型
                startActivityForResult(intent, CHOOSE_BIG_PICTURE);
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
}
