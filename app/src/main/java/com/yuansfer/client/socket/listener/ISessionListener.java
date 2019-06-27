package com.yuansfer.client.socket.listener;

import org.apache.mina.core.session.IoSession;
/**
* @Author Fly-Android
* @CreateDate 2019/6/25 15:19
* @Desciption 会话监听器
*/
public interface ISessionListener {

    void onSessionAdd(IoSession session);

    void onSessionRemove(IoSession session);

    void onMessageSent(IoSession session, Object msg);

    void onMessageSendFail(Object msg, String reason);

    void onMessageReceive(IoSession session, Object msg);
}
