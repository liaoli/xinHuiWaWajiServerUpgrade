package com.xinhui.upgradeapp.service;

import android.support.v4.util.ArrayMap;

import com.xinhui.upgradeapp.util.PrefUtils;
import com.xinhuitech.baselibrary.data.serialize.ProtoBufferMapper;
import com.xinhuitech.baselibrary.utils.LogUtils;
import com.xinhuitech.baselibrary.utils.PackageUtils;
import com.xinhuitech.baselibrary.utils.PrefUtil;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import boomegg.cn.wawa.proto.Base;
import boomegg.cn.wawa.proto.Cmd;
import boomegg.cn.wawa.proto.Game;
import boomegg.cn.wawa.proto.Struct;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LongLinkMessageManager implements LongLink.LongLinkCallback, LongLink.LongLinkObserver {
    private static LongLinkMessageManager longLinkMessageManager;
    private LongLink longLink;
    private BlockingDeque<ProtoBufferMapper> protoBufferQueue = new LinkedBlockingDeque<>();
    private Map<Integer, ProtoBufferMapper> responseMapperMap = new ArrayMap<>();
    private Map<ProtoBufferMapper, Integer> commandMap = new ArrayMap<>();
    private HeartBeat heartBeat;
    private int messageCmd;
    private MessageCallback messageCallback;

    private LongLinkMessageManager() {
        heartBeat = new HeartBeat();
        longLink = new WebSocketLink(this, this, heartBeat);
        longLink.makeSureConnected();
    }

    public static LongLinkMessageManager getManager() {
        synchronized (LongLinkMessageManager.class) {
            if (longLinkMessageManager == null) {
                longLinkMessageManager = new LongLinkMessageManager();
            }
        }
        return longLinkMessageManager;
    }

    public void send(int commandId, ProtoBufferMapper protoBufferMapper) {
        LogUtils.e("send commandId: " + commandId);
        responseMapperMap.put(commandId + 1, protoBufferMapper);
        if (!longLink.makeSureConnected()) {
            commandMap.put(protoBufferMapper, commandId);
            protoBufferQueue.add(protoBufferMapper);
            return;
        }

        byte[] buffer = new byte[0];
        buffer = protoBufferMapper.transformTo();

        boolean success = longLink.sendMessage(LongLinkPacker.packData(commandId, buffer));
        if (!success) {
            longLink.makeSureConnected();
            commandMap.put(protoBufferMapper, commandId);
            protoBufferQueue.add(protoBufferMapper);
        }
    }

    @Override
    public void onLongLinkConnected() {
        int size = protoBufferQueue.size();
        LogUtils.e("onLongLinkConnected: size " + size);
        if (size > 0) {
            ProtoBufferMapper bufferMapper;
            for (int i = 0; i < size; i++) {
                bufferMapper = protoBufferQueue.poll();

                longLink.sendMessage(LongLinkPacker.packData(commandMap.get(bufferMapper), bufferMapper.transformTo()));

            }
        }
    }

    @Override
    public boolean needVerify() {
        return true;
    }

    @Override
    public void onVerifySend(LongLink longLink) {
        LogUtils.e("onVerifySend: ");
        PrefUtils instance = PrefUtils.getInstance();
        Struct.ZegoInfo zegoInfo = Struct.ZegoInfo.newBuilder().setRoomID(PrefUtil.getInstance().getRoomId())
                .addStreamUrls(PrefUtil.getInstance().getStreamId()).addStreamUrls(PrefUtil.getInstance().getStreamId2()).build();
        Game.WAWARegReq regReq = Game.WAWARegReq.newBuilder().setName("xue_bao_001").setSerial(instance.getString("UUID"))
                .setDeviceVer(PackageUtils.getVersionName()).setZegoInfo(zegoInfo).build();
        ProtoBufferMapper<Game.WAWARegReq, Game.WAWARegResp> bufferMapper = new ProtoBufferMapper<>(regReq, Game.WAWARegResp.parser(), Cmd.GAME_CMD.GAME_CMD_WAWA_REG_REQ_VALUE);
        send(bufferMapper.getCommandId(), bufferMapper);
    }

    @Override
    public void onVerifyResponse(byte[] buffer) {
        LogUtils.e("onVerifyResponse: ");
        ProtoBufferMapper bufferMapper = (ProtoBufferMapper) responseMapperMap.get(Cmd.GAME_CMD.GAME_CMD_WAWA_REG_RESP_VALUE);
        if (bufferMapper != null) {
            bufferMapper.transformFrom(buffer);
        }

        commandMap.remove(bufferMapper);
    }

    @Override
    public void onMessageReceived(byte[] buffer) {
        int[] command = new int[1];
        byte[] unPackData = LongLinkPacker.unPackData(buffer, command);
        messageCmd = command[0];
        LogUtils.e("onMessageReceived commandId: " + messageCmd);
        try {
            switch (messageCmd) {
                case Cmd.GAME_CMD.DOLL_MACHINE_INIT_REQ_VALUE:
                case Cmd.GAME_CMD.GAME_CMD_SRV_TAKE_WAWA_REQ_VALUE:
                    if (messageCallback != null) {
                        messageCallback.onPush(messageCmd, unPackData);
                    }
                    break;
                case Cmd.GAME_CMD.GAME_CMD_WAWA_HB_RESP_VALUE:
                    Game.WAWAHBResp wawahbResp = Game.WAWAHBResp.parseFrom(unPackData);
                    heartBeat.onHeartBeatResult(true);
                    break;
                case Cmd.GAME_CMD.GAME_CMD_WAWA_REG_RESP_VALUE:
                    onVerifyResponse(unPackData);
                    break;
                case 150001:
                    /**
                     * 解包  收到消息
                     */
                    Game.CtrlMsgReq ctrlMsgRep = Game.CtrlMsgReq.parseFrom(unPackData);

                    if(ctrlCallback != null){
                        ctrlCallback.onAction(messageCmd,ctrlMsgRep);
                    }

                    Base.RspHead rspHead = Base.RspHead.newBuilder().setRetCode(0).setRetMsg("received").build();
                    Game.CtrlMsgResp ctrlMsgResp = Game.CtrlMsgResp.newBuilder().setHead(rspHead).build();

                    boolean flag = longLink.sendMessage(new ProtoBufferMapper(ctrlMsgResp, null, messageCmd + 1).transformTo());

                    LogUtils.e("Ctrl resp " + ctrlMsgRep + ", flag = " + flag + ctrlMsgRep.toString());

                    /**
                     * 打包发消息
                     */
                    //  LongLinkPacker.unPackData(ctrlEndReq.toByteArray(),command);

                    break;
                default:
                    ProtoBufferMapper bufferMapper = responseMapperMap.remove(messageCmd);
                    if (bufferMapper != null) {
                        bufferMapper.transformFrom(unPackData);
                    }

                    commandMap.remove(bufferMapper);

                    if (messageCallback != null) {
                        messageCallback.onMessageResponse(messageCmd, 0);
                    }
                    // TODO: 2017/12/12 通知客户端进程分发消息
                    break;


            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (messageCallback != null) {
                messageCallback.onMessageResponse(messageCmd, -1);
            }
        }
    }

    @Override
    public void onError(final int errorCode, Exception ex) {
        LogUtils.e("onDispatchError: " + errorCode + ", exception: " + ex.toString());
        LogUtils.e(ex);
    }

    @Override
    public void onLongLinkDisconnected() {

    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    public interface MessageCallback {
        void onMessageResponse(int commandId, int errorCode);

        void onPush(int commandId, byte[] buffer);
    }

    public interface  CtrlCallback{
        void onAction(int commandId ,Game.CtrlMsgReq ctrlMsgRep);
    }

    CtrlCallback ctrlCallback;

    public void setCtrlCallback(CtrlCallback ctrlCallback) {
        this.ctrlCallback = ctrlCallback;
    }
}
