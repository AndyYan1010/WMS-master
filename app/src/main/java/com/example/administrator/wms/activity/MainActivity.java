package com.example.administrator.wms.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.GridviewApater1;
import com.example.administrator.wms.entity.MainMenuEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gv;
    private long exitTime = 0;

    private int[] resArr = new int[]{R.drawable.shouliao, R.drawable.shangjia,R.drawable.faliao,R.drawable.chaipi,R.drawable.kucun,R.drawable.pandian
            ,R.drawable.baozhuang,R.drawable.fahuo,R.drawable.kusun,R.drawable.zhijian,0,0};

    private String[] textArr = new String[]{"物料接收", "物料上架","发料开单","截料拆批","库存查询","盘点","包装追溯","成品发货","库损处理","物料质检","",""};
    private List<MainMenuEntity> dadaList;
    private GridviewApater1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dadaList = new ArrayList<MainMenuEntity>();
        for (int i = 0; i < resArr.length; i++) {
            MainMenuEntity data = new MainMenuEntity();
            data.setResId(resArr[i]);
            data.setText(textArr[i]);
            dadaList.add(data);
        }
        gv = (GridView) findViewById(R.id.gv);
        adapter = new GridviewApater1(this, dadaList);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        startActivity(new Intent(MainActivity.this,ShouliaoActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,ShangjiaActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,FaliaoActivity.class));
                        break;
                    case 3:
//                        startActivity(new Intent(MainActivity.this,JieliaoActivity.class));
                        startActivity(new Intent(MainActivity.this,FldetailActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,KucunActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this,PandianActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this,BaozhuangActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this,ChengpinActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this,KusunActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this,ZhijianActivity.class));
                        break;
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出应用",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
