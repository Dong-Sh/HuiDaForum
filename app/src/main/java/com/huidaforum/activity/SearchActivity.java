package com.huidaforum.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chaychan.viewlib.PowerfulEditText;
import com.huidaforum.R;
import com.huidaforum.base.BaseActivity;
import com.huidaforum.bean.Bean;
import com.huidaforum.db.SearchNameDb;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gui on 2017/10/24.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.pet)
    PowerfulEditText pet;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.bt_clear)
    Button btClear;

    @BindView(R.id.pll_search)
    PercentLinearLayout pllSearch;
    @BindView(R.id.rlv_search)
    RecyclerView rlvSearch;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.rlv_find)
    RecyclerView rlvFind;
    @BindView(R.id.rlv_search_content)
    RecyclerView rlvSearchContent;
    @BindView(R.id.bt_quxiao)
    Button btQuxiao;
    private SearchNameDb helper;
    private SQLiteDatabase db;
    private ArrayList<Bean> list;
    private MyAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        helper = new SearchNameDb(SearchActivity.this);
        queryData("");
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String neirong = pet.getText().toString().trim();
                if (TextUtils.isEmpty(neirong)) {
                    Toast.makeText(SearchActivity.this, "请输入要搜索的帖子", Toast.LENGTH_SHORT).show();
                    return;
                }
                pllSearch.setVisibility(View.GONE);
                btSearch.setVisibility(View.GONE);
                rlvSearchContent.setVisibility(View.VISIBLE);
                btQuxiao.setVisibility(View.VISIBLE);

                Toast.makeText(SearchActivity.this, "搜索请求，隐藏其他", Toast.LENGTH_SHORT).show();


                boolean hasData = hasData(pet.getText().toString().trim());
                // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                if (!hasData) {
                    insertData(pet.getText().toString().trim());
                    queryData("");
                }
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                queryData("");
            }
        });
        btQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pllSearch.setVisibility(View.VISIBLE);
                btSearch.setVisibility(View.VISIBLE);
                btQuxiao.setVisibility(View.GONE);
                rlvSearchContent.setVisibility(View.GONE);
            }
        });
    }

    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    private boolean hasData(String tempName) {
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //  判断是否有下一个
        return cursor.moveToNext();
    }

    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        pllSearch.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        ArrayList<Bean> list = new ArrayList<>();
        list.add(new Bean("淘宝"));
        list.add(new Bean("天猫"));
        list.add(new Bean("京东"));
        list.add(new Bean("当当"));
        rlvFind.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
        adapter = new MyAdapter(R.layout.item_search, list);
        rlvFind.setAdapter(adapter);

    }

    private void queryData(String tempName) {
        list = new ArrayList<>();
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        while (cursor.moveToNext()) {
            int name = cursor.getColumnIndex("name");
            String string = cursor.getString(name);
            Bean bean = new Bean(string);
            list.add(bean);
        }
        if (cursor.getCount() == 0) {
            pllSearch.setVisibility(View.GONE);
        } else {
            pllSearch.setVisibility(View.VISIBLE);
        }
        MyAdapter myAdapter = new MyAdapter(R.layout.item_search, list);
        rlvSearch.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));
        rlvSearch.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv_search = (TextView) view.findViewById(R.id.tv_search);
                pet.setText(tv_search.getText().toString().trim());
            }
        });
    }

    @Override
    public void initListener() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv_search = (TextView) view.findViewById(R.id.tv_search);
                pet.setText(tv_search.getText().toString().trim());
        }
        });
    }

    @Override
    public void processClick(View v) {

    }

    class MyAdapter extends BaseQuickAdapter<Bean, BaseViewHolder> {

        public MyAdapter(@LayoutRes int layoutResId, @Nullable List<Bean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Bean item) {
            helper.setText(R.id.tv_search, item.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
