package com.example.administrator.wms.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.wms.R;

public class KucunActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tv_totals;
    private EditText et_condition;
    private ListView lv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kucun);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(R.string.kccx);

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
        tv_totals = (TextView) findViewById(R.id.tv_totals);
        et_condition = (EditText) findViewById(R.id.et_condition);
        lv_result = (ListView) findViewById(R.id.lv_result);
    }

    protected void setListeners(){

    }
}
