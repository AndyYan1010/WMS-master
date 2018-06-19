package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.AsnDetail;
import java.util.List;

/**
 * Created by Administrator on 2018/2/24 0024.
 */

public class SldAdapter extends BaseAdapter{
    Context mContext;
    List<AsnDetail> datalist;
    LayoutInflater inflater;

    public SldAdapter(Context context,List<AsnDetail> list){
        this.mContext = context;
        this.datalist = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view==null){
            view = inflater.inflate(R.layout.item_sld,null);
            holder = new Holder();
            holder.tv_wlmc = (TextView) view
                    .findViewById(R.id.tv_wlmc);
            holder.tv_sup = (TextView) view
                    .findViewById(R.id.tv_sup);
            holder.tv_ddsl = (TextView) view
                    .findViewById(R.id.tv_ddsl);
            view.setTag(holder);
        }else{
            holder = (Holder)view.getTag();
        }
        holder.tv_wlmc.setText(datalist.get(i).getFitemname());
        holder.tv_sup.setText(datalist.get(i).getFsupplier());
        holder.tv_ddsl.setText(datalist.get(i).getFqty());
        return view;
    }

    class Holder{
        TextView tv_wlmc,tv_sup,tv_ddsl;
    }
}
