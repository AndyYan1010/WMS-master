package com.example.administrator.wms.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;

import java.util.List;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/21 9:17
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class LvOrderNumAdapter extends BaseAdapter {
    private Context      mContext;
    private List<String> mList;

    public LvOrderNumAdapter(Context context, List list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (null == view) {
            view = View.inflate(mContext, R.layout.lv_item_order, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.tv_num = view.findViewById(R.id.tv_num);
            view.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }
        myViewHolder.tv_num.setText(mList.get(i));
        return view;
    }

    private class MyViewHolder {
        TextView tv_num;
    }
}
