package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.Faliao2;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class YifaAdapter extends BaseAdapter {
    private Context mContext;
    private List<Faliao2> list;
    private LayoutInflater inflater;

    public YifaAdapter(Context context,List<Faliao2> list){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view = inflater.inflate(R.layout.item_yifa,null);
            holder = new ViewHolder();
            holder.tv_pch = (TextView) view
                    .findViewById(R.id.tv_pch);
            holder.tv_sl = (TextView) view
                    .findViewById(R.id.tv_sl);
            holder.tv_dw = (TextView) view
                    .findViewById(R.id.tv_dw);
            holder.tv_wlbm = (TextView) view
                    .findViewById(R.id.tv_wlbm);
            holder.tv_miaoshu = (TextView) view
                    .findViewById(R.id.tv_miaoshu);
            holder.iv_del = (ImageView) view
                    .findViewById(R.id.iv_del);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_sl.setText(list.get(i).getFnumber());
        holder.tv_dw.setText(list.get(i).getFunit());
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    class ViewHolder{
        TextView tv_pch,tv_sl,tv_dw,tv_wlbm,tv_miaoshu;
        ImageView iv_del;
    }
}
