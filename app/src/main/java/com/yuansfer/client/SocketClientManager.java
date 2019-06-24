package com.yuansfer.client;

public class SocketClientManager {

    private static SocketClientManager sInstance;

    private SocketClientManager() {

    }

    public static SocketClientManager getInstance() {
        if (sInstance == null) {
            synchronized (SocketClientManager.class) {
                if (sInstance == null) {
                    sInstance = new SocketClientManager();
                }
            }
        }
        return sInstance;
    }

}
