package com.yuansfer.client.socket.listener;

import org.apache.mina.core.session.IoSession;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/2 15:42
 * @Desciption 消息接收Listener
 */
public abstract class AbstractMsgReceivedListener implements ISessionListener {
    @Override
    public void onSessionAdd(IoSession session) {

    }

    @Override
    public void onSessionRemove(IoSession session) {

    }

    @Override
    public void onMessageSent(IoSession session, Object msg) {

    }

    @Override
    public void onMessageSendFail(Object msg, String reason) {

    }
}
