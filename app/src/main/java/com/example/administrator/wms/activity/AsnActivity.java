package com.example.administrator.wms.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.OwnerAdapter;
import com.example.administrator.wms.entity.Owner;
import com.example.administrator.wms.util.Consts;
import com.example.administrator.wms.util.HttpUtils;
import com.example.administrator.wms.view.CustomProgress;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AsnActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button btn_submit;
    TextView tv_wlname,tv_barcode,tv_daoda,tv_shishou,tv_owner;
    String itemid,ownerid="";
    CustomProgress dialog;
    List<Owner> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asn);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("物料明细");

        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setViews(){
        btn_submit = (Button)findViewById(R.id.btn_submit);
        tv_wlname = (TextView)findViewById(R.id.tv_wlname);
        tv_barcode = (TextView)findViewById(R.id.tv_barcode);
        tv_daoda = (TextView)findViewById(R.id.tv_daoda);
        tv_shishou = (TextView)findViewById(R.id.tv_shishou);
        tv_owner = (TextView)findViewById(R.id.tv_owner);
        Intent intent = getIntent();
        tv_wlname.setText(intent.getStringExtra("itemname"));
        tv_daoda.setText(intent.getStringExtra("qty"));
        itemid = intent.getStringExtra("itemid");
        list = new ArrayList<>();
    }

    protected void setListeners(){
        tv_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(AsnActivity.this);
                new AlertDialog.Builder(AsnActivity.this).setTitle("请输入条码").setIcon(
                        R.drawable.danhao).setView(
                        et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_barcode.setText(et.getText().toString());
                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });
        tv_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OwnerTask().execute();
            }
        });
        tv_shishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(AsnActivity.this);
                new AlertDialog.Builder(AsnActivity.this).setTitle("请输入数量").setIcon(
                        R.drawable.danhao).setView(
                        et).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_shishou.setText(et.getText().toString());
                    }
                })
                        .setNegativeButton("取消", null).show();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ownerid.equals("")){
                    Toast.makeText(AsnActivity.this,"请选择货主",Toast.LENGTH_SHORT).show();
                }else if(tv_barcode.getText().toString().equals("")){
                    Toast.makeText(AsnActivity.this,"请扫入条码",Toast.LENGTH_SHORT).show();
                }else if(tv_shishou.getText().toString().equals("")){
                    Toast.makeText(AsnActivity.this,"请输入实收数量",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        JSONObject json = new JSONObject();
                        json.put("barcode", tv_barcode.getText().toString());
                        json.put("itemid", itemid);
                        json.put("acceptno",tv_shishou.getText().toString());
                        json.put("ownerid",ownerid);
                        new SubmitTask(json.toString()).execute();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    class OwnerTask extends AsyncTask<Void,String,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list.clear();
            dialog = CustomProgress.show(AsnActivity.this,"加载中",true,null);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String url = Consts.URL+"ownerList";
                HttpResponse response = HttpUtils.GetUtil(url);
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                String result = reader.readLine();
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            Log.i("货主列表",s+"= = = = = = = = = = = = = = = = = = = = = = =");
            if(s!=null){
                try {
                    JSONArray array = new JSONArray(s);
                    for(int i=0;i<array.length();i++){
                        JSONObject json = array.getJSONObject(i);
                        Owner owner = new Owner();
                        owner.setId(json.getString("id"));
                        owner.setName(json.getString("name"));
                        owner.setContact(json.getString("contact"));
                        list.add(owner);
                    }
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.dialog,null);
                    ListView lv = (ListView)view.findViewById(R.id.lv);

                    final OwnerAdapter adapter = new OwnerAdapter(AsnActivity.this,list);
                    lv.setAdapter(adapter);

                    final AlertDialog alertDialog = new AlertDialog.Builder(AsnActivity.this).setTitle("请选择货主").setView(view).setIcon(R.drawable.huozhu).create();
                    alertDialog.show();

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            alertDialog.dismiss();
                            tv_owner.setText(list.get(i).getContact());
                            ownerid = list.get(i).getId();
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(AsnActivity.this,"搜索失败",Toast.LENGTH_SHORT).show();
            }
        }
    }


    class SubmitTask extends AsyncTask<Void,String,String>{
        String codeJson;

        SubmitTask(String codeJson){
            this.codeJson = codeJson;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = CustomProgress.show(AsnActivity.this,"加载中",true,null);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                String url = Consts.URL + "sendCode";
                NameValuePair pair1 = new BasicNameValuePair("codeJson", codeJson);
                ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
                pairs.add(pair1);
                HttpResponse response = HttpUtils.PostUtil(url, pairs);
                HttpEntity entity = response.getEntity();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(entity.getContent()));
                String result = reader.readLine();
                return result;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            if(s.equals("SUCCESS")){
                Toast.makeText(AsnActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(AsnActivity.this,"提交失败",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
