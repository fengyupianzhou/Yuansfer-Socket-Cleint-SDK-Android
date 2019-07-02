package com.yuansfer.client.socket.listener;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 11:47
 * @Desciption 连接状态监听
 */
public interface IConnectStateListener {

    /**
     * 设备已连接
     */
    void onDeviceConnected();

    /**
     * 设备已断开
     */
    void onDeviceDisconnected();
}
