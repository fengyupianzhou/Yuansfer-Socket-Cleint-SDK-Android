package com.yuansfer.client.connect;

import java.net.SocketAddress;

/**
 * 连接session
 */
public class PosSession {

    private boolean isConnected;
    private SocketAddress remoteAddress;
    private SocketAddress localAddress;

    public PosSession(boolean isConnected, SocketAddress remoteAddress, SocketAddress localAddress) {
        this.isConnected = isConnected;
        this.remoteAddress = remoteAddress;
        this.localAddress = localAddress;
    }

    public SocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    public SocketAddress getLocalAddress() {
        return localAddress;
    }

    public boolean isConnected() {
        return isConnected;
    }

}

