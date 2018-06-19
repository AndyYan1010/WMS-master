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
 * Created by Administrator on 2018/2/26 0026.
 */

public class ShangjiaAdapter extends BaseAdapter{
    Context mContext;
    List<AsnDetail> datalist;
    LayoutInflater inflater;

    public ShangjiaAdapter(Context context,List<AsnDetail> list){
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
            view = inflater.inflate(R.layout.item_shangjia,null);
            holder = new Holder();
            holder.tv_wlno = (TextView)view
                    .findViewById(R.id.tv_wlno);
            holder.tv_pinming = (TextView)view
                    .findViewById(R.id.tv_pinming);
            holder.tv_no = (TextView)view
                    .findViewById(R.id.tv_no);
            holder.tv_dw = (TextView)view
                    .findViewById(R.id.tv_dw);
            view.setTag(holder);
        }else{
            holder = (Holder)view.getTag();
        }
        holder.tv_wlno.setText(datalist.get(i).getFitemid());
        holder.tv_pinming.setText(datalist.get(i).getFitemname());
        holder.tv_no.setText(datalist.get(i).getFqty());
        holder.tv_dw.setText(datalist.get(i).getFunit());
        return view;
    }

    class Holder{
        TextView tv_wlno,tv_pinming,tv_no,tv_dw;
    }
}
