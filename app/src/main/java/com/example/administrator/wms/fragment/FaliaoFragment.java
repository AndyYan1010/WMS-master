package com.example.administrator.wms.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.FaliaoApater2;
import com.example.administrator.wms.entity.Faliao;
import com.example.administrator.wms.entity.Faliao2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class FaliaoFragment extends Fragment{
    private View view;
    private Context context;
    private ListView lv_tjpc;
    private FaliaoApater2 adapter;
    private List<Faliao2> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_fl, container, false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
       lv_tjpc = (ListView) view.findViewById(R.id.lv_tjpc);
       adapter = new FaliaoApater2(context,list);
       lv_tjpc.setAdapter(adapter);
    }

    protected void setListeners(){

    }
}
