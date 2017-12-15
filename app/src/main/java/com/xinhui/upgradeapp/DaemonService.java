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
    private Class  Daemon = MyService.class;
    TimerTask task = new TimerTask() {


        public boolean isMyServiceAlive;

        @Override
        public void run() {

            if (getBaseContext() == null) {
                return;
            }

            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningServiceInfo> services = mActivityManager.getRunningServices(Integer.MAX_VALUE);


            for(ActivityManager.RunningServiceInfo service : services){
                String name = service.service.getClassName();
                String myName = Daemon.getName();
                if (myName.equals(name)) {
                    isMyServiceAlive = true;
                    break;
                } else {
                    isMyServiceAlive = false;
                }

                Log.e(TAG, "service ------------->  "+  service.service.getClassName());
            }

            if(!isMyServiceAlive){
                //TODO：启动myservice
                Intent intent = new Intent(DaemonService.this,MyService.class);
                startService(intent);
            }

            Log.e(TAG, "processInfo ------------->  " + Daemon.getName() + "is live = " + isMyServiceAlive );
        }

    };
}
