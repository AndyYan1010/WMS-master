package com.example.administrator.wms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.FaliaoApater2;
import com.example.administrator.wms.entity.Faliao2;

import java.util.ArrayList;
import java.util.List;

public class Faliao2Activity extends AppCompatActivity {
    Toolbar toolbar;
    ListView lv_faliao2;
    List<Faliao2> list;
    FaliaoApater2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faliao2);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle("发料单详情");

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
        lv_faliao2 = (ListView)findViewById(R.id.lv_faliao2);
        list = new ArrayList<>();
        adapter = new FaliaoApater2(Faliao2Activity.this,list);
        lv_faliao2.setAdapter(adapter);
    }

    protected void setListeners(){
        lv_faliao2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}
