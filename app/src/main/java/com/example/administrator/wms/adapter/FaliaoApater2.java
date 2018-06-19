package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.Faliao;
import com.example.administrator.wms.entity.Faliao2;

import java.util.List;

/**
 * Created by Administrator on 2017-03-20.
 */
public class FaliaoApater2 extends BaseAdapter {

    private Context mContext = null;
    private List<Faliao2> dataList;

    private LayoutInflater inflater;

    public FaliaoApater2(Context context, List<Faliao2> dataList) {
        this.mContext = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {

            view = inflater.inflate(R.layout.item_faliao2, null);
            holder = new Holder();
            holder.tv_wlid = (TextView)view
                    .findViewById(R.id.tv_wlid);
            holder.tv_wname = (TextView)view
                    .findViewById(R.id.tv_wname);
            holder.tv_number = (TextView) view
                    .findViewById(R.id.tv_number);
            holder.tv_danwei = (TextView) view
                    .findViewById(R.id.tv_danwei);
            holder.tv_kuweis = (TextView) view
                    .findViewById(R.id.tv_kuweis);
            holder.tv_supp = (TextView) view
                    .findViewById(R.id.tv_supp);
            holder.tv_cangku = (TextView) view
                    .findViewById(R.id.tv_cangku);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.tv_wlid.setText(dataList.get(i).getFitemid());
        holder.tv_wname.setText(dataList.get(i).getFitemname());
        holder.tv_number.setText(dataList.get(i).getFnumber());
        holder.tv_danwei.setText(dataList.get(i).getFunit());
        holder.tv_kuweis.setText(dataList.get(i).getFkuwei());
        holder.tv_supp.setText(dataList.get(i).getFsupplier());
        holder.tv_cangku.setText(dataList.get(i).getFstock());
        return view;
    }

    private class Holder {
        TextView tv_wlid,tv_wname,tv_number,tv_danwei,tv_kuweis,tv_supp,tv_cangku;
    }

}
