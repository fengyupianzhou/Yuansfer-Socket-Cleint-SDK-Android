package com.yuansfer.client.socket.listener;

import org.apache.mina.core.session.IoSession;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 11:47
 * @Desciption 连接会话监听
 */
public interface ISessionListener {

    /**
     * 会话加入
     *
     * @param session 会话
     */
    void onSessionAdd(IoSession session);

    /**
     * 会话移除
     *
     * @param session 会话
     */
    void onSessionRemove(IoSession session);

    /**
     * 消息已送达
     *
     * @param session 会话
     * @param msg     消息内容
     */
    void onMessageSent(IoSession session, Object msg);

    /**
     * 消息发送失败
     *
     * @param msg    消息内容
     * @param reason 失败原由
     */
    void onMessageSendFail(Object msg, String reason);

    /**
     * 消息已到达
     *
     * @param session 会话
     * @param msg     消息内容
     */
    void onMessageReceive(IoSession session, Object msg);
}
