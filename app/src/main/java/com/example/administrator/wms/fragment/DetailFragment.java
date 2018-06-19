package com.example.administrator.wms.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.DetailAdapter;
import com.example.administrator.wms.entity.Detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class DetailFragment extends Fragment {
    private Context context;
    private View view;
    private ListView lv_fldetail;
    private DetailAdapter adapter;
    private List<Detail> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_fldetail,container,false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
        lv_fldetail = (ListView) view.findViewById(R.id.lv_fldetail);
        adapter = new DetailAdapter(context,list);
        lv_fldetail.setAdapter(adapter);
    }

    protected void setListeners(){

    }
}
