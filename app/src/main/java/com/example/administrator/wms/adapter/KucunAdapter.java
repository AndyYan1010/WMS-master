package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.wms.entity.Kucun;

import java.util.List;

/**
 * Created by Administrator on 2017/12/11 0011.
 */

public class KucunAdapter extends BaseAdapter{
    private Context mContext;
    private List<Kucun> list;
    private LayoutInflater inflater;

    public KucunAdapter(Context context,List<Kucun> list){
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
