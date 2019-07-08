/*
 * Copyright (c)  2016-2018.  Yuanex Inc. All rights reserved File :
 * Creation :  18-8-27 下午12:56
 * Description : NetworkUtils.java
 * Author : baishixian@gmail.com
 */

package com.yuansfer.client.netmonitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * NetworkUtils
 *
 * @author baishixian
 * @date 2018/8/15 15:13
 */
class NetworkUtils {

    /**
     * 判断网络是否连接
     */
    static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            @SuppressLint("MissingPermission")
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                return info.getState() == NetworkInfo.State.CONNECTED;
            }
        }

        return false;
    }

    /**
     * 获取当前的网络状态 ：没有网络-0：WIFI网络1：4G网络-4：3G网络-3：2G网络-2*
     *
     * @param context
     * @return
     */
    public static @NetworkType
    String getNetworkType(Context context) {
        //结果返回值
        String netType = NetworkType.UNKNOWN;
        //获取手机所有连接管理对象
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == manager) { // 为空则认为无网络
            return NetworkType.NONE;
        }

        //获取NetworkInfo对象
        @SuppressLint("MissingPermission")
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //NetworkInfo对象为空 则代表没有网络
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return NetworkType.NONE;
        }

        //否则 NetworkInfo对象不为空 则获取该networkInfo的类型
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NetworkType.WIFI;
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            netType = getMobileNetWorkType(context);
        }

        return netType;
    }

    private static String getMobileNetWorkType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetworkType.MOBILE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetworkType.MOBILE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetworkType.MOBILE_4G;
            default:
                return NetworkType.UNKNOWN;
        }
    }
}
