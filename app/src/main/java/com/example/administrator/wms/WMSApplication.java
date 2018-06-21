package com.example.administrator.wms;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/21 9:57
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class WMSApplication extends Application {

    public static String userID   = "";//用户id
    public static String username = "";//用户名

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
