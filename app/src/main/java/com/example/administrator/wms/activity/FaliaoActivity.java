package com.example.administrator.wms.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.LvOrderNumAdapter;
import com.example.administrator.wms.util.Consts;
import com.example.administrator.wms.util.ProgressDialogUtil;
import com.example.administrator.wms.util.SoapUtil;
import com.example.administrator.wms.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FaliaoActivity extends AppCompatActivity implements View.OnClickListener {
    //    private TextView tv_gongji;
    //    private ListView lv_fl;
    //    private FaliaoApater adapter;
    //    private List<Faliao> list = new ArrayList<>();
    private Toolbar            toolbar;
    private ImageView          img_back;
    private TextView           tv_title;
    private ListView           lv_ord_num;
    private SmartRefreshLayout smartRefresh;
    private String[] mStrings = {"调拨单", "取料单"};
    private List<String>      mData;
    private LvOrderNumAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faliao);
        //setTool();
        setViews();
        setData();
        setListeners();
    }

    protected void setTool() {
        //        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        //        toolbar.setTitle(R.string.flkd);
        //
        //        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //        setSupportActionBar(toolbar);
        //
        //        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);
        //        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                finish();
        //            }
        //        });
        //
        //        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
        //            @Override
        //            public boolean onMenuItemClick(MenuItem item) {
        //                switch (item.getItemId()) {
        //                    case R.id.action_sm:
        //
        //                        break;
        //                }
        //                return true;
        //            }
        //        });
    }

    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        getMenuInflater().inflate(R.menu.menu, menu);//加载menu文件到布局
    //        return true;
    //    }

    protected void setViews() {
        //        tv_gongji = (TextView) findViewById(R.id.tv_gongji);
        //        lv_fl = (ListView) findViewById(R.id.lv_fl);
        //        Faliao fl = new Faliao();
        //        fl.setFfaliaono("qweyqgdsdgajksgdj");
        //        fl.setFgongdanno("ajsdhasjkdhjkasdh");
        //        fl.setFnote("空间哈尽快的哈数据库的哈");
        //        list.add(fl);
        //        adapter = new FaliaoApater(this,list);
        //        lv_fl.setAdapter(adapter);
        img_back = findViewById(R.id.img_back);
        tv_title = findViewById(R.id.tv_title);
        smartRefresh = findViewById(R.id.smart_ref);
        lv_ord_num = findViewById(R.id.lv_ord_num);
    }

    private void setData() {
        img_back.setOnClickListener(this);
        tv_title.setText("调拨列表");
        mData = new ArrayList();
//        mData.add("126.360.155");
//        mData.add("126.360.026");
//        mData.add("126.282.052");
//        mData.add("126.651.662");
        adapter = new LvOrderNumAdapter(FaliaoActivity.this, mData);
        lv_ord_num.setAdapter(adapter);
        lv_ord_num.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FaliaoActivity.this, AllocationDetailActivity.class);
                intent.putExtra("orderID", mData.get(i));
                startActivity(intent);
            }
        });

        //查询所有的调拨单
        String allOrderUrl = "select fbillno from icstockbill where ftrantype=41 and isnull(fcheckerid,0)=0";
        new ItemTask(allOrderUrl).execute();
    }

    protected void setListeners() {
        //        lv_fl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //                startActivity(new Intent());
        //            }
        //        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    class ItemTask extends AsyncTask<Void, String, String> {
        String sql;

        ItemTask(String sql) {
            this.sql = sql;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            Map<String, String> map = new HashMap<>();
            map.put("FSql", sql);
            map.put("FTable", "t_icitem");
            return SoapUtil.requestWebService(Consts.JA_select, map);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if (null==mData){
                    mData=new ArrayList<>();
                }else {
                    mData.clear();
                }
                Document doc = DocumentHelper.parseText(s);
                Element ele = doc.getRootElement();
                Iterator iter = ele.elementIterator("Cust");
                HashMap<String, String> map = new HashMap<>();
                while (iter.hasNext()) {
                    Element recordEle = (Element) iter.next();
                    map.put("fbillno", recordEle.elementTextTrim("fbillno"));//调拨单id
                    mData.add(recordEle.elementTextTrim("fbillno"));
                }
                //刷新页面
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(FaliaoActivity.this, "未搜索到调拨单");
            }
            ProgressDialogUtil.hideDialog();
        }
    }
}
