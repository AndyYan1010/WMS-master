package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.Detail;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class DetailAdapter extends BaseAdapter{
    private Context mContext;
    private List<Detail> list;
    private LayoutInflater inflater;

    public DetailAdapter(Context context,List<Detail> list){
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
        ViewHolder holder;
        if(view==null){
            view = inflater.inflate(R.layout.item_detail,null);
            holder = new ViewHolder();
            holder.tv_xuhao = (TextView) view
                    .findViewById(R.id.tv_xuhao);
            holder.tv_xiangci = (TextView) view
                    .findViewById(R.id.tv_xiangci);
            holder.tv_yf = (TextView) view
                    .findViewById(R.id.tv_yf);
            holder.tv_sf = (TextView) view
                    .findViewById(R.id.tv_sf);
            holder.tv_cy = (TextView) view
                    .findViewById(R.id.tv_cy);
            holder.tv_cpbm = (TextView) view
                    .findViewById(R.id.tv_cpbm);
            holder.tv_cpgg = (TextView) view
                    .findViewById(R.id.tv_cpgg);
            holder.tv_supplier = (TextView) view
                    .findViewById(R.id.tv_supplier);
            holder.tv_stock = (TextView) view
                    .findViewById(R.id.tv_stock);
            holder.tv_unit = (TextView) view
                    .findViewById(R.id.tv_unit);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        holder.tv_xuhao.setText(list.get(i).getFxuhao());
        holder.tv_xiangci.setText(list.get(i).getFxiangci());
        holder.tv_yf.setText(list.get(i).getFyingfa());
        holder.tv_sf.setText(list.get(i).getFshifa());
        holder.tv_cy.setText(list.get(i).getFchayi());
        holder.tv_cpbm.setText(list.get(i).getFcode());
        holder.tv_cpgg.setText(list.get(i).getFmodel());
        holder.tv_supplier.setText(list.get(i).getFsupplier());
        holder.tv_stock.setText(list.get(i).getFstock());
        holder.tv_unit.setText(list.get(i).getFunit());
        return view;
    }

    class ViewHolder{
        TextView tv_xuhao,tv_xiangci,tv_yf,tv_sf,tv_cy,tv_cpbm,tv_cpgg,tv_supplier,tv_stock,tv_unit;
    }
}
