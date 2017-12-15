package com.xinhui.upgradeapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/12/15.
 */

public class App extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getRootContext(){
        return context;
    }
}
