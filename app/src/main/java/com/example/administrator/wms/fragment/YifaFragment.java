package com.example.administrator.wms.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.YifaAdapter;
import com.example.administrator.wms.entity.Faliao2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class YifaFragment extends Fragment{
    private Context context;
    private View view;
    private ListView lv_yfdetail;
    private List<Faliao2> list = new ArrayList<>();
    private YifaAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        view = inflater.inflate(R.layout.fragment_yfdetail,container,false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
        lv_yfdetail = (ListView) view.findViewById(R.id.lv_yfdetail);
        list = new ArrayList<>();
        adapter = new YifaAdapter(context,list);
        lv_yfdetail.setAdapter(adapter);
    }

    protected void setListeners(){

    }
}
