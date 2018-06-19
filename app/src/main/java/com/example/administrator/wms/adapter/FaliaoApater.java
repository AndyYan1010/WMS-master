package com.example.administrator.wms.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.entity.Faliao;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017-03-20.
 */
public class FaliaoApater extends BaseAdapter {

    private Context mContext = null;
    private List<Faliao> dataList;

    private LayoutInflater inflater;

    public FaliaoApater(Context context, List<Faliao> dataList) {
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

            view = inflater.inflate(R.layout.item_faliao, null);
            holder = new Holder();
            holder.tv_fl1 = (TextView) view
                    .findViewById(R.id.tv_fl1);
            holder.tv_fl2 = (TextView) view
                    .findViewById(R.id.tv_fl2);
            holder.tv_fl3 = (TextView) view
                    .findViewById(R.id.tv_fl3);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.tv_fl1.setText(dataList.get(i).getFfaliaono());
        holder.tv_fl2.setText(dataList.get(i).getFgongdanno());
        holder.tv_fl3.setText(dataList.get(i).getFnote());

        return view;
    }

    private class Holder {
        TextView tv_fl1,tv_fl2,tv_fl3;
    }

}
