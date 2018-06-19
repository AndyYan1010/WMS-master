package com.example.administrator.wms.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wms.R;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/19 10:06
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class TakeOrderFragment extends Fragment {
    private View mRootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_all_order, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {

    }

    private void initData() {

    }
}
