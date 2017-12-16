package com.xinhuitech.baselibrary.data.serialize;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.xinhuitech.baselibrary.utils.LogUtils;
import com.xinhuitech.baselibrary.utils.SystemUtils;


import boomegg.cn.wawa.proto.Base;
/**
 * Created by Administrator on 2017/12/4.
 */

public class ProtoBufferMapper<R extends GeneratedMessageLite, P extends GeneratedMessageLite> {
    protected R request;
    protected P response;
    protected Parser<P> parser;
    private int commandId;
    private Exception exception;

    public ProtoBufferMapper(R request, Parser<P> parser, int commandId) {
        this.request = request;
        this.parser = parser;
        this.commandId = commandId;
    }

    public byte[] transformTo() {
        com.google.protobuf.ByteString bodyBytes = request.toByteString();
        String uuid = SystemUtils.getLanMacAddress() + "_" + SystemUtils.getIMEI();
        Base.PktOpt pktOpt = Base.PktOpt.newBuilder().setVersion("1.0.0").setUID(uuid).build();
        Base.PktInfo pktInfo = Base.PktInfo.newBuilder().setCmd(commandId).setLen(bodyBytes.size()).build();
        Base.PktHead pktHead = Base.PktHead.newBuilder().setOpt(pktOpt).setPktInfo(pktInfo).build();
        Base.Packet packet = Base.Packet.newBuilder().setHead(pktHead).setBody(bodyBytes).build();
        return packet.toByteArray();
    }

    public <T> T transformFrom(byte[] data) {
        try {
            Base.Packet packet = Base.Packet.parseFrom(data);
            LogUtils.e("packet: " + packet);
            response = parser.parseFrom(packet.getBody());
            LogUtils.e("response: " + response);
            return (T) response;
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCommandId() {
        return commandId;
    }



}
