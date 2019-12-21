package com.yuansfer.client.connect;

import com.yuansfer.client.protocol.PosMessage;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/26 16:03
 * @Desciption 心跳工作，响应server发来的心跳包，心跳内容与server保持一致
 */
public class KeepAliveFactoryImpl implements KeepAliveMessageFactory {

    public boolean isRequest(IoSession session, Object message) {
        return PosMessage.HEART_BEAT_FLAG == ((PosMessage) message).getFlag();
    }

    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    public Object getRequest(IoSession session) {
        return null;
    }

    public Object getResponse(IoSession session, Object request) {
        return PosMessage.obtain(PosMessage.HEART_BEAT_FLAG, null);
    }

}

