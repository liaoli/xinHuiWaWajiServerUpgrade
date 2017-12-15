package com.xinhui.upgradeapp.service;

/**
 * Created by Administrator on 2017/12/12.
 */

public interface LongLink {
    int ERROR_NETWORK = 0x1;
    int ERROR_DECODE = 101;

    boolean makeSureConnected();

    boolean sendMessage(byte[] message);

    void disconnect();

    interface HeartBeatCallback {
        void onLongLinkEstablished(LongLink longLink);

        void onLongLinkDisconnected();

        void onHeartBeatResult(boolean success);
    }

    interface LongLinkObserver {

        void onLongLinkConnected();

        boolean needVerify();

        void onVerifySend(LongLink longLink);

        void onVerifyResponse(byte[] buffer);

        void onLongLinkDisconnected();
    }


    interface LongLinkCallback {

        void onLongLinkConnected();

        void onMessageReceived(byte[] buffer);

        void onError(int errorCode, Exception ex);

        void onLongLinkDisconnected();
    }
}

