package com.xinhui.upgradeapp.service;

import android.os.CountDownTimer;

import com.xinhui.upgradeapp.content.Content;
import com.xinhuitech.baselibrary.utils.LogUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * Created by Administrator on 2017/12/12.
 */

public class WebSocketLink extends WebSocketListener implements LongLink {
    private static final String HOST_WEB_SOCKET = Content.url_online;
    private static WebSocket remoteSocket;
    private static OkHttpClient okHttpClient;
    private static WebSocketLink webSocketLink;

    private LongLinkObserver longLinkObserver;
    private LongLinkCallback longLinkCallback;
    private HeartBeatCallback heartBeatCallback;
    private boolean disconnected;
    private boolean isConnecting;
    private final Request request;

    public WebSocketLink(LongLinkCallback longLinkCallback, LongLinkObserver longLinkObserver, HeartBeatCallback heartBeatCallback) {
        this.longLinkCallback = longLinkCallback;
        this.longLinkObserver = longLinkObserver;
        this.heartBeatCallback = heartBeatCallback;
        okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        request = builder.url(HOST_WEB_SOCKET).header("Origin", Content.Origin_online).build();
    }

    @Override
    public boolean makeSureConnected() {
        if (remoteSocket == null && !isConnecting) {
            remoteSocket = okHttpClient.newWebSocket(request, this);
            return false;
        } else if (disconnected && !isConnecting) {
            disconnect();
            remoteSocket = okHttpClient.newWebSocket(request, this);
            return false;
        }

        return true;
    }

    @Override
    public boolean sendMessage(byte[] message) {
        return remoteSocket.send(ByteString.of(message));
    }


    @Override
    public void disconnect() {
        if (remoteSocket != null) {
            remoteSocket.cancel();
        }
        disconnected = true;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        LogUtils.e("websocket: onOpen");
        isConnecting = false;
        disconnected = false;
        reConnectTimes = 0;
        if(reConnectCountDownTimer != null){
            reConnectCountDownTimer.cancel();
        }

        if (longLinkCallback != null) {
            longLinkCallback.onLongLinkConnected();
        }

        if (longLinkObserver != null && longLinkObserver.needVerify()) {
            longLinkObserver.onVerifySend(this);
        }

        if (heartBeatCallback != null) {
            heartBeatCallback.onLongLinkEstablished(this);
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        LogUtils.e("websocket: onMessage : " + text);
        if (longLinkCallback != null) {
            longLinkCallback.onMessageReceived(text.getBytes());
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        LogUtils.e("websocket: onMessage : " + bytes.toString());
        if (longLinkCallback != null) {
            longLinkCallback.onMessageReceived(bytes.toByteArray());
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        disconnected = true;
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        LogUtils.e("websocket: onClosed");
        disconnected = true;
        isConnecting = false;
        remoteSocket = null;
        if (longLinkCallback != null) {
            longLinkCallback.onLongLinkDisconnected();
        }

        if (heartBeatCallback != null) {
            heartBeatCallback.onLongLinkDisconnected();
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (t instanceof IOException) {
            disconnect();
            reConnect();
        }

        LogUtils.e("websocket onFailure:");
        LogUtils.error(t);
    }

    private int reConnectTimes = 0;

    private int reConnectedtime = 3000;
    private CountDownTimer reConnectCountDownTimer;

    private void reConnect() {

        if(reConnectTimes > 10){
            return;
        };
        reConnectTimes += 1;
        reConnectCountDownTimer = new CountDownTimer(reConnectedtime * reConnectTimes, 1000) {
            @Override
            public void onTick(long l) {

                if (isConnecting) {
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                makeSureConnected();
            }
        };

        reConnectCountDownTimer.start();
    }
}
