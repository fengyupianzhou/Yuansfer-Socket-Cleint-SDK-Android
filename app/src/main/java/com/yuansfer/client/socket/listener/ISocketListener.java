package com.yuansfer.client.socket.listener;

import org.apache.mina.core.service.IoService;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 11:47
 * @Desciption socket状态监听
 */
public interface ISocketListener {

    /**
     * socket启动
     *
     * @param service IoService
     */
    void onSocketStart(IoService service);

    /**
     * socket停止
     *
     * @param service IoService
     */
    void onSocketStop(IoService service);
}
