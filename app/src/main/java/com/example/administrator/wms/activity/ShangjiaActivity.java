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
import com.example.administrator.wms.fragment.GroupsjFragment;
import com.example.administrator.wms.fragment.SinglesjFragment;

public class ShangjiaActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SinglesjFragment ssjFragment;
    private GroupsjFragment gsjFragment;
    Button[] btns = new Button[2];
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
        setContentView(R.layout.activity_shangjia);
        setTool();
        setViews();
        setListeners();
    }

    protected void setTool(){
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        toolbar.setTitle(R.string.wlsj);

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
        btns[0] = (Button) findViewById(R.id.btn_single);
        btns[1] = (Button) findViewById(R.id.btn_group);
        btns[0].setSelected(true);
        ssjFragment = new SinglesjFragment();
        gsjFragment = new GroupsjFragment();
        frags = new Fragment[]{ssjFragment,gsjFragment};

        // 一开始，显示第一个fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.sj_fragment, ssjFragment);
        transaction.show(ssjFragment);
        transaction.commit();
    }

    protected void setListeners(){
        for(int i=0;i<btns.length;i++){
            btns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        switch (view.getId()) {
                            case R.id.btn_single:
                                selectedIndex = 0;
                                break;
                            case R.id.btn_group:
                                selectedIndex = 1;
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
                                transaction.add(R.id.sj_fragment,
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
