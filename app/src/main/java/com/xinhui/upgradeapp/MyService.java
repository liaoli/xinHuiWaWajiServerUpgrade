package com.xinhui.upgradeapp;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.xinhui.upgradeapp.content.UrlOriginContent;
import com.xinhui.upgradeapp.content.CtrlType;
import com.xinhui.upgradeapp.service.LongLinkMessageManager;
import com.xinhui.upgradeapp.util.SilentInstall;

import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import boomegg.cn.wawa.proto.Game;

public class MyService extends Service implements LongLinkMessageManager.CtrlCallback {
    private static final String TAG = "MyService";
    private final static int GRAY_SERVICE_ID = -1001;

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(task, 0, 10000);

        LongLinkMessageManager.getManager().setCtrlCallback(this);
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(GRAY_SERVICE_ID, new Notification());//API < 18 ，此方法能有效隐藏Notification上的图标
        return super.onStartCommand(intent, flags, startId);
    }

    Timer timer = new Timer();

    public boolean isAppStart = false;
    private String packageName_now = UrlOriginContent.TARGET_PACKAGE;
    private String packageName_Daemon = packageName_now + ":guard";
    private String packageName_MyDaemon = "com.xinhui.upgradeapp" + ":guard";

    TimerTask task = new TimerTask() {


        public boolean isUIvisible;
        public boolean isUIAlive;
        public boolean isThirdDaemonLive;
        private boolean isDaemonLive;

        @Override
        public void run() {

            if (getBaseContext() == null) {
                return;
            }
            ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> processInfos = mActivityManager.getRunningAppProcesses();

            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                if (processInfo.processName.equals(packageName_now)) {
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                            || processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                        isUIvisible = true;
                    } else {
                        isUIvisible = false;
                    }

                    isUIAlive = true;
                    break;
                } else {
                    isUIAlive = false;
                    isUIvisible = false;
                }

            }

            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                if (processInfo.processName.equals(packageName_MyDaemon)) {
                    isDaemonLive = true;
                    break;
                } else {
                    isDaemonLive = false;
                }

            }

            if (!isDaemonLive) {
                Intent intent = new Intent(MyService.this, DaemonService.class);
                startService(intent);
            }


            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                if (processInfo.processName.equals(packageName_Daemon)) {
                    isThirdDaemonLive = true;
                    break;
                } else {
                    isThirdDaemonLive = false;
                }
            }


            if (!isUIAlive || !isUIvisible) {
                //TODO：通知守护进程 启动UI
                startWawajiDaemon();
            }

            if (!isThirdDaemonLive) {
                //TODO：启动守护进程
                startWawajiDaemon();

            }
            Log.e(TAG, "processInfo ------------->  " + packageName_now + "is live = " + isUIAlive + ",is visible = " + isUIvisible + ",isDaemonLive = " + isDaemonLive + " isThirdDaemonLive =" + isThirdDaemonLive );
        }


    };

    private void startWawajiDaemon() {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.xinhuitech.xhdollmachineserver","com.xinhuitech.xhdollmachineserver.service.GuardService");
            startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private BaseDownloadTask createDownLoadTask(String url) {

        FileDownloadHelper.holdContext(getApplicationContext());
        FileDownloader.setup(getApplicationContext());
        String path = FileDownloadHelper.getAppContext().getExternalCacheDir().getAbsolutePath() + File.separator + "update.apk";
        if (TextUtils.isEmpty(url)) {
            url = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
        }

        return FileDownloader.getImpl().
                create(url)
                .setPath(path, false)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(new FileDownloadSampleListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        task.cancel();
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue,
                                             int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        Log.e("completed", "completed" + task.getFilename());

                        String path = task.getPath();


                        if (BuildConfig.DEBUG) {
                            path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "wwj.apk";
                        }

                        boolean result = SilentInstall.install(path);

                        Log.e(TAG,"result = " + result);
                        if (result) {
                            startWawajiDaemon();
                        }

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void onAction(int commandId, Game.CtrlMsgReq ctrlMsgRep) {

        switch (ctrlMsgRep.getCmd()) {

            case CtrlType.RESTART:
                startWawajiDaemon();
                break;
            case CtrlType.UPDATE:
                createDownLoadTask(ctrlMsgRep.getData()).start();
                break;
        }
    }


}
