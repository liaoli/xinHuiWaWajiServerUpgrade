package com.xinhui.upgradeapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.xinhui.upgradeapp.service.LongLinkMessageManager;
import com.xinhui.upgradeapp.util.SilentInstall;
import com.xinhui.upgradeapp.util.SystemManager;
import com.xinhuitech.baselibrary.utils.LogUtils;

import java.io.File;

import okhttp3.WebSocket;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private WebSocket mWebSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv);

        textView.setText("VersionCode:" + getVersionCode(this) + "\nVersionName:" + getVersionName(this));

        Intent service = new Intent(this, MyService.class);

        startService(service);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wwj.apk";


        boolean root_result = SystemManager.getRoot(this);

        Log.e(TAG,"root_result " + root_result);

//        boolean result = SilentInstall.install(path);
//
//        LogUtils.e("result  " + result);
//
//        Intent intent = new Intent();
//        intent.setClassName("com.xinhuitech.xhdollmachineserver", "com.xinhuitech.xhdollmachineserver.service.GuardService");
//        startService(intent);
    }

//


    /**
     * get App versionCode
     * @param context
     * @return
     */
    public String getVersionCode(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionCode=packageInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * get App versionName
     * @param context
     * @return
     */
    public String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionName="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
