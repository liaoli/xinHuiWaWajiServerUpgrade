package com.xinhui.upgradeapp.service;


import android.util.Log;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xinhuitech.baselibrary.utils.SystemUtils;

import boomegg.cn.wawa.proto.Base;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LongLinkPacker {

    public static byte[] packData(int commandId, byte[] dataBuffer) {
        String uuid = SystemUtils.getLanMacAddress() + "_" + SystemUtils.getIMEI();
        Log.e("uuid ----->", "uuid ----->" +uuid);
        Base.PktOpt pktOpt = Base.PktOpt.newBuilder().setVersion("1.0.0").setUID(uuid).build();
        Base.PktInfo pktInfo = Base.PktInfo.newBuilder().setCmd(commandId).setLen(dataBuffer.length).build();
        Base.PktHead pktHead = Base.PktHead.newBuilder().setOpt(pktOpt).setPktInfo(pktInfo).build();
        Base.Packet packet = Base.Packet.newBuilder().setHead(pktHead).setBody(ByteString.copyFrom(dataBuffer)).build();
        return packet.toByteArray();
    }


    public static byte[] unPackData(byte[] dataBuffer, int[] command) {
        try {
            Base.Packet packet = Base.Packet.parseFrom(dataBuffer);
            command[0] = packet.getHead().getPktInfo().getCmd();
            return packet.getBody().toByteArray();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return null;
    }
}
