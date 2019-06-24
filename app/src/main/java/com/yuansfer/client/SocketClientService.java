package com.yuansfer.client;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class SocketClientService extends Service {

    private static final String ACTION_SOCKET_SERVER_SERVICE = "com.android.service.action.SocketClientService";
    private SocketClientConnector mSocketConnector;

    public static void startService(Context context, String remoteAddr) {
        startService(context, remoteAddr, SocketConfig.DEFAULT_PORT);
    }

    public static void startService(Context context, String remoteAddr, int remotePort) {
        SocketConfig.SocketConfigBuilder builder = new SocketConfig.SocketConfigBuilder()
                .setRemoteAddress(remoteAddr).setRemotePort(remotePort);
        startService(context, builder.build());
    }

    public static void startService(Context context, SocketConfig config) {
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
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && mSocketConnector == null) {
            SocketConfig config = (SocketConfig) intent.getSerializableExtra("config");
            mSocketConnector = new SocketClientConnector(config);
            mSocketConnector.startConnection();
        }
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSocketConnector != null) {
            mSocketConnector.stopConnection();
            mSocketConnector = null;
        }
    }

}
