package com.example.administrator.wms.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.TransferAdapter;

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

public class TakeOrderFragment extends Fragment {
    private View         mRootView;
    private RecyclerView rec_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_take_order, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        rec_view = mRootView.findViewById(R.id.rec_view);
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
}
