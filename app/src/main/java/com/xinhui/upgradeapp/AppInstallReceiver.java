
package com.xinhui.upgradeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xinhui.upgradeapp.content.UrlOriginContent;

/**
 * Created by liaoli on 2017/12/12.
 * 升级之后，自动开开启
 */

public class AppInstallReceiver extends BroadcastReceiver {

    public String packageName = UrlOriginContent.TARGET_PACKAGE;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String pkgName = intent.getDataString();
        if(action.equals(Intent.ACTION_PACKAGE_ADDED) && packageName.equals(pkgName)){
            Intent i = context.getPackageManager().getLaunchIntentForPackage(packageName);
            context.startActivity(i);
            Log.d("tag","app installed " +  pkgName);
        }else if(action.equals(Intent.ACTION_PACKAGE_REMOVED)){
            Log.d("tag","app uninstalled" + pkgName);
        }
    }
}
