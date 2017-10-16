package com.huidaforum.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private AlertDialog.Builder dialog;
    private String s;

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

    }

    @Override
    public void initListener() {
        rlMinePersonalName.setOnClickListener(this);
        rlMinePersonalSex.setOnClickListener(this);
        rlMinePersonalBirthday.setOnClickListener(this);
        rlMinePersonalEmail.setOnClickListener(this);
        rlMinePersonalSchool.setOnClickListener(this);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()){
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

    private void showUpdateSchoolDialog() {
        dialog = new AlertDialog.Builder(MinePersonalActivity.this);
        dialog.setTitle("请输入学校名称");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(MinePersonalActivity.this).inflate(R.layout.schoolname_dialog, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        dialog.setView(view);
        final EditText schoolName = (EditText)view.findViewById(R.id.et_schoolname);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String school = schoolName.getText().toString().trim();
                TextView tv_person_schoolname = (TextView) findViewById(R.id.tv_person_schoolname);
                if (!school.toString().isEmpty()) {
                    tv_person_schoolname.setText(school);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

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
                Toast.makeText(MinePersonalActivity.this, "birth"+date, Toast.LENGTH_SHORT).show();
                if (!date.toString().isEmpty()){
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
        final EditText email = (EditText)view.findViewById(R.id.et_email);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String personEmail= email.getText().toString().trim();
                TextView tv_personal_nikename = (TextView) findViewById(R.id.tv_person_email);
                if (!personEmail.toString().isEmpty()){
                    tv_personal_nikename.setText(personEmail);
                }

            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

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
        dialog.setSingleChoiceItems(sex, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                s = sex[which];
            }
        });
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MinePersonalActivity.this, "性别为：" + s, Toast.LENGTH_SHORT).show();
                TextView sex = (TextView) findViewById(R.id.tv_person_sex);
                if (!s.toString().isEmpty()) {
                    sex.setText(s);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

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
        final EditText username = (EditText)view.findViewById(R.id.et_username);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String name = username.getText().toString().trim();
                TextView tv_personal_nikename = (TextView) findViewById(R.id.tv_personal_nikename);
                if (!name.toString().isEmpty()) {
                    tv_personal_nikename.setText(name);
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
