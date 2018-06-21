package com.example.administrator.wms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.TransferAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/19 10:06
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class TakeOrderFragment extends Fragment implements View.OnClickListener {
    private View         mRootView;
    private RecyclerView rec_view;
    private Button       bt_scan;
    private int REQUEST_CODE = 1002;//接收扫描结果

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_take_order, null);
        initView();
        initData();
        initListener();
        return mRootView;
    }

    private void initView() {
        rec_view = mRootView.findViewById(R.id.rec_view);
        bt_scan = mRootView.findViewById(R.id.bt_scan);
    }

    private void initData() {
        final List mData = new ArrayList();
        mData.add("物料代码");
        mData.add("名称");
        mData.add("规格");
        mData.add("数量");
        mData.add("单位");
        mData.add("调入仓库");
        mData.add("调出仓库");
        mData.add("10.1002.220220");
        for (int i = 0; i < 50; i++) {
            mData.add("lol" + (i) * 10);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 8);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (position % 7 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rec_view.setLayoutManager(gridLayoutManager);
        TransferAdapter adapter = new TransferAdapter(getContext(), mData);
        rec_view.setAdapter(adapter);
    }

    private void initListener() {
        bt_scan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_scan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //获取调拨单id信息，弹出dailog展示，在新的页面确定后添加到表格中
                    // sendGoodsInfo(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
