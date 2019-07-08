/*
 * Copyright (c)  2016-2018.  Yuanex Inc. All rights reserved File :
 * Creation :  18-8-27 下午12:56
 * Description : NetChangeObserver.java
 * Author : baishixian@gmail.com
 */

package com.yuansfer.client.netmonitor;


import android.support.annotation.MainThread;

/**
 * NetChangeObserver 网络改变的观察者
 *
 * @author baishixian
 * @date 2018/8/14 17:30
 */
public interface NetChangeObserver {
    /**
     * 网络连接
     */
    @MainThread
    void onNetConnected();

    /**
     * 网络断开
     */
    @MainThread
    void onNetDisConnect();
}
