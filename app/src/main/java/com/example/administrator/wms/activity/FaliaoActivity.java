package com.example.administrator.wms.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.MyPagerAdapter;
import com.example.administrator.wms.fragment.AllocationOrderFragment;
import com.example.administrator.wms.fragment.TakeOrderFragment;
import com.example.administrator.wms.view.MyFixedViewpager;

import java.util.ArrayList;

public class FaliaoActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar          toolbar;
    //    private TextView tv_gongji;
    //    private ListView lv_fl;
    //    private FaliaoApater adapter;
    //    private List<Faliao> list = new ArrayList<>();
    private ImageView        img_back;
    private TextView         tv_title;
    private TabLayout        mTablayout;//导航标签
    private MyFixedViewpager mView_pager;//自我viewpager可实现禁止滑动
    private String[] mStrings = {"调拨单", "已取单"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faliao);
        //        setTool();
        setViews();
        setData();
        setListeners();
    }

    protected void setTool() {
        //        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        //        toolbar.setTitle(R.string.flkd);
        //
        //        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //        setSupportActionBar(toolbar);
        //
        //        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        //        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                finish();
        //            }
        //        });
        //
        //        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
        //            @Override
        //            public boolean onMenuItemClick(MenuItem item) {
        //                switch (item.getItemId()) {
        //                    case R.id.action_sm:
        //
        //                        break;
        //                }
        //                return true;
        //            }
        //        });
    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        getMenuInflater().inflate(R.menu.menu, menu);//加载menu文件到布局
    //        return true;
    //    }

    protected void setViews() {
        //        tv_gongji = (TextView) findViewById(R.id.tv_gongji);
        //        lv_fl = (ListView) findViewById(R.id.lv_fl);
        //        Faliao fl = new Faliao();
        //        fl.setFfaliaono("qweyqgdsdgajksgdj");
        //        fl.setFgongdanno("ajsdhasjkdhjkasdh");
        //        fl.setFnote("空间哈尽快的哈数据库的哈");
        //        list.add(fl);
        //        adapter = new FaliaoApater(this,list);
        //        lv_fl.setAdapter(adapter);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        mTablayout = findViewById(R.id.tablayout);
        mView_pager = findViewById(R.id.view_pager);
    }

    private void setData() {
        img_back.setOnClickListener(this);
        tv_title.setText("发料开单");
        initTabFragment();
    }

    private void initTabFragment() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        //需求调拨界面
        AllocationOrderFragment AllOrderFragment = new AllocationOrderFragment();
        fragments.add(AllOrderFragment);
        //取料界面
        TakeOrderFragment takeOrderFragment = new TakeOrderFragment();
        fragments.add(takeOrderFragment);

        // 创建ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        mView_pager.setAdapter(myPagerAdapter);
        //设置viewpager不可滑动
//        mView_pager.setCanScroll(false);
        //tablayout关联tablayout和viewpager实现联动
        mTablayout.setupWithViewPager(mView_pager);
        for (int i = 0; i < mStrings.length; i++) {
            mTablayout.getTabAt(i).setText(mStrings[i]);
        }
    }

    protected void setListeners() {
        //        lv_fl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //                startActivity(new Intent());
        //            }
        //        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
