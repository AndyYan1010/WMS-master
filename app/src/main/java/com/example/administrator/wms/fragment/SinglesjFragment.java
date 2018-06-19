package com.example.administrator.wms.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.ShangjiaAdapter;
import com.example.administrator.wms.entity.AsnDetail;

import java.util.List;


public class SinglesjFragment extends Fragment {
    Context mContext;
    View view;
    TextView tv_hjbh;
    ListView lv_shangjia;
    ShangjiaAdapter adapter;
    List<AsnDetail> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        view = inflater.inflate(R.layout.fragment_singlesj, container, false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
        tv_hjbh = (TextView)view.findViewById(R.id.tv_hjbh);
        lv_shangjia = (ListView)view.findViewById(R.id.lv_shangjia);
    }

    protected void setListeners(){
        tv_hjbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(mContext);
                new AlertDialog.Builder(mContext).setTitle("请输入货架号").setIcon(
                        R.drawable.danhao).setView(
                        et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_hjbh.setText(et.getText().toString());
                        //查询收料单详情

                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });

        lv_shangjia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
