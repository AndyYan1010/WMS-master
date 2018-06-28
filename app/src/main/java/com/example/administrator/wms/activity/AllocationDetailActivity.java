package com.example.administrator.wms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wms.BaseActivity;
import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.MyPagerAdapter;
import com.example.administrator.wms.fragment.AllocationOrderFragment;
import com.example.administrator.wms.fragment.TakeOrderFragment;
import com.example.administrator.wms.messageInfo.GoodsInfo;
import com.example.administrator.wms.view.MyFixedViewpager;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/21 9:07
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AllocationDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView        img_back;
    private TextView         tv_title;
    private TabLayout        mTablayout;//导航标签
    private MyFixedViewpager mView_pager;//自我viewpager可实现禁止滑动
    private String[] mStrings = {"调拨单", "取料单"};
    private String          orderID;//前面传过来的调拨单ID
    private List<GoodsInfo> mGoodsInfoList;//存放需查找的物品信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faliao_detail);
        Intent intent = getIntent();
        orderID = intent.getStringExtra("orderID");
        setViews();
        setData();
    }

    private void setViews() {
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        mTablayout = findViewById(R.id.tablayout);
        mView_pager = findViewById(R.id.view_pager);
    }

    private void setData() {
        img_back.setOnClickListener(this);
        tv_title.setText("发料开单");
        mGoodsInfoList = new ArrayList();
        initTabFragment();
    }

    private void initTabFragment() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        //需求调拨界面
        AllocationOrderFragment allOrderFragment = new AllocationOrderFragment();
        allOrderFragment.setOrderID(orderID);
        fragments.add(allOrderFragment);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    //获取本act中存放物品信息的list
    public List getInfoList() {
        return mGoodsInfoList;
    }

    public String getOrderID() {
        return orderID;
    }
}
