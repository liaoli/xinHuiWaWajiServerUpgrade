package com.xinhuitech.baselibrary.data.serialize;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.xinhuitech.baselibrary.data.mapper.EntityToModelMapper;
import com.xinhuitech.baselibrary.domain.Model;
import com.xinhuitech.baselibrary.utils.LogUtils;
import com.xinhuitech.baselibrary.utils.SystemUtils;

import boomegg.cn.wawa.proto.Base;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/12/4.
 */

public class ProtoBufferMapper<R extends GeneratedMessageLite, P extends GeneratedMessageLite> {
    protected R request;
    protected P response;
    protected Parser<P> parser;
    private int commandId;
    EntityToModelMapper<GeneratedMessageLite> modelMapper;
    private Observer<GeneratedMessageLite> observer;
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

    public ProtoBufferMapper<R, P> modelMapper(EntityToModelMapper<? extends GeneratedMessageLite> modelMapper) {
        this.modelMapper = (EntityToModelMapper<GeneratedMessageLite>) modelMapper;
        return this;
    }

    public void postResult() {
        Observable<GeneratedMessageLite> observable = Observable.create(new ObservableOnSubscribe<GeneratedMessageLite>() {
            @Override
            public void subscribe(ObservableEmitter<GeneratedMessageLite> emitter) throws Exception {
                if (exception != null) {
                    emitter.onError(exception);
                } else {
                    emitter.onNext(response);
                    emitter.onComplete();
                }
            }
        });
        observable.subscribe(observer);

    }

    public int getCommandId() {
        return commandId;
    }

    public void subscribe(Observer<GeneratedMessageLite> observer) {
        this.observer = observer;
    }

}
