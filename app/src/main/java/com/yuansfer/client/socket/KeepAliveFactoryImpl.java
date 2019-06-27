package com.yuansfer.client.socket;

import com.yuansfer.client.socket.protocol.SocketMessage;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/26 16:03
 * @Desciption 心跳工作，响应server发来的心跳包，心跳内容与server保持一致
 */
public class KeepAliveFactoryImpl implements KeepAliveMessageFactory {

    public boolean isRequest(IoSession session, Object message) {
        return SocketMessage.HEART_BEAT_FLAG == ((SocketMessage) message).getFlag();
    }

    public boolean isResponse(IoSession session, Object message) {
        return false;
    }

    public Object getRequest(IoSession session) {
        return null;
    }

    public Object getResponse(IoSession session, Object request) {
        return SocketMessage.obtain(SocketMessage.HEART_BEAT_FLAG, null);
    }

}

