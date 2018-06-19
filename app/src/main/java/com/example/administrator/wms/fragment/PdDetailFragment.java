package com.example.administrator.wms.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.PdDetailAdapter;
import com.example.administrator.wms.entity.PdDetail;

import java.util.ArrayList;
import java.util.List;


public class PdDetailFragment extends Fragment {
    Context mContext;
    View view;
    ListView lv_pd_detail;
    List<PdDetail> list;
    PdDetailAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        view = inflater.inflate(R.layout.fragment_pd_detail, container, false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
        lv_pd_detail = (ListView) view.findViewById(R.id.lv_pd_detail);
        list = new ArrayList<>();
        adapter = new PdDetailAdapter(mContext,list);
        lv_pd_detail.setAdapter(adapter);
    }

    protected void setListeners(){

    }
}
