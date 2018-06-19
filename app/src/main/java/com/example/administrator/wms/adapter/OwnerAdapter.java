package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.Owner;

import java.util.List;

/**
 * Created by Administrator on 2018/2/27 0027.
 */

public class OwnerAdapter extends BaseAdapter {
    private Context mContext;
    private List<Owner> datalist;
    private LayoutInflater inflater;

    public OwnerAdapter(Context context,List<Owner> list){
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
            view = inflater.inflate(R.layout.item_owner,null);
            holder = new Holder();
            holder.tv_id = (TextView)view
                    .findViewById(R.id.tv_id);
            holder.tv_name = (TextView)view
                    .findViewById(R.id.tv_name);
            view.setTag(holder);
        }else{
            holder = (Holder) view.getTag();
        }
        holder.tv_id.setText(datalist.get(i).getId());
        holder.tv_name.setText(datalist.get(i).getContact());
        return view;
    }

    class Holder{
        TextView tv_id,tv_name;
    }
}
