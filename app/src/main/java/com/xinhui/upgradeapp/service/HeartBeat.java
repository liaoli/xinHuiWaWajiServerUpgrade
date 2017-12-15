package com.xinhui.upgradeapp.service;

import android.os.Handler;
import android.os.Message;

import com.xinhuitech.baselibrary.data.serialize.ProtoBufferMapper;
import com.xinhuitech.baselibrary.utils.LogUtils;

import boomegg.cn.wawa.proto.Cmd;
import boomegg.cn.wawa.proto.Game;

/**
 * Created by Administrator on 2017/12/12.
 */

public class HeartBeat implements LongLink.HeartBeatCallback {
    public static final int HEAT_BEAT_MSG = 1;
    public static final int HEAT_BEAT_TIME_OUT_MSG = 2;
    public static final long HEART_BEAT_PERIOD = 10000;
    public static final long HEART_BEAT_TIME_OUT_PERIOD = 2000;
    private static HeatBeatHandler heatBeatHandler = new HeatBeatHandler();
    private static LongLink longLink;
    public static byte[] heartBeatBytes;
    private int heartBeatTimeoutCount;

    public HeartBeat() {
        Game.WAWAHBReq regReq = Game.WAWAHBReq.newBuilder().build();
        ProtoBufferMapper<Game.WAWAHBReq, Game.WAWAHBResp> bufferMapper =
                new ProtoBufferMapper<>(regReq, Game.WAWAHBResp.parser(), Cmd.GAME_CMD.GAME_CMD_WAWA_HB_REQ_VALUE);
        heartBeatBytes = LongLinkPacker.packData(Cmd.GAME_CMD.GAME_CMD_WAWA_HB_REQ_VALUE, bufferMapper.transformTo());
    }

    @Override
    public void onLongLinkEstablished(LongLink longLink) {
        this.longLink = longLink;
        heatBeatHandler.removeMessages(HEAT_BEAT_MSG);
        heatBeatHandler.removeMessages(HEAT_BEAT_TIME_OUT_MSG);
        heatBeatHandler.sendEmptyMessageDelayed(HEAT_BEAT_MSG, HEART_BEAT_PERIOD);
    }

    public static void sendHeartBeat() {
        if (longLink.makeSureConnected()) {
            LogUtils.e("sendHeartBeat");
            boolean success = longLink.sendMessage(heartBeatBytes);
            if (!success) {
                longLink.makeSureConnected();
            }
        }
    }

    public static void onHeartBeatTimeout() {
        longLink.makeSureConnected();
    }

    @Override
    public void onLongLinkDisconnected() {
        longLink = null;
        heatBeatHandler.removeMessages(HEAT_BEAT_MSG);
        heatBeatHandler.removeMessages(HEAT_BEAT_TIME_OUT_MSG);
    }

    @Override
    public void onHeartBeatResult(boolean success) {
        heatBeatHandler.removeMessages(HEAT_BEAT_TIME_OUT_MSG);
    }


    public static class HeatBeatHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == HEAT_BEAT_MSG) {
                HeartBeat.sendHeartBeat();
                heatBeatHandler.sendEmptyMessageDelayed(HEAT_BEAT_TIME_OUT_MSG, HEART_BEAT_TIME_OUT_PERIOD);
                heatBeatHandler.sendEmptyMessageDelayed(HEAT_BEAT_MSG, HEART_BEAT_PERIOD);
            } else {
                heatBeatHandler.removeMessages(HEAT_BEAT_MSG);
                heatBeatHandler.removeMessages(HEAT_BEAT_TIME_OUT_MSG);
                HeartBeat.onHeartBeatTimeout();
            }
        }
    }
}
