package com.xinhui.upgradeapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;

/**
 * Created by Administrator on 2017/12/15.
 */

public class App extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        context = this;
       // CrashReport.initCrashReport(getApplicationContext(), "38003a3ad1", false);
        Bugly.init(getApplicationContext(), "38003a3ad1", false);
    }



    public static Context getRootContext(){
        return context;
    }
}
