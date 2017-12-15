package com.xinhui.upgradeapp;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

public class DaemonService extends Service {
    private static final int GRAY_SERVICE_ID = -1002;

    public DaemonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(task, 0, 10000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(GRAY_SERVICE_ID, new Notification());//API < 18 ，此方法能有效隐藏Notification上的图标
        return super.onStartCommand(intent, flags, startId);
    }


    Timer timer = new Timer();

    public boolean isAppStart = false;
    private String packageName_now = "com.xinhui.upgradeapp";
    private String packageName_Daemon = packageName_now + ":guard";
    TimerTask task = new TimerTask() {


        public boolean isMyServiceAlive;

        @Override
        public void run() {

            if (getBaseContext() == null) {
                return;
            }

            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> processInfos = mActivityManager.getRunningAppProcesses();



            for(ActivityManager.RunningAppProcessInfo processInfo : processInfos){
                if (processInfo.processName.equals(packageName_Daemon)) {
                    isMyServiceAlive = true;
                    break;
                } else {
                    isMyServiceAlive = false;
                }
            }

            if(!isMyServiceAlive){
                //TODO：启动myservice
                Intent intent = new Intent(DaemonService.this,MyService.class);
                startService(intent);
            }

            Log.e(TAG, "processInfo ------------->  " + packageName_now + "is live = " + isMyServiceAlive + ",is visible = " + isMyServiceAlive );
        }

    };
}
