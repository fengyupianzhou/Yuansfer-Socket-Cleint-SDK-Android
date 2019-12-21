package com.yuansfer.client.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.yuansfer.client.netmonitor.NetChangeObserver;
import com.yuansfer.client.netmonitor.NetMonitor;
import com.yuansfer.client.connect.PosConnectConfig;
import com.yuansfer.client.connect.PosClientConnector;
import com.yuansfer.client.util.LogUtils;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/25 15:20
 * @Desciption Sokcet客户端Service
 */
public class PosClientService extends Service implements NetChangeObserver {

    private static final String ACTION_SOCKET_SERVER_SERVICE = "com.android.service.action.PosClientService";
    private PosClientConnector mSocketConnector;

    public static void startService(Context context, String remoteAddr) {
        startService(context, remoteAddr, PosConnectConfig.DEFAULT_PORT);
    }

    public static void startService(Context context, String remoteAddr, int remotePort) {
        PosConnectConfig.SocketConfigBuilder builder = new PosConnectConfig.SocketConfigBuilder()
                .setRemoteAddress(remoteAddr).setRemotePort(remotePort);
        startService(context, builder.build());
    }

    public static void startService(Context context, PosConnectConfig config) {
        Intent intent = new Intent(ACTION_SOCKET_SERVER_SERVICE);
        intent.setPackage(context.getPackageName());
        intent.putExtra("config", config);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(ACTION_SOCKET_SERVER_SERVICE);
        intent.setPackage(context.getPackageName());
        context.stopService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NetMonitor.getInstance().start(this);
        NetMonitor.getInstance().registerObserver(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && mSocketConnector == null) {
            PosConnectConfig config = intent.getParcelableExtra("config");
            mSocketConnector = new PosClientConnector(this, config);
        }
        return Service.START_STICKY;
    }

    @Override
    public void onNetConnected() {
        LogUtils.d("网络已连接");
        if (mSocketConnector != null) {
            mSocketConnector.startConnection();
        }
    }

    @Override
    public void onNetDisConnect() {
        LogUtils.d("网络已断开");
        if (mSocketConnector != null) {
            mSocketConnector.dismissConnection();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NetMonitor.getInstance().removeObserver(this);
        NetMonitor.getInstance().release(this);
        if (mSocketConnector != null) {
            mSocketConnector.dismissConnection();
            mSocketConnector = null;
        }
    }

}
