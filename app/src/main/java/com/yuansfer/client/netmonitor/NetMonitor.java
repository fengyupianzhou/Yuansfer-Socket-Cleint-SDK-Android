/*
 * Copyright (c)  2016-2018.  Yuanex Inc. All rights reserved File :
 * Creation :  18-8-27 下午12:56
 * Description : NetMonitor.java
 * Author : baishixian@gmail.com
 */

package com.yuansfer.client.netmonitor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.yuansfer.client.utils.LogUtils;

import java.util.HashSet;
import java.util.Set;


/**
 * NetMonitor
 *
 * @author baishixian
 * @date 2018/8/15 14:38
 */
public class NetMonitor implements MonitorCallback {

    private final Set<NetChangeObserver> mNetChangeObservers;

    @Override
    public void notifyChanged(int netState) {
        LogUtils.d( "NetMonitor notifyChanged netChangeObservers size: " + mNetChangeObservers.size());
        for (NetChangeObserver observer : mNetChangeObservers) {
            if (netState == NetworkState.NET_CONNECTED) {
                observer.onNetConnected();
            } else {
                observer.onNetDisConnect();
            }
        }
    }

    private static class NetMonitorHolder {
        private final static NetMonitor INSTANCE = new NetMonitor();
    }

    private NetMonitor() {
        mNetChangeObservers = new HashSet<>();
    }

    public static NetMonitor getInstance() {
        return NetMonitorHolder.INSTANCE;
    }

    /**
     * 开始监听网络变化
     * @param context
     */
    public void start(@NonNull Context context) {
        LogUtils.d( "NetMonitor start with BroadcastReceiver.");
        NetworkChangedReceiver.registerNetworkStateReceiver(context, this);
    }

    /**
     * 释放监听网络变化
     * @param context
     */
    public void release(@NonNull Context context) {
        LogUtils.d( "NetMonitor release");
        NetworkChangedReceiver.unRegisterNetworkStateReceiver(context);
        removeAllObserver();
    }


    /**
     * 检查网络状态
     * @param context
     */
    public void checkNetworkState(Context context) {
        LogUtils.d( "NetMonitor checkNetworkState");
        NetworkChangedReceiver.checkNetworkState(context);

        /*
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            NetworkChangedReceiver.checkNetworkState(context);
        } else {
            NetworkChangeCallBack.checkNetworkState(context);
        }
        */
    }

    /**
     * 注册网络状态变化订阅者
     * @param netChangeObserver
     */
    public void registerObserver(@NonNull NetChangeObserver netChangeObserver) {
        mNetChangeObservers.add(netChangeObserver);
    }

    /**
     * 移除网络状态变化订阅者
     * @param netChangeObserver
     */
    public void removeObserver(@NonNull NetChangeObserver netChangeObserver) {
        mNetChangeObservers.remove(netChangeObserver);
    }

    /**
     * 移除所有网络状态变化订阅者
     */
    public void removeAllObserver() {
        mNetChangeObservers.clear();
    }

}
