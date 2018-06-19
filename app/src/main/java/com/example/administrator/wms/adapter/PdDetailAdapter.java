package com.example.administrator.wms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.PdDetail;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23 0023.
 */

public class PdDetailAdapter extends BaseAdapter{
    private Context mContext = null;
    private List<PdDetail> datalist;

    private LayoutInflater inflater;

    public PdDetailAdapter(Context context,List<PdDetail> datalist){
        this.mContext = context;
        this.datalist = datalist;
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
            view = inflater.inflate(R.layout.item_pd_detail,null);
            holder = new Holder();
            holder.tv_pici = (TextView) view
                    .findViewById(R.id.tv_pici);
            holder.tv_shul = (TextView) view
                    .findViewById(R.id.tv_shul);
            holder.tv_dw = (TextView) view
                    .findViewById(R.id.tv_dw);
            holder.tv_kuwei = (TextView) view
                    .findViewById(R.id.tv_kuwei);
            holder.tv_pdsl = (TextView) view
                    .findViewById(R.id.tv_pdsl);
            holder.tv_pdr = (TextView) view
                    .findViewById(R.id.tv_pdr);
            holder.tv_pdtime = (TextView) view
                    .findViewById(R.id.tv_pdtime);
            holder.tv_wlcode = (TextView) view
                    .findViewById(R.id.tv_wlcode);
            holder.tv_gg = (TextView) view
                    .findViewById(R.id.tv_gg);
            holder.tv_gys = (TextView) view
                    .findViewById(R.id.tv_gys);
            holder.tv_ck = (TextView) view
                    .findViewById(R.id.tv_ck);
            view.setTag(holder);
        }else{
            holder = (Holder)view.getTag();
        }

        holder.tv_pici.setText(datalist.get(i).getFpicihao());
        holder.tv_shul.setText(datalist.get(i).getFnumber());
        holder.tv_dw.setText(datalist.get(i).getFunit());
        holder.tv_kuwei.setText(datalist.get(i).getFkuwei());
        holder.tv_pdsl.setText(datalist.get(i).getFpdnumber());
        holder.tv_pdr.setText(datalist.get(i).getFpdmember());
        holder.tv_pdtime.setText(datalist.get(i).getFpdtime());
        holder.tv_wlcode.setText(datalist.get(i).getFwlcode());
        holder.tv_gg.setText(datalist.get(i).getFmodel());
        holder.tv_gys.setText(datalist.get(i).getFsupplier());
        holder.tv_ck.setText(datalist.get(i).getFstock());
        return view;
    }

    class Holder{
        TextView tv_pici,tv_shul,tv_dw,tv_kuwei,tv_pdsl,tv_pdr,tv_pdtime,tv_wlcode,tv_gg,tv_gys,tv_ck;
    }
}
