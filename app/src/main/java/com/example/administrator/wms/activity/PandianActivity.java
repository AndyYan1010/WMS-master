package com.example.administrator.wms.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.administrator.wms.R;
import com.example.administrator.wms.fragment.CyFragment;
import com.example.administrator.wms.fragment.PdDetailFragment;
import com.example.administrator.wms.fragment.PdFragment;
import com.example.administrator.wms.fragment.YpFragment;

public class PandianActivity extends AppCompatActivity {
    Toolbar toolbar;
    PdDetailFragment pddFragment;
    PdFragment pdFragment;
    YpFragment ypFragment;
    CyFragment cyFragment;
    Button[] btns = new Button[4];
    Fragment[] frags = null;
    /**
     * 当前显示的fragment
     */
    int currentIndex = 0;
    /**
     * 选中的button,显示下一个fragment
     */
    int selectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pandian);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(R.string.pandian);

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
        btns[0] = (Button) findViewById(R.id.btn_pd_detail);
        btns[1] = (Button) findViewById(R.id.btn_pd);
        btns[2] = (Button) findViewById(R.id.btn_yp);
        btns[3] = (Button) findViewById(R.id.btn_cy);
        btns[0].setSelected(true);
        pddFragment = new PdDetailFragment();
        pdFragment = new PdFragment();
        ypFragment = new YpFragment();
        cyFragment = new CyFragment();
        frags = new Fragment[]{pddFragment,pdFragment,ypFragment,cyFragment};

        // 一开始，显示第一个fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.pd_fragment,pddFragment);
        transaction.show(pddFragment);
        transaction.commit();
    }

    protected void setListeners(){
        for(int i=0;i<btns.length;i++) {
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        switch (view.getId()) {
                            case R.id.btn_pd_detail:
                                selectedIndex = 0;
                                break;
                            case R.id.btn_pd:
                                selectedIndex = 1;
                                break;
                            case R.id.btn_yp:
                                selectedIndex = 2;
                                break;
                            case R.id.btn_cy:
                                selectedIndex = 3;
                                break;
                        }

                        // 判断单击是不是当前的
                        if (selectedIndex != currentIndex) {
                            // 不是当前的
                            FragmentTransaction transaction = getSupportFragmentManager()
                                    .beginTransaction();
                            // 当前hide
                            transaction.hide(frags[currentIndex]);
                            // show你选中

                            if (!frags[selectedIndex].isAdded()) {
                                // 以前没添加过
                                transaction.add(R.id.pd_fragment,
                                        frags[selectedIndex]);
                            }
                            // 事务
                            transaction.show(frags[selectedIndex]);
                            transaction.commit();

                            btns[currentIndex].setSelected(false);
                            btns[selectedIndex].setSelected(true);
                            currentIndex = selectedIndex;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
