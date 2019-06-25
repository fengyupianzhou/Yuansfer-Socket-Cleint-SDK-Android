/*
 * Copyright (c)  2016-2018.  Yuanex Inc. All rights reserved File :
 * Creation :  18-8-27 下午12:56
 * Description : NetworkChangedReceiver.java
 * Author : baishixian@gmail.com
 */

package com.yuansfer.client.netmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;


/**
 * NetworkChangedReceiver
 *
 * @author baishixian
 * @date 2018/8/15 14:51
 */
class NetworkChangedReceiver extends BroadcastReceiver {

    private static final String CUSTOM_ANDROID_NET_CHANGE_ACTION = "gdut.bsx.network.CONNECTIVITY_CHANGE";
    private static final String ANDROID_NET_CHANGE_ACTION = ConnectivityManager.CONNECTIVITY_ACTION;
    private static MonitorCallback monitorCallback;
    private static NetworkChangedReceiver mNetworkChangedReceiver;

    private static BroadcastReceiver getReceiver() {
        if (null == mNetworkChangedReceiver) {
            mNetworkChangedReceiver = new NetworkChangedReceiver();
        }

        return mNetworkChangedReceiver;
    }

    /**
     * 注册
     * @param context
     */
    public static void registerNetworkStateReceiver(@NonNull Context context, @NonNull MonitorCallback callback) {
        monitorCallback = callback;
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 反注册
     * @param context
     */
    public static void unRegisterNetworkStateReceiver(Context context) {
        if (mNetworkChangedReceiver != null) {
            context.getApplicationContext().unregisterReceiver(mNetworkChangedReceiver);
        }

        mNetworkChangedReceiver = null;
        monitorCallback = null;
    }

    /**
     * 检查网络状态
     * @param context
     */
    public static void checkNetworkState(Context context) {
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        context.sendBroadcast(intent);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mNetworkChangedReceiver = this;

        if (intent != null) {
            String action = intent.getAction();
            if (ANDROID_NET_CHANGE_ACTION.equals(action) || CUSTOM_ANDROID_NET_CHANGE_ACTION.equals(action)) {
                notifyObserver(NetworkUtils.isConnected(context));
            }
        }
    }

    private void notifyObserver(Boolean isNetAvailable) {
        if (monitorCallback != null) {
            if (isNetAvailable) {
                monitorCallback.notifyChanged(MonitorCallback.NetworkState.NET_CONNECTED);
            } else {
                monitorCallback.notifyChanged(MonitorCallback.NetworkState.NET_DISCONNECTED);
            }
        }
    }
}
