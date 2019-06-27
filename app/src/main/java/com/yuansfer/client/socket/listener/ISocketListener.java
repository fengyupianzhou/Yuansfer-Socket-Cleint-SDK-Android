package com.yuansfer.client.socket.listener;

import org.apache.mina.core.service.IoService;
/**
* @Author Fly-Android
* @CreateDate 2019/6/25 15:19
* @Desciption Socket连接状态监听器
*/
public interface ISocketListener {

    void onSocketStart(IoService service);
    void onSocketStop(IoService service);
}
