package com.yuansfer.client.listener;

import com.yuansfer.client.connect.PIOSession;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/2 15:42
 * @Desciption 消息接收Listener
 */
public abstract class AbstractMsgReceivedListener implements ISessionListener {
    @Override
    public void onSessionAdd(PIOSession session) {

    }

    @Override
    public void onSessionRemove(PIOSession session) {

    }

    @Override
    public void onMessageSent(PIOSession session, Object msg) {

    }

    @Override
    public void onMessageSendFail(Object msg, String reason) {

    }
}
