package com.xinhui.upgradeapp;

import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2017/12/15.
 */

public class App extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        CrashReport.initCrashReport(getApplicationContext(), "38003a3ad1", false);
    }

    public static Context getRootContext(){
        return context;
    }
}
