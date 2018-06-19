package com.example.administrator.wms.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.activity.AsnActivity;
import com.example.administrator.wms.adapter.SldAdapter;
import com.example.administrator.wms.entity.AsnDetail;
import com.example.administrator.wms.util.Consts;
import com.example.administrator.wms.util.HttpUtils;
import com.example.administrator.wms.view.CustomProgress;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ShouliaoFragment extends Fragment {
    Context mContext;
    View view;
    TextView tv_sld;
    ListView lv_sld;
    List<AsnDetail> list;
    CustomProgress dialog;
    SldAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        view = inflater.inflate(R.layout.fragment_shouliao, container, false);
        setViews();
        setListeners();
        return view;
    }

    protected void setViews(){
        tv_sld = (TextView) view.findViewById(R.id.tv_sld);
        lv_sld = (ListView) view.findViewById(R.id.lv_sld);
        list = new ArrayList<>();
    }

    protected void setListeners(){
        tv_sld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(mContext);
                new AlertDialog.Builder(mContext).setTitle("请输入单号").setIcon(
                        R.drawable.danhao).setView(
                        et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_sld.setText(et.getText().toString());
                        //查询收料单详情
                        new SlTask(tv_sld.getText().toString()).execute();
                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });

        lv_sld.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AsnDetail asn = list.get(i);
                Intent intent = new Intent(mContext, AsnActivity.class);
                intent.putExtra("itemid",asn.getFitemid());
                intent.putExtra("itemname",asn.getFitemname());
                intent.putExtra("qty",asn.getFqty());
                startActivity(intent);
            }
        });
    }

    class SlTask extends AsyncTask<Void,String,String>{

        String number;
        public SlTask(String number){
            this.number = number;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list.clear();
            dialog = CustomProgress.show(mContext,"搜索中",true,null);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String url = Consts.URL + "searchSld?number="+number;
                HttpResponse response = HttpUtils.GetUtil(url);
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                String result = reader.readLine();
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return "1";
            }
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            dialog.dismiss();
            DecimalFormat df = new DecimalFormat("#");
            Log.i("搜索得到收料单信息",str+"=====================================");
            if(!str.equals("1")){
                try {
                    JSONArray array = new JSONArray(str);
                    for(int i=0;i<array.length();i++){
                        JSONObject json = array.getJSONObject(i);
                        AsnDetail detail = new AsnDetail();
                        detail.setFitemid(json.getString("fitemid"));
                        detail.setFitemname(json.getString("fitemname"));
                        detail.setFsupplier(json.getString("fsupplier"));
                        Double qty = Double.parseDouble(json.getString("fqty"));
                        detail.setFqty(df.format(qty));
                        list.add(detail);
                    }
                    adapter = new SldAdapter(mContext,list);
                    lv_sld.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else {
                Toast.makeText(mContext,"搜索失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
